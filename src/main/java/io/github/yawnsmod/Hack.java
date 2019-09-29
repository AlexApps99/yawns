package io.github.yawnsmod;

import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Hack {
	private String name = "";
	private String tooltip = "";
	private Category category = Category.NONE;
	private boolean status = false;
	
	public Hack(String name, String tooltip, Hack.Category category, boolean status) {
		this.name = name;
		this.tooltip = tooltip;
		this.category = category;
		this.status = status;
	}

	public Hack(String name, String tooltip, boolean status) {
		this.name = name;
		this.tooltip = tooltip;
		this.status = status;
	}

	public Hack(String name, String tooltip) {
		this.name = name;
		this.tooltip = tooltip;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean toggleStatus() {
		return status = !status;
	}
	
	public enum Category {
		CHAT,
		DISPLAY,
		MOVEMENT,
		NONE
	}
}