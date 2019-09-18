package io.github.yawnsmod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity; // Hides fog/blindness effect
import net.minecraftforge.client.event.RenderBlockOverlayEvent; // Hides fire overlay, water texture overlay
import net.minecraftforge.client.event.RenderGameOverlayEvent; // Hides helmet overlay, portal swirl overlay, shows my overlay
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TestOverlay
{
	@SubscribeEvent(receiveCanceled=true)
	public void onFogDensity(FogDensity event) // This may cause rendering bugs, possibly try RenderFogEvent
	{
		event.setDensity(0);
		event.setCanceled(true);
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void onRenderBlockOverlay(RenderBlockOverlayEvent event)
	{
		switch (event.getOverlayType()) {
		case FIRE:
			event.setCanceled(true);
			break;
		case BLOCK:
			event.setCanceled(true);
			break;
		case WATER:
			event.setCanceled(true);
			break;
		default:
			break;
		}
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void beforeRenderGameOverlay(RenderGameOverlayEvent.Pre event)
	{
		switch (event.getType()) {
		case HELMET:
			event.setCanceled(true);
			break;
		case PORTAL:
			event.setCanceled(true);
			break;
		case POTION_ICONS:
			GL11.glPushMatrix();
			GL11.glTranslated(0, 0, 0); // Relocate hacks list to below potion icons when potion effects are active
			break;
		default:
			break;
		}
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void afterRenderGameOverlay(RenderGameOverlayEvent.Post event)
	{
		switch (event.getType()) {
		case ALL:
			new TPSMeter(Minecraft.getMinecraft(), event);
			break;
		case POTION_ICONS:
			GL11.glPopMatrix();
		default:
			break;
		}
	}
}