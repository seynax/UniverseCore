package fr.seynax.universecore;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


@Mod(UniverseCore.MODID)
public class UniverseCore
{
    public static final String MODID = "universecore";
	private static final Logger LOGGER = LogUtils.getLogger();

	private static  DeferredRegister<Block> BLOCKS;

    public UniverseCore()
    {
         MinecraftForge.EVENT_BUS.register(this);
    }

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