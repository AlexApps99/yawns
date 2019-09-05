package io.github.yawnsmod;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;

//import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.RayTraceResult;

public class TPSMeter extends Gui
{
    public TPSMeter(Minecraft mc)
    {
        ScaledResolution scaled = new ScaledResolution(mc);
        //int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        
    	DecimalFormat coords = new DecimalFormat(",##0.000");
    	drawString(mc.fontRenderer, String.join(" ", coords.format(mc.player.posX), coords.format(mc.player.posY), coords.format(mc.player.posZ)), 0, 0, Integer.parseInt("FFAA00", 16));
    	
    	long time = mc.world.getWorldTime();
    	LocalTime timeMins = LocalTime.MIN.plus(Duration.ofMinutes((long) ((double) (time + 6000)/(24000.0/1440))));
    	drawString(mc.fontRenderer, String.format("%d (%2$tI:%2$tM %2$Tp)", time, timeMins), 0, mc.fontRenderer.FONT_HEIGHT, Integer.parseInt("FFAA00", 16));
    	
    	RayTraceResult lookedatblock = mc.player.rayTrace(200, 1.0F);
    	if (lookedatblock != null && lookedatblock.typeOfHit == RayTraceResult.Type.BLOCK)
    	{
    		drawString(mc.fontRenderer, mc.world.getBlockState(lookedatblock.getBlockPos()).getBlock().getLocalizedName(), 0, height - mc.fontRenderer.FONT_HEIGHT, Integer.parseInt("FFAA00", 16));
    	}
    }
}