package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class AdvancedTooltip extends Hack {
    private boolean advancedItemTooltips = false;

    public AdvancedTooltip() {
        super("Advanced Tooltip", "Feel like hackerman!", Hack.Category.DISPLAY, false);
    }

    @Override
    public void onEnabled() {
        advancedItemTooltips = mc.gameSettings.advancedItemTooltips;
        mc.gameSettings.advancedItemTooltips = true;
    }

    @Override
    public void onDisabled() {
        if (mc.gameSettings.advancedItemTooltips) {
            mc.gameSettings.advancedItemTooltips = advancedItemTooltips;
        }
    }

    @SubscribeEvent
    public void onRenderTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().getTag() != null) {
            event.getToolTip().add(event.getItemStack().getTag().toFormattedComponent());
        }
    }
}