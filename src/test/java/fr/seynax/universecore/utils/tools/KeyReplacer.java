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
package fr.seynax.universecore.utils.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import fr.seynax.universecore.utils.file.FileUtils;

/**
 *
 */
public class KeyReplacer
{
	public final static String replace(final Map<String, String> keysIn, final String contentIn)
	{
		var content = contentIn;
		for (final var entry : keysIn.entrySet())
		{
			final var	key		= entry.getKey();
			final var	value	= entry.getValue();
			content = content.replace(key, value);
		}

		return content;
	}

	public final static String replaceFromFile(final Map<String, String> keysIn, final String filePathIn) throws Exception
	{
		final var content = FileUtils.content(filePathIn);

		return KeyReplacer.replace(keysIn, content);
	}

	public final static String replaceFromFile(final Map<String, String> keysIn, final File fileIn) throws Exception
	{
		final var content = FileUtils.content(fileIn);

		return KeyReplacer.replace(keysIn, content);
	}

	public final static void replaceAndWriteFile(final Map<String, String> keysIn, final String filePathIn) throws Exception
	{
		final var replaced = KeyReplacer.replace(keysIn, FileUtils.content(filePathIn));
		FileUtils.write(filePathIn, replaced);
	}

	public final static void replaceAndWriteFile(final Map<String, String> keysIn, final File fileIn) throws Exception
	{
		final var replaced = KeyReplacer.replace(keysIn, FileUtils.content(fileIn));
		FileUtils.write(fileIn, replaced);
	}

	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return all keyeds values from file content from filePathIn line by line, each of all keys and values are on one line and separated by ->, to cancel -> use \ (\-\> or just -\>)
	 * @throws Exception
	 */
	public final static Map<String, String> keysFromFile(final String filePathIn) throws Exception
	{
		return KeyReplacer.keysFromFile(new File(filePathIn));
	}

	/**
	 * @author Seynax
	 * @param fileIn
	 * @return all keyeds values from fileIn content line by line, each of all keys and values are on one line and separated by ->, to cancel -> use \ (\-\> or just -\>)
	 * @throws Exception
	 */
	public final static Map<String, String> keysFromFile(final File fileIn) throws Exception
	{
		final Map<String, String> keys = new HashMap<>();

		FileUtils.linesExecutions(fileIn, lineIn ->
		{
			final var splitteds = lineIn.split("->");

			if (splitteds == null || splitteds.length <= 1)
			{
				return;
			}

			final var	key		= splitteds[0].replace("\\-\\>", "");
			final var	value	= splitteds[1].replace("\\-\\>", "");

			keys.put(key, value);
		});

		return keys;
	}

	public final static String replaceFromFile(final String keysConfigFilePathIn, final String filePathIn) throws Exception
	{
		return KeyReplacer.replaceFromFile(new File(keysConfigFilePathIn), new File(filePathIn));
	}

	public final static String replaceFromFile(final File keysConfigFileIn, final File fileIn) throws Exception
	{
		return KeyReplacer.replaceFromFile(KeyReplacer.keysFromFile(keysConfigFileIn), fileIn);
	}

	public final static void replaceAndWriteFile(final String keysConfigFilePathIn, final String filePathIn) throws Exception
	{
		final var replaced = KeyReplacer.replace(KeyReplacer.keysFromFile(keysConfigFilePathIn), FileUtils.content(filePathIn));
		FileUtils.write(filePathIn, replaced);
	}

	public final static void replaceAndWriteFile(final File keysConfigFileIn, final File fileIn) throws Exception
	{
		final var replaced = KeyReplacer.replace(KeyReplacer.keysFromFile(keysConfigFileIn), FileUtils.content(fileIn));
		FileUtils.write(fileIn, replaced);
	}
}