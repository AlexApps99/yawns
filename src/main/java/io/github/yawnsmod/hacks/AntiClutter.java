package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AntiClutter extends Hack {
    public AntiClutter() {
        super("Anti-Clutter", "Hides boss bar and scoreboard", Hack.Category.DISPLAY, false);
    }

    @SubscribeEvent
    public void beforeRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        // todo Seems to mess up health rendering
        if (event.getType() == RenderGameOverlayEvent.ElementType.BOSSHEALTH) {
            event.setCanceled(true);
        }
        //mc.world.getScoreboard() // might be useful
    }
}