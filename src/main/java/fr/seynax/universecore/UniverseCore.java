package fr.seynax.universecore;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

  	  	BLOCKS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void init(String modidIn)
    {
    	BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, modidIn);
    }

    public RegistryObject<Block> register(String idIn, Block blockIn)
    {
    	return BLOCKS.register(idIn, () -> blockIn);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}