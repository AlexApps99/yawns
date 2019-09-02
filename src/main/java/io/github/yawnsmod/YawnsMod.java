package io.github.yawnsmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = YawnsMod.MODID, name = YawnsMod.NAME, version = YawnsMod.VERSION, useMetadata= true)
public class YawnsMod
{
    public static final String MODID = "yawnsmod";
    public static final String NAME = "YAWNS Mod";
    public static final String VERSION = "0.1";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("Pre-init");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	logger.info("Init");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	logger.info("Post-init");
    	MinecraftForge.EVENT_BUS.register(new TestOverlay());
    }
}
