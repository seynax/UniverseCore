/**
 * Copyright 2021-2023 Onsiea Studio All rights reserved.<br>
 * <br>
 *
 * This file is part of Onsiea Engine project.
 * (https://github.com/OnsieaStudio/OnsieaEngine)<br>
 * <br>
 *
 * Onsiea Engine is [licensed]
 * (https://github.com/OnsieaStudio/OnsieaEngine/blob/main/LICENSE) under the terms of
 * the "GNU General Public Lesser License v2.1" (LGPL-2.1).
 * https://github.com/OnsieaStudio/OnsieaEngine/wiki/License#license-and-copyright<br>
 * <br>
 *
 * Onsiea Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.<br>
 * <br>
 *
 * Onsiea Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.<br>
 * <br>
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Onsiea Engine. If not, see <https://www.gnu.org/licenses/>.<br>
 * <br>
 *
 * Neither the name "Onsiea Studio", "Onsiea Engine", or any derivative name or the
 * names of its authors / contributors may be used to endorse or promote
 * products derived from this software and even less to name another project or
 * other work without clear and precise permissions written in advance.<br>
 * <br>
 *
 * @Author : Seynax (https://github.com/seynax)<br>
 * @Organization : Onsiea Studio (https://github.com/OnsieaStudio)
 */
package fr.seynax.universecore.utils.file.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import fr.seynax.universecore.utils.StringUtils;
import fr.seynax.universecore.utils.json.JSONArray;
import fr.seynax.universecore.utils.json.JSONObject;
import fr.seynax.universecore.utils.json.JSONValue;

/**
 *
 */
public class JSONParser
{
	// JSON with string content or all lines contained in collection, use JSONReader to read and parse JSON File content

	public final static JSONArray parse(final String contentIn) throws Exception
	{
		final List<String> lines = new ArrayList<>();
		for (var line : contentIn.split("\n"))
		{
			if (line.endsWith("\r"))
			{
				line = line.substring(line.length() - 1);
			}
			lines.add(line);
		}
		return JSONParser.parse(lines);
	}

	public final static JSONArray parse(final Collection<String> linesIn) throws Exception
	{
		final var	tempArrays	= new Stack<JSONArray.Builder>();
		final var	tempObjects	= new Stack<JSONObject.Builder>();

		final var root = new JSONArray.Builder(new JSONValue.Builder("root"));

		final var						quotePattern	= Pattern.compile("\"[.[^\"]]*\"");
		final List<String>				quoteStrings	= new ArrayList<>();
		final List<JSONValue.Builder>	values			= new ArrayList<>();

		var content = "";
		for (final var line : linesIn)
		{
			var refinedLine = line;

			final var matcher = quotePattern.matcher(line);

			while (matcher.find())
			{
				var start = matcher.start();
				if (start < 0 || start > refinedLine.length())
				{
					start = 0;
				}
				var end = matcher.end();
				if (end < 0 || end > refinedLine.length())
				{
					end = refinedLine.length();
				}
				final var group = matcher.group();
				refinedLine = refinedLine.replace(group, "<QUOTED_STRING_TEMPORARY_REPLACEMENT_" + quoteStrings.size() + ">");
				quoteStrings.add(group);
			}

			for (var i = 0; i < refinedLine.length(); i++)
			{
				final var c = refinedLine.charAt(i);

				switch (c)
				{
					case '{':
					{
						JSONValue.Builder key = null;
						if (tempObjects.size() > 0)
						{
							final var poppedObject = tempObjects.pop();

							if (poppedObject != null)
							{
								key = poppedObject.key();

								if (poppedObject.value().value() != null)
								{
									throw new Exception("[ERROR] Array must had just key !");
								}
								poppedObject.value().remove();
							}
						}
						else
						{
							if (key == null && !content.matches("\\s+") && content.length() > 0)
							{
								key = new JSONValue.Builder(StringUtils.removeUnusedBlanks(content));
							}
							else
							{
								key = new JSONValue.Builder();
							}
							values.add(key);
						}

						final var builder = new JSONArray.Builder(key);
						tempArrays.add(builder);

						content = "";

						break;
					}
					case '}':
					{
						if (tempArrays.size() <= 0)
						{
							throw new Exception("JSON parsing failed with refinedLines collections !");
						}

						final var poppedArray = tempArrays.pop();

						if (poppedArray == null)
						{
							throw new Exception("JSON parsing failed with refinedLines collections !");
						}

						if (tempObjects.size() > 0)
						{
							final var poppedObject = tempObjects.pop();
							if (poppedObject != null)
							{
								poppedArray.add(poppedObject.value(StringUtils.removeUnusedBlanks(content)));
							}
						}

						if (tempArrays.size() > 0)
						{
							tempArrays.lastElement().add(poppedArray);
						}
						else
						{
							root.add(poppedArray);
						}
						content = "";

						break;
					}
					case ':':
						final var builder = new JSONObject.Builder(StringUtils.removeUnusedBlanks(content));
						tempObjects.add(builder);
						values.add(builder.key());
						values.add(builder.value());

						content = "";

						break;
					case ',':
						if (tempObjects.size() > 0)
						{
							final var poppedObject = tempObjects.pop();
							if (poppedObject != null)
							{
								if (tempArrays.size() > 0)
								{
									tempArrays.lastElement().add(poppedObject.value(StringUtils.removeUnusedBlanks(content)));
								}
								else
								{
									root.add(poppedObject.value(StringUtils.removeUnusedBlanks(content)));
								}
							}
						}
						content = "";

						break;
					default:
						content += c;
						break;
				}
			}
		}

		if (tempObjects.size() > 0)
		{
			root.add(tempObjects.pop().value(content));
		}

		while (tempObjects.size() > 0)
		{
			root.add(tempObjects.pop());
		}
		tempArrays.clear();
		// tempObjects.clear();

		var i = 0;
		for (final var value : values)
		{
			if (value.isRemoved() || value.value() == null || !value.value().matches("^<QUOTED_STRING_TEMPORARY_REPLACEMENT_[0-9]+>$"))
			{
				continue;
			}

			value.value(quoteStrings.get(i));

			i++;
		}

		values.clear();
		quoteStrings.clear();

		return (JSONArray) root.build();
	}
}