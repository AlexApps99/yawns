package io.github.yawnsmod.hacks;

import org.lwjgl.glfw.GLFW;

import io.github.yawnsmod.Hack;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Sprint extends Hack {
	public Sprint() {
		super("Sprint", "Gotta go fast", Hack.Category.MOVEMENT, GLFW.GLFW_KEY_MINUS);
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void onInputUpdate(InputUpdateEvent event) {
		if (event.getMovementInput().moveForward > 0) {
			event.getEntityPlayer().setSprinting(true);
		}
	}
}
