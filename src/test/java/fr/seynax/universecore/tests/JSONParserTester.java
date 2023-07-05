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
package fr.seynax.universecore.tests;

import fr.seynax.universecore.utils.file.json.JSONParser;
import fr.seynax.universecore.utils.json.JSONUtils;

/**
 *
 */
public class JSONParserTester
{
	public static void main(final String[] args)
	{
		final var json = """
				{
				    "variants": {
				        "facing=east,half=bottom,shape=straight":  { "model": "millenaire:banco_stairs_drying2" },
				        "facing=west,half=bottom,shape=straight":  { "model": "millenaire:banco_stairs_drying2", "y": 180, "uvlock": true },
				        "facing=south,half=bottom,shape=straight": { "model": "millenaire:banco_stairs_drying2", "y": 90, "uvlock": true },
				        "facing=north,half=bottom,shape=straight": { "model": "millenaire:banco_stairs_drying2", "y": 270, "uvlock": true },
				        "facing=east,half=bottom,shape=outer_right":  { "model": "millenaire:banco_outer_stairs_drying2" },
				        "facing=west,half=bottom,shape=outer_right":  { "model": "millenaire:banco_outer_stairs_drying2", "y": 180, "uvlock": true },
				        "facing=south,half=bottom,shape=outer_right": { "model": "millenaire:banco_outer_stairs_drying2", "y": 90, "uvlock": true },
				        "facing=north,half=bottom,shape=outer_right": { "model": "millenaire:banco_outer_stairs_drying2", "y": 270, "uvlock": true },
				        "facing=east,half=bottom,shape=outer_left":  { "model": "millenaire:banco_outer_stairs_drying2", "y": 270, "uvlock": true },
				        "facing=west,half=bottom,shape=outer_left":  { "model": "millenaire:banco_outer_stairs_drying2", "y": 90, "uvlock": true },
				        "facing=south,half=bottom,shape=outer_left": { "model": "millenaire:banco_outer_stairs_drying2" },
				        "facing=north,half=bottom,shape=outer_left": { "model": "millenaire:banco_outer_stairs_drying2", "y": 180, "uvlock": true },
				        "facing=east,half=bottom,shape=inner_right":  { "model": "millenaire:banco_inner_stairs_drying2" },
				        "facing=west,half=bottom,shape=inner_right":  { "model": "millenaire:banco_inner_stairs_drying2", "y": 180, "uvlock": true },
				        "facing=south,half=bottom,shape=inner_right": { "model": "millenaire:banco_inner_stairs_drying2", "y": 90, "uvlock": true },
				        "facing=north,half=bottom,shape=inner_right": { "model": "millenaire:banco_inner_stairs_drying2", "y": 270, "uvlock": true },
				        "facing=east,half=bottom,shape=inner_left":  { "model": "millenaire:banco_inner_stairs_drying2", "y": 270, "uvlock": true },
				        "facing=west,half=bottom,shape=inner_left":  { "model": "millenaire:banco_inner_stairs_drying2", "y": 90, "uvlock": true },
				        "facing=south,half=bottom,shape=inner_left": { "model": "millenaire:banco_inner_stairs_drying2" },
				        "facing=north,half=bottom,shape=inner_left": { "model": "millenaire:banco_inner_stairs_drying2", "y": 180, "uvlock": true },
				        "facing=east,half=top,shape=straight":  { "model": "millenaire:banco_stairs_drying2", "x": 180, "uvlock": true },
				        "facing=west,half=top,shape=straight":  { "model": "millenaire:banco_stairs_drying2", "x": 180, "y": 180, "uvlock": true },
				        "facing=south,half=top,shape=straight": { "model": "millenaire:banco_stairs_drying2", "x": 180, "y": 90, "uvlock": true },
				        "facing=north,half=top,shape=straight": { "model": "millenaire:banco_stairs_drying2", "x": 180, "y": 270, "uvlock": true },
				        "facing=east,half=top,shape=outer_right":  { "model": "millenaire:banco_outer_stairs_drying2", "x": 180, "y": 90, "uvlock": true },
				        "facing=west,half=top,shape=outer_right":  { "model": "millenaire:banco_outer_stairs_drying2", "x": 180, "y": 270, "uvlock": true },
				        "facing=south,half=top,shape=outer_right": { "model": "millenaire:banco_outer_stairs_drying2", "x": 180, "y": 180, "uvlock": true },
				        "facing=north,half=top,shape=outer_right": { "model": "millenaire:banco_outer_stairs_drying2", "x": 180, "uvlock": true },
				        "facing=east,half=top,shape=outer_left":  { "model": "millenaire:banco_outer_stairs_drying2", "x": 180, "uvlock": true },
				        "facing=west,half=top,shape=outer_left":  { "model": "millenaire:banco_outer_stairs_drying2", "x": 180, "y": 180, "uvlock": true },
				        "facing=south,half=top,shape=outer_left": { "model": "millenaire:banco_outer_stairs_drying2", "x": 180, "y": 90, "uvlock": true },
				        "facing=north,half=top,shape=outer_left": { "model": "millenaire:banco_outer_stairs_drying2", "x": 180, "y": 270, "uvlock": true },
				        "facing=east,half=top,shape=inner_right":  { "model": "millenaire:banco_inner_stairs_drying2", "x": 180, "y": 90, "uvlock": true },
				        "facing=west,half=top,shape=inner_right":  { "model": "millenaire:banco_inner_stairs_drying2", "x": 180, "y": 270, "uvlock": true },
				        "facing=south,half=top,shape=inner_right": { "model": "millenaire:banco_inner_stairs_drying2", "x": 180, "y": 180, "uvlock": true },
				        "facing=north,half=top,shape=inner_right": { "model": "millenaire:banco_inner_stairs_drying2", "x": 180, "uvlock": true },
				        "facing=east,half=top,shape=inner_left":  { "model": "millenaire:banco_inner_stairs_drying2", "x": 180, "uvlock": true },
				        "facing=west,half=top,shape=inner_left":  { "model": "millenaire:banco_inner_stairs_drying2", "x": 180, "y": 180, "uvlock": true },
				        "facing=south,half=top,shape=inner_left": { "model": "millenaire:banco_inner_stairs_drying2", "x": 180, "y": 90, "uvlock": true },
				        "facing=north,half=top,shape=inner_left": { "model": "millenaire:banco_inner_stairs_drying2", "x": 180, "y": 270, "uvlock": true }
				    }
				}
				""";
		try
		{
			final var array = JSONParser.parse(json);

			JSONUtils.show(array);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}
}