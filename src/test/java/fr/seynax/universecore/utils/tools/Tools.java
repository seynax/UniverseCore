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

/**
 *
 */
public class Tools
{
	// DO KeyReplacer : replace key into value
	// DO DataExtractor from regex
	// TODO DataExctrator from file (or filepath) list
	// TODO DataExtractor from JSON (key -> object, object : string, value or array)
	// TODO DataExtractor : method to config extraction, to output the same data only once (example : list all the block models used by the blocks, but only once even if several blocks use the same model (and therefore
	// several files refer several times to this model))
	// TODO KeyReplaccer : replace regex into value
	// TODO Auto add blocks and items from blockstates, models and config file with equivalant (block->class or else in low priority model->class)
	// TODO Auto write blocks and items registry from blockstates, models from config file or not (Map<String, String) with equivalant (block->class or else in low priority model->class)
	// TODO improve block auto-add and auto-write tools, assuming the material of the blocks depending on whether the name contains a specific keyword (wool, log, wood, ingot, ore, stone, iron, metal, ingot etc) through a
	// configuration file or not (Map<String, String>)
	// TODO tools for modifying coordinates (x, y, z) in model files through filter (equal, not equal, greater, less, greater than or equal, less than or equal) to a fixed or dynamic value (old value +- */% transition value)
	// TODO blockstates file creator according to various properties
	// TODO block class according to certain properties (create the properties if necessary in the right class file, propose it if not) with all the necessary methods (constructor or other)
	// TODO block properties lister from blockstate or block class
}