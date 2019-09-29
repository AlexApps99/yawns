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
	
	@SubscribeEvent(receiveCanceled=true)
	public void onFogDensity(FogDensity event) { // This may cause rendering bugs, possibly try RenderFogEvent
		event.setDensity(event.getDensity()*0);
		event.setCanceled(true);
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
		switch (event.getOverlayType()) {
		case FIRE:
		case BLOCK:
		case WATER:
			event.setCanceled(true);
			break;
		default:
			break;
		}
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void beforeRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
		switch (event.getType()) {
		case HELMET:
		case PORTAL:
			event.setCanceled(true);
			break;
		default:
			break;
		}
	}
}