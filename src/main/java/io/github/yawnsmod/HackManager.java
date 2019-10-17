package io.github.yawnsmod;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import io.github.yawnsmod.hacks.*;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.lwjgl.glfw.GLFW;

// todo add basic clientside commands with brigadier
// use https://github.com/TheElectronWill/night-config for config as it's already a forge dep
// try to run ondisabled when game is closed
// localize more stuff
public class HackManager {
	private final Map<Hack, Integer> hacks = new HashMap<>();
	private final static HackManager hm = new HackManager();
	
	private HackManager() {
		hacks.put(new AdvancedTooltip(), GLFW.GLFW_KEY_APOSTROPHE);
		hacks.put(new AntiOverlay(), GLFW.GLFW_KEY_RIGHT_BRACKET);
		hacks.put(new FancyChat(), GLFW.GLFW_KEY_MINUS);
		hacks.put(new FullBright(), GLFW.GLFW_KEY_UP);
		hacks.put(new Overlay(), GLFW.GLFW_KEY_LEFT_BRACKET);
		hacks.put(new Sprint(), GLFW.GLFW_KEY_EQUAL);
		hacks.put(new Tracers(), GLFW.GLFW_KEY_DOWN);
		hacks.put(new Trajectory(), GLFW.GLFW_KEY_BACKSLASH);
	}
	
	public static HackManager getInstance() {
		return hm;
	}
	
	public ArrayList<Hack> getHacks() {
		return new ArrayList<>(this.hacks.keySet());
	}

	public ArrayList<Hack> getHacksByStatus(boolean status, ArrayList<Hack> hacks) {
		ArrayList<Hack> outputHacks = new ArrayList<>();

		for(Hack hack: hacks) {
			if (hack.getStatus() == status) {
				outputHacks.add(hack);
			}
		}
		return outputHacks;
	}

	public ArrayList<Hack> getHacksByStatus(boolean status) {
		return getHacksByStatus(status, getHacks());
	}

	public ArrayList<Hack> getHacksByStatus() {
		return getHacksByStatus(true);
	}

	public ArrayList<Hack> getHacksByCategory(Hack.Category category, ArrayList<Hack> hacks) {
		ArrayList<Hack> outputHacks = new ArrayList<>();

		for(Hack hack: hacks) {
			if (hack.getCategory() == category) {
				outputHacks.add(hack);
			}
		}
		return outputHacks;
	}

	public ArrayList<Hack> getHacksByCategory(Hack.Category category) {
		return getHacksByCategory(category, getHacks());
	}

	@SubscribeEvent(receiveCanceled=true)
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		for(Map.Entry<Hack, Integer> hack: hacks.entrySet()) {
			if (event.getAction() == 1) {
				if (YawnsMod.yawnsgui.isPressed()) {
					YawnsMod.LOGGER.info("I'll implement a GUI *some*day...");
				} else if (event.getKey() == hack.getValue()) {
					hack.getKey().toggleStatus();
				}
			}
		}
	}
}