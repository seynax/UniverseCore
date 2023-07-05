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
package fr.seynax.universecore.utils.json;

/**
 *
 */
public class JSONUtils
{
	public final static void show(final JSONArray arrayIn)
	{
		System.out.println(JSONUtils.content("", arrayIn));
	}

	public final static void show(final String prefixIn, final JSONArray arrayIn)
	{
		System.out.println(JSONUtils.content(prefixIn, arrayIn));
	}

	public final static String content(final JSONArray arrayIn)
	{
		return JSONUtils.content("", arrayIn);
	}

	public final static String content(final String prefixIn, final JSONArray arrayIn)
	{
		final var content = new StringBuilder();

		content.append(prefixIn + "- " + arrayIn.key() + " [" + arrayIn.count() + "] :\r\n" + prefixIn + "{\r\n");
		for (var i = 0; i < arrayIn.count(); i++)
		{
			final var child = arrayIn.get(i);

			if (child.isArray())
			{
				content.append(JSONUtils.content(prefixIn + "\t", (JSONArray) child));
			}
			else
			{
				content.append(prefixIn).append("\t- ").append(child.key()).append(" -> ").append(((JSONObject) child).value());
			}

			if (i + 1 < arrayIn.count())
			{
				content.append("\r\n");
			}
		}
		content.append("\r\n" + prefixIn + "}");

		return content.toString();
	}
}
