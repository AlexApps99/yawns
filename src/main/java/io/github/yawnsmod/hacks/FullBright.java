package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;

public class FullBright extends Hack {
    private double gammaSetting = 1;

    public FullBright() {
        super("Full Bright", "It's like pointing lasers in your eyes", Hack.Category.DISPLAY, false);
    }

    @Override
    public void onEnabled() {
        gammaSetting = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 15;
    }

    @Override
    public void onDisabled() {
        if (mc.gameSettings.gammaSetting == 15) {
            mc.gameSettings.gammaSetting = gammaSetting;
        }
    }
}
