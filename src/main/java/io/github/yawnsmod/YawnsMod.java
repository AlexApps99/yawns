// By AlexApps
package io.github.yawnsmod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.lwjgl.glfw.GLFW;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("yawnsmod")
public class YawnsMod {
	public static final Logger LOGGER = LogManager.getLogger();
	public static KeyBinding yawnsgui;
	
	public YawnsMod() {
		// Todo mcforge.readthedocs.io/en/latest/concepts/internationalization
		LOGGER.info("\"A yawn is a silent shout\" - The Prince of Paradox");
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
	}

	private void clientSetup(FMLClientSetupEvent event) {
		LOGGER.info("*starts yawning*");
		yawnsgui = new KeyBinding("key.yawnsgui", GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.yawnsmod");
		ClientRegistry.registerKeyBinding(yawnsgui);
		MinecraftForge.EVENT_BUS.register(HackManager.getInstance());
		LOGGER.info("*stops yawning*");
	}
}
