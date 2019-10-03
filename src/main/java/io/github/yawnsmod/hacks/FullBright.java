package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraft.client.Minecraft;

public class FullBright extends Hack {
    private double gammaSetting = 1;

    public FullBright() {
        super("Full Bright", "It's like pointing lasers in your eyes", Hack.Category.DISPLAY, false);
    }

    @Override
    public void onEnabled() {
        gammaSetting = Minecraft.getInstance().gameSettings.gammaSetting;
        Minecraft.getInstance().gameSettings.gammaSetting = 15;
    }

    @Override
    public void onDisabled() {
        Minecraft.getInstance().gameSettings.gammaSetting = gammaSetting;
    }
}
