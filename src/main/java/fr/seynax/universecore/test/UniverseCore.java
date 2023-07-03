package fr.seynax.universecore.test;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import fr.seynax.universecore.manager.UniverseCoreManager;
import fr.seynax.universecore.test.registries.BlockRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UniverseCore.MODID)
public class UniverseCore
{
	public static final String				MODID	= "universecore";
	public final static Logger				LOGGER	= LogUtils.getLogger();
	public final static UniverseCoreManager	MANAGER	= new UniverseCoreManager(UniverseCore.MODID, BlockRegistry.class);

	public UniverseCore()
	{
		MinecraftForge.EVENT_BUS.register(this);
		UniverseCore.LOGGER.debug("UniverseCore TEST initialization !");

		final var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		UniverseCore.MANAGER.register(modEventBus);
	}
}