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
package fr.seynax.universecore.manager;

import java.util.function.Supplier;

import fr.seynax.universecore.registry.ExtendedRegistry;
import fr.seynax.universecore.registry.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 *
 */
public class UniverseCoreManager
{
	// TODO blocks registry
	// TODO items registry
	// TODO all others registries

	private final Registries				registries;
	private final ExtendedRegistry<Block>	registryBlock;

	@SuppressWarnings("unchecked")
	@SafeVarargs
	public UniverseCoreManager(final String modidIn, final Class<? extends ExtendedRegistry<?>>... customRegistriesClassesIn)
	{
		this.registries		= new Registries(modidIn, customRegistriesClassesIn);
		this.registryBlock	= (ExtendedRegistry<Block>) this.registries.get(ForgeRegistries.BLOCKS.getRegistryKey().toString());
	}

	public final UniverseCoreManager register(final IEventBus modEventBusIn)
	{
		// Mod event bus register

		for (final var registry : this.registries.values())
		{
			registry.initialize(modEventBusIn);
		}

		// TODO Forge event bus register

		return this;
	}

	// Block register function

	public final RegistryObject<Block> block(final String idIn, final Supplier<? extends Block> blockSupplierIn)
	{
		return this.registryBlock.registerObject(idIn, blockSupplierIn);
	}

	public final RegistryObject<Block> block(final String idIn, final Properties propertiesIn)
	{
		return this.registryBlock.registerObject(idIn, () -> new Block(propertiesIn));
	}

	public final RegistryObject<Block> block(final String idIn, final Material materialIn)
	{
		return this.registryBlock.registerObject(idIn, () -> new Block(Properties.of(materialIn)));
	}
}