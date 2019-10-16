package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraftforge.client.GuiIngameForge;

public class FullBright extends Hack {
    private double gammaSetting = 1;
    private boolean renderVignette = true;

    public FullBright() {
        super("Full Bright", "It's like pointing lasers in your eyes", Hack.Category.DISPLAY, false);
    }

    @Override
    public void onEnabled() {
        gammaSetting = mc.gameSettings.gammaSetting;
        renderVignette = GuiIngameForge.renderVignette;
        mc.gameSettings.gammaSetting = 15;
        GuiIngameForge.renderVignette = false;
    }

    @Override
    public void onDisabled() {
        if (mc.gameSettings.gammaSetting == 15) {
            mc.gameSettings.gammaSetting = gammaSetting;
        }
        if (GuiIngameForge.renderVignette == false) {
            GuiIngameForge.renderVignette = renderVignette;
        }
    }
}
