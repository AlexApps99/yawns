package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity; // Hides fog/blindness effect
import net.minecraftforge.client.event.RenderBlockOverlayEvent; // Hides fire overlay, water texture overlay
import net.minecraftforge.client.event.RenderGameOverlayEvent; // Hides helmet overlay, portal swirl overlay, shows my overlay
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AntiOverlay extends Hack {
	public AntiOverlay() {
		super("Anti-Overlay", "Hides pesky overlays and distractions", Hack.Category.DISPLAY, true);
	}
	
	@SubscribeEvent
	public void onFogDensity(FogDensity event) {
		// Todo fix buggy behaviour like tracers being hidden, different effect activating underwater to out of water
		event.setDensity(event.getDensity() * 0); // For if it should be disabled incrementally
		// Cancels fog rendering
		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
		switch (event.getOverlayType()) {
			case FIRE:
			case BLOCK:
			case WATER:
				// Cancels rendering of fire overlay, stuck in block overlay, underwater overlay
				event.setCanceled(true);
				break;
		}
	}
	
	@SubscribeEvent
	public void beforeRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
		switch (event.getType()) {
			//case BOSSHEALTH:
			case HELMET:
			case PORTAL:
				// Cancels helmet rendering (eg pumpkin) and portal swirl rendering (not to be confused with nausea)
				event.setCanceled(true);
				break;
		}
	}

	@SubscribeEvent
	public void beforeRenderGameOverlayText(RenderGameOverlayEvent.Pre.Text event) {
		// Hides text onscreen (needs further testing, optional option
		//event.setCanceled(true);
	}
}