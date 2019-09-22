package io.github.yawnsmod;

import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyBindHandler {
	@SubscribeEvent(receiveCanceled=true)
	public void onKeyInput(KeyInputEvent event) {
		if (YawnsMod.yawnsgui.isPressed()) {
            YawnsMod.LOGGER.info("Heyy");
        }
	}
}