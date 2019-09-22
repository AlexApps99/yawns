package io.github.yawnsmod;

import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Hack {
	private String name;
	private String tooltip;
	private Category category;
	private int keycode;
	
	public Hack(String name, String tooltip, Hack.Category category, int keycode) {
		this.name = name;
		this.tooltip = tooltip;
		this.category = category;
		this.keycode = keycode;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTooltip() {
		return tooltip;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public int getKeycode() {
		return keycode;
	}
	
	public enum Category {
		CHAT,
		DISPLAY,
		MOVEMENT
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void onKeyInput(KeyInputEvent event) {
		if (event.getKey() == keycode && event.getAction() == 1) {
			YawnsMod.hm.toggle(this);
		}
	}
}