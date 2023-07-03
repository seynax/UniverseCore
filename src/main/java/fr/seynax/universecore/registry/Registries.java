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
package fr.seynax.universecore.registry;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.seynax.universecore.registries.RegistryBlock;
import fr.seynax.universecore.test.UniverseCore;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

/**
 *
 */
public class Registries
{
	private final static IKeyedRegistryInitializer<?>[] NEEDED_REGISTRIES = {
		Registries.keyedRegistry(ForgeRegistries.BLOCKS, RegistryBlock::new),
		Registries.keyedRegistry(ForgeRegistries.ITEMS, modIdIn -> new ExtendedRegistry<>(modIdIn, _modIdIn -> DeferredRegister.create(ForgeRegistries.ITEMS, _modIdIn))) };

	private final static <T> IKeyedRegistryInitializer<T> keyedRegistry(final IForgeRegistry<T> registryIn, final IExtendedRegistryInitializer<T> registryInitializerIn)
	{
		return Registries.keyedRegistry(registryIn.getRegistryKey().toString(), registryInitializerIn);
	}

	private final static <T> IKeyedRegistryInitializer<T> keyedRegistry(final String keyIn, final IExtendedRegistryInitializer<T> registryInitializerIn)
	{
		return new IKeyedRegistryInitializer<>()
		{
			@Override
			public String key()
			{
				return keyIn;
			}

			@Override
			public IExtendedRegistryInitializer<T> initializer()
			{
				return registryInitializerIn;
			}
		};
	}

	private final Map<String, ExtendedRegistry<?>> registries;

	@SafeVarargs
	public Registries(final String modidIn, final Class<? extends ExtendedRegistry<?>>... customRegistriesClassesIn)
	{
		this.registries = new LinkedHashMap<>();
		final Map<String, IKeyedRegistryInitializer<?>> neededRegistries = new HashMap<>();
		for (final var neededRegistryKeyedInitializer : Registries.NEEDED_REGISTRIES)
		{
			neededRegistries.put(neededRegistryKeyedInitializer.key(), neededRegistryKeyedInitializer);
		}

		try
		{
			for (final var registryClass : customRegistriesClassesIn)
			{
				final var	constructor	= registryClass.getConstructor(String.class);
				final var	registry	= constructor.newInstance(modidIn);

				final var key = registry.forgeRegistry.getRegistryKey().toString();

				this.registries.put(key, registry);
				neededRegistries.remove(key);
			}
		}
		catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			UniverseCore.LOGGER.error("Failed to make new instance from registry class object with class(String modidIn) supposed constructor !");

			e.printStackTrace();
		}

		for (final var neededRegistryKeyedInitializer : neededRegistries.values())
		{
			this.registries.put(neededRegistryKeyedInitializer.key(), neededRegistryKeyedInitializer.initializer().initialize(modidIn));
		}
		neededRegistries.clear();
	}

	public final void add(final String keyIn, final ExtendedRegistry<Object> registryIn)
	{
		this.registries.put(keyIn, registryIn);
	}

	public final boolean contains(final String keyIn)
	{
		return this.registries.containsKey(keyIn);
	}

	public final ExtendedRegistry<?> get(final String keyIn)
	{
		return this.registries.get(keyIn);
	}

	public interface IKeyedRegistryInitializer<T>
	{
		String key();

		IExtendedRegistryInitializer<T> initializer();
	}

	public interface IExtendedRegistryInitializer<T>
	{
		ExtendedRegistry<T> initialize(String modidIn);
	}

	public final int size()
	{
		return this.registries.size();
	}

	public final Collection<ExtendedRegistry<?>> values()
	{
		return this.registries.values();
	}
}