package fr.seynax.universecore.registry;

import java.util.function.Supplier;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ExtendedRegistry<T>
{
	public final DeferredRegister<T> forgeRegistry;

	public ExtendedRegistry(final String modidIn, final IRegistryInitializer<T> initializerIn)
	{
		this.forgeRegistry = initializerIn.initialize(modidIn);
	}

	public final ExtendedRegistry<T> initialize(final IEventBus eventBusIn)
	{
		this.forgeRegistry.register(eventBusIn);

		return this;
	}

	public final RegistryObject<T> registerObject(final String idIn, final Supplier<? extends T> supIn)
	{
		return this.forgeRegistry.register(idIn, supIn);
	}

	/**
	 * Equivalent to registerObject, but does not return the object but the register (allows you to add many items at once, in a single line of code)
	 *
	 * @author Seynax
	 * @param idIn
	 * @param supIn
	 * @return this current extended registry instance
	 */
	public final ExtendedRegistry<T> addObject(final String idIn, final Supplier<? extends T> supIn)
	{
		this.forgeRegistry.register(idIn, supIn);

		return this;
	}

	public final DeferredRegister<T> forgeRegistry()
	{
		return this.forgeRegistry;
	}

	public interface IRegistryInitializer<T>
	{
		DeferredRegister<T> initialize(String modidIn);
	}
}