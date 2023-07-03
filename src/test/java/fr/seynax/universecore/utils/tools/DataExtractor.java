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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import fr.seynax.universecore.utils.file.FileUtils;

/**
 *
 */
public class DataExtractor
{
	public final Map<String, List<String>> extract(final List<String> regexesIn, final String contentIn)
	{
		final Map<String, List<String>> extracteds = new LinkedHashMap<>();
		for (final var regex : regexesIn)
		{
			final var	pattern	= Pattern.compile(regex);
			final var	matcher	= pattern.matcher(contentIn);

			List<String> regexExtracteds = null;
			while (matcher.find())
			{
				if (regexExtracteds == null)
				{
					regexExtracteds = extracteds.get(regex);
					if (regexExtracteds == null)
					{
						regexExtracteds = new ArrayList<>();
						extracteds.put(regex, regexExtracteds);
					}
				}

				regexExtracteds.add(matcher.group());

			}
		}

		return extracteds;
	}

	public final Map<String, List<String>> extractFromFile(final List<String> regexesIn, final String filePathIn) throws Exception
	{
		final var content = FileUtils.content(filePathIn);

		return this.extract(regexesIn, content);
	}

	public final Map<String, List<String>> extractFromFile(final List<String> regexesIn, final File fileIn) throws Exception
	{
		final var content = FileUtils.content(fileIn);

		return this.extract(regexesIn, content);
	}
}