package fr.seynax.universecore;

import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registries
{
	private final String modid;
	public final  DeferredRegister<Block> blocks;
	private  IEventBus eventBus;

	public Registries(String modidIn  )
	{
		modid = modidIn;
		blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, modidIn);
	}

	public final Registries register(IEventBus eventBusIn)
	{
		eventBus = eventBusIn;
	
		blocks.register(eventBus);
		
		return this;
	}

    public final RegistryObject<Block> register(DeferredRegister<Block> registryIn, String idIn, Supplier<? extends Block> sup)
    {
    	return registryIn.register(idIn, sup);
    }
}