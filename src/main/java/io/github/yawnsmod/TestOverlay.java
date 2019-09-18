package io.github.yawnsmod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TestOverlay
{
	@SubscribeEvent(receiveCanceled=true)
	public void beforeRenderGui(RenderGameOverlayEvent.Pre event)
	{
		switch (event.getType()) {
		case HELMET:
			event.setCanceled(true);
			break;
		case PORTAL:
			event.setCanceled(true);
			break;
		default:
			break;
		}
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void beforeRenderBlockOverlay(RenderBlockOverlayEvent event)
	{
		switch (event.getOverlayType()) {
		case FIRE:
			event.setCanceled(true);
			break;
		//case BLOCK:
		//	event.setCanceled(true);
		//	break;
		case WATER:
			event.setCanceled(true);
			break;
		default:
			break;
		}
	}
	
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event)
	{
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			new TPSMeter(Minecraft.getMinecraft(), event);
		}
	}
}