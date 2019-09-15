// By AlexApps
package io.github.yawnsmod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = YawnsMod.MODID, name = YawnsMod.NAME, version = YawnsMod.VERSION, useMetadata = true)
public class YawnsMod
{
    public static final String MODID = "yawnsmod";
    public static final String NAME = "YAWNS Mod";
    public static final String VERSION = "0.1";

    public static Logger logger;
    public static KeyBinding yawnsgui;
    
    @Mod.Instance("yawnsmod")
    public static YawnsMod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        yawnsgui = new KeyBinding("key.yawnsgui", Keyboard.KEY_RCONTROL, "key.categories.yawnsmod");
        ClientRegistry.registerKeyBinding(yawnsgui);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	logger.info("Registering Test Overlay...");
    	MinecraftForge.EVENT_BUS.register(new TestOverlay());
    	MinecraftForge.EVENT_BUS.register(new ChatModifier());
    	MinecraftForge.EVENT_BUS.register(new KeyBindHandler());
    }
}
