package io.github.yawnsmod;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import io.github.yawnsmod.Hack;
import io.github.yawnsmod.hacks.AntiOverlay;
import io.github.yawnsmod.hacks.FancyChat;
import io.github.yawnsmod.hacks.Overlay;
import io.github.yawnsmod.hacks.Sprint;

public class HackManager {
	public Map<Hack, Boolean> hacks = new HashMap<Hack, Boolean>();
	
	public HackManager() {
		hacks.put(new AntiOverlay(), true);
		hacks.put(new FancyChat(), true);
		hacks.put(new Overlay(), true);
		hacks.put(new Sprint(), true);
	}
	
	public static HackManager getInstance() {
		return YawnsMod.hm;
	}
	
	public ArrayList<Hack> getHacks() {
		ArrayList<Hack> hacks = new ArrayList<Hack>();
		
		for(Map.Entry<Hack, Boolean> hack : this.hacks.entrySet()) {
			hacks.add(hack.getKey());
		}
		return hacks;
	}
	
	public ArrayList<Hack> getEnabledHacks() {
		ArrayList<Hack> enabledHacks = new ArrayList<Hack>();
		
		for(Map.Entry<Hack, Boolean> hack : hacks.entrySet()) {
			if (hack.getValue()) {
				enabledHacks.add(hack.getKey());
			}
		}
		return enabledHacks;
	}
	
	public void toggle(Hack hack) {
		hacks.put(hack, !hacks.get(hack));
	}
	
	public void enable(Hack hack) {
		hacks.put(hack, true);
	}
	
	public void disable(Hack hack) {
		hacks.put(hack, false);
	}
	
	public boolean getEnabled(Hack hack) {
		return hacks.get(hack);
	}
}