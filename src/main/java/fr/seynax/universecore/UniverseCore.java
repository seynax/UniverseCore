package fr.seynax.universecore;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UniverseCore
{
	private static  DeferredRegister<Block> BLOCKS;

    public final static void init(String modidIn)
    {
    	BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, modidIn);
    }

    public final static void register(IEventBus modEventBusIn)
    {
  	  	BLOCKS.register(modEventBusIn);
    }

    public final static RegistryObject<Block> register(String idIn, Block blockIn)
    {
    	return BLOCKS.register(idIn, () -> blockIn);
    }
}