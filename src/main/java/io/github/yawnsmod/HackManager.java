package io.github.yawnsmod;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import io.github.yawnsmod.Hack;
import io.github.yawnsmod.hacks.AntiOverlay;
import io.github.yawnsmod.hacks.FancyChat;
import io.github.yawnsmod.hacks.Overlay;
import io.github.yawnsmod.hacks.Sprint;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.lwjgl.glfw.GLFW;

public class HackManager {
	public Map<Hack, Integer> hacks = new HashMap<Hack, Integer>();
	
	public HackManager() {
		hacks.put(new AntiOverlay(), GLFW.GLFW_KEY_RIGHT_BRACKET);
		hacks.put(new FancyChat(), GLFW.GLFW_KEY_MINUS);
		hacks.put(new Overlay(), GLFW.GLFW_KEY_LEFT_BRACKET);
		hacks.put(new Sprint(), GLFW.GLFW_KEY_EQUAL);
	}
	
	public static HackManager getInstance() {
		return YawnsMod.hm;
	}
	
	public ArrayList<Hack> getHacks() {
		ArrayList<Hack> hacks = new ArrayList<Hack>();
		
		for(Hack hack : this.hacks.keySet()) {
			hacks.add(hack);
		}
		return hacks;
	}
	
	public ArrayList<Hack> getEnabledHacks() {
		ArrayList<Hack> enabledHacks = new ArrayList<Hack>();
		
		for(Hack hack : this.hacks.keySet()) {
			if (hack.getStatus()) {
				enabledHacks.add(hack);
			}
		}
		return enabledHacks;
	}

	@SubscribeEvent(receiveCanceled=true)
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		for(Map.Entry<Hack, Integer> hack : hacks.entrySet()) {
			if (event.getKey() == hack.getValue() && event.getAction() == 1) {
				if (hack.getKey().toggleStatus()) {
					MinecraftForge.EVENT_BUS.register(hack.getKey());
				} else {
					MinecraftForge.EVENT_BUS.unregister(hack.getKey());
				}
			}
		}
	}
}