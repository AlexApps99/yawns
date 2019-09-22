// By AlexApps
package io.github.yawnsmod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.lwjgl.glfw.GLFW;

import io.github.yawnsmod.HackManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("yawnsmod")
public class YawnsMod {
	public static final Logger LOGGER = LogManager.getLogger();
	public static KeyBinding yawnsgui;
	public static HackManager hm = new HackManager();
	
	public YawnsMod() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		LOGGER.info("Registering Hacks...");
		for(Hack hack : hm.getEnabledHacks()) {
			MinecraftForge.EVENT_BUS.register(hack);
		}
	}

	private void clientSetup(FMLClientSetupEvent event) {
		yawnsgui = new KeyBinding("key.yawnsgui", GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.yawnsmod");
		ClientRegistry.registerKeyBinding(yawnsgui);
	}
}
