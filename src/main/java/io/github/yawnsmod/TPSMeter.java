package io.github.yawnsmod;

import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalTime;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;

public class TPSMeter extends Gui
{
    public TPSMeter(Minecraft mc, RenderGameOverlayEvent.Post event)
    {
        ScaledResolution res = event.getResolution();
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
    	
        /* Top-left text */                                                        /* Top-right text */
    	ArrayList<String> tl = new ArrayList<String>(); ArrayList<String> tr = new ArrayList<String>();
    	/* Bottom-left text */                                                  /* Bottom-right text */
    	ArrayList<String> bl = new ArrayList<String>(); ArrayList<String> br = new ArrayList<String>();
    	
    	tl.add(TextFormatting.GOLD+""+ TextFormatting.UNDERLINE+"YAWNS Mod"+TextFormatting.RESET+" "+TextFormatting.DARK_GREEN+""+TextFormatting.ITALIC+"(in-dev garbage edition)"+TextFormatting.RESET);
    	tl.add(String.format("%d FPS", Minecraft.getDebugFPS()));
    	tl.add(String.format("Biome: %s", mc.player.world.getBiomeForCoordsBody(mc.player.getPosition()).getBiomeName()));
    	
    	long time = mc.world.getWorldTime();
    	bl.add(String.format("%d (%2$tI:%2$tM %2$Tp)", time, LocalTime.MIN.plus(Duration.ofMinutes((long) ((3.0/50.0)*(time + 6000))))));
    	bl.add(String.format("Saturation: %.1f/20.0", mc.player.getFoodStats().getSaturationLevel())); // Todo make this above the hunger bar in a different color
    	int ping = -1;
    	try {
    		ping = mc.player.connection.getPlayerInfo(mc.player.getGameProfile().getId()).getResponseTime();
    	} catch (NullPointerException err) {
    		;
    	}
    	bl.add(String.format("Ping: %d", ping));
    	
    	tr.add(TextFormatting.STRIKETHROUGH + "Totally real mod" + TextFormatting.RESET);
    	tr.add(TextFormatting.STRIKETHROUGH + "Also real thing" + TextFormatting.RESET);
    	
    	if (mc.player.dimension != -1) {
	    	br.add(String.format("Nether: %,.3f %,.5f %,.3f", mc.player.posX/8, mc.player.posY, mc.player.posZ/8));
	    	br.add(String.format("Overworld: %,.3f %,.5f %,.3f", mc.player.posX, mc.player.posY, mc.player.posZ));
    	} else {
    		br.add(String.format("Overworld: %,.3f %,.5f %,.3f", mc.player.posX*8, mc.player.posY, mc.player.posZ*8));
    		br.add(String.format("Nether: %,.3f %,.5f %,.3f", mc.player.posX, mc.player.posY, mc.player.posZ));
    	}
    	
    	String axis;
    	switch (mc.player.getHorizontalFacing()) {
    		case NORTH:
    			axis = "-Z";
    			break;
    		case SOUTH:
    			axis = "+Z";
    			break;
    		case EAST:
    			axis = "+X";
    			break;
    		case WEST:
    			axis = "-X";
    			break;
    		default:
    			axis = "?";
    			break;
    	}
    	br.add(String.format("Facing: %s (%.2f/%.2f)", axis, MathHelper.wrapDegrees(mc.player.rotationYaw), MathHelper.wrapDegrees(mc.player.rotationPitch)));
    	Vec3d eyepos = mc.player.getPositionEyes(event.getPartialTicks());
    	RayTraceResult lookedatblock = mc.world.rayTraceBlocks(eyepos, eyepos.add(mc.player.getLook(event.getPartialTicks()).scale(1024)), true, false, false);
    	String lookedatblockstring = "None";
    	if (lookedatblock != null && lookedatblock.typeOfHit == RayTraceResult.Type.BLOCK)
    	{
    		lookedatblockstring = mc.world.getBlockState(lookedatblock.getBlockPos()).getBlock().getLocalizedName();
    	}
    	br.add(String.format("Looking at: %s", lookedatblockstring));
    	
    	for (int i = 0; i < tl.size(); i++) {
    		drawString(mc.fontRenderer, tl.get(i), 2, mc.fontRenderer.FONT_HEIGHT*i + 2*i + 2, Integer.parseInt("FFFFFF", 16));
    	}
    	for (int i = 0; i < bl.size(); i++) {
    		drawString(mc.fontRenderer, bl.get(i), 2, height - (mc.fontRenderer.FONT_HEIGHT*(i+1) + 2*i + 2), Integer.parseInt("FFFFFF", 16));
    	}
    	for (int i = 0; i < tr.size(); i++) {
    		drawString(mc.fontRenderer, tr.get(i), width - mc.fontRenderer.getStringWidth(tr.get(i)) - 2, mc.fontRenderer.FONT_HEIGHT*i + 2*i + 2, Integer.parseInt("FFFFFF", 16));
    	}
    	for (int i = 0; i < br.size(); i++) {
    		drawString(mc.fontRenderer, br.get(i), width - mc.fontRenderer.getStringWidth(br.get(i)) - 2, height - (mc.fontRenderer.FONT_HEIGHT*(i+1) + 2*i + 2), Integer.parseInt("FFFFFF", 16));
    	}
    }
}