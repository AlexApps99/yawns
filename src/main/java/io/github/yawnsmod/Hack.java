package io.github.yawnsmod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

// todo This should be implementable, not extendable...
// http://tutorials.jenkov.com/java/interfaces.html#interface-default-methods
public class Hack {
	private String name = "";
	private String tooltip = "";
	private Category category = Category.NONE;
	private boolean status = false;
	protected final Minecraft mc = Minecraft.getInstance();
	protected final Logger LOGGER = YawnsMod.LOGGER;

	public Hack(String name, String tooltip, Hack.Category category, boolean status) {
		this.name = name;
		this.tooltip = tooltip;
		this.category = category;
		setStatus(status);
	}

	public Hack(String name, String tooltip, boolean status) {
		this(name, tooltip, Category.NONE, status);
	}

	public Hack(String name, String tooltip) {
		this(name, tooltip, false);
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

	public boolean setStatus(boolean status) {
		if (this.status != status) {
			if (this.status = status) {
				this.onEnabled();
				MinecraftForge.EVENT_BUS.register(this);
			} else {
				MinecraftForge.EVENT_BUS.unregister(this);
				this.onDisabled();
			}
		}

		return status;
	}

	public boolean toggleStatus() {
		return setStatus(!this.status);
	}

	public void onEnabled() {}

	public void onDisabled() {}
	
	public enum Category {
		CHAT,
		DISPLAY,
		MOVEMENT,
		NONE
	}
}