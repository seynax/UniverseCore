package fr.seynax.universecore;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;


@Mod(UniverseCore.MODID)
public class UniverseCore
{
    public static final String MODID = "universecore";


    public UniverseCore()
    {
         MinecraftForge.EVENT_BUS.register(this);
    }
}