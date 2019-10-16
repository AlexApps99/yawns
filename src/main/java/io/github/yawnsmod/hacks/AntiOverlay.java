package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity; // Hides fog/blindness effect
import net.minecraftforge.client.event.RenderBlockOverlayEvent; // Hides fire overlay, water texture overlay
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AntiOverlay extends Hack {
	private boolean renderHelmet = true;
	private boolean renderPortal = true;
	private boolean renderBossHealth = true;
	private boolean renderObjective = true;

	public AntiOverlay() {
		super("Anti-Overlay", "Hides pesky overlays and distractions", Hack.Category.DISPLAY, true);
	}
	
	@SubscribeEvent
	public void onFogDensity(FogDensity event) {
		// Todo fix buggy behaviour like different effect activating underwater to out of water
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
		}
	}

	@Override
	public void onEnabled() {
		renderHelmet = GuiIngameForge.renderHelmet;
		renderPortal = GuiIngameForge.renderPortal;
		renderBossHealth = GuiIngameForge.renderBossHealth;
		renderObjective = GuiIngameForge.renderObjective;

		GuiIngameForge.renderHelmet = false;
		GuiIngameForge.renderPortal = false;
		GuiIngameForge.renderBossHealth = false;
		GuiIngameForge.renderObjective = false;
	}

	@Override
	public void onDisabled() {
		if (GuiIngameForge.renderHelmet == false) {
			GuiIngameForge.renderHelmet = renderHelmet;
		}
		if (GuiIngameForge.renderPortal == false) {
			GuiIngameForge.renderPortal = renderPortal;
		}
		if (GuiIngameForge.renderBossHealth == false) {
			GuiIngameForge.renderBossHealth = renderBossHealth;
		}
		if (GuiIngameForge.renderObjective == false) {
			GuiIngameForge.renderObjective = renderObjective;
		}
	}

	// Todo disable nausea, see GameRenderer.setupCameraTransform(), probably do the inverse of those effects to undo it
}