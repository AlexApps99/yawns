package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Sprint extends Hack {
	public Sprint() {
		super("Sprint", "Gotta go fast", Hack.Category.MOVEMENT, false);
	}

	@SubscribeEvent(receiveCanceled=true)
	public void onInputUpdate(InputUpdateEvent event) {
		if (event.getMovementInput().moveForward > 0) {
			event.getEntityPlayer().setSprinting(true);
		}
	}
}
