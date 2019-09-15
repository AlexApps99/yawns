package io.github.yawnsmod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyBindHandler
{
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if (YawnsMod.yawnsgui.isPressed())
        {
            YawnsMod.logger.info("Heyy");
        }
	}
}