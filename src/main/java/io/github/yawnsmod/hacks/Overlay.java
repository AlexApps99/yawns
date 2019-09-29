package io.github.yawnsmod.hacks;

import java.util.ArrayList;

import io.github.yawnsmod.Hack;
import io.github.yawnsmod.YawnsMod;

import java.time.Duration;
import java.time.LocalTime;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceFluidMode;

public class Overlay extends Hack {
	public Overlay() {
		super("Overlay", "Overlays information on your screen", Hack.Category.DISPLAY, true);
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void afterRenderGameOverlay(RenderGameOverlayEvent.Post event) {
    	/*                                                                                              *\
    	  Todo:
    	  - Add TPS
    	  - Add armor enchantments + durability (possibly using icons, possibly using text)
    	  - Add held item enchantments + durability (possibly using icons, possibly using text)
    	  - Add potion effects, duration, level (overlayed over icons or as text without icons onscreen)
    	\*                                                                                              */
    	
		switch (event.getType()) {
		case ALL:
			Minecraft mc = Minecraft.getInstance();
			
	        int width = mc.mainWindow.getScaledWidth();
	        int height = mc.mainWindow.getScaledHeight();
	    	
	        /* Top-left text */                                                        /* Top-right text */
	    	ArrayList<String> tl = new ArrayList<String>(); ArrayList<String> tr = new ArrayList<String>();
	    	/* Bottom-left text */                                                  /* Bottom-right text */
	    	ArrayList<String> bl = new ArrayList<String>(); ArrayList<String> br = new ArrayList<String>();
	    	
	    	tl.add(TextFormatting.GOLD+""+ TextFormatting.UNDERLINE+"YAWNS Mod"+TextFormatting.RESET+" "+TextFormatting.DARK_GREEN+""+TextFormatting.ITALIC+"(in-dev garbage edition)"+TextFormatting.RESET);
	    	tl.add(String.format("%d FPS", Minecraft.getDebugFPS()));
	    	tl.add(String.format("Biome: %s", mc.player.world.getBiomeBody(mc.player.getPosition()).getDisplayName().getUnformattedComponentText()));
	    	
	    	long time = mc.world.getDayTime();
	    	bl.add(String.format("%d (%2$tI:%2$tM %2$Tp)", time, LocalTime.MIN.plus(Duration.ofMinutes((long) ((3.0/50.0)*(time + 6000))))));
	    	bl.add(String.format("Saturation: %.1f/20.0", mc.player.getFoodStats().getSaturationLevel()));
	    	int ping = -1;
	    	try {
	    		ping = mc.player.connection.getPlayerInfo(mc.player.getGameProfile().getId()).getResponseTime();
	    	} catch (NullPointerException err) {
	    		;
	    	}
	    	bl.add(String.format("Ping: %d", ping));
	    	
	    	for (Hack hack : YawnsMod.hm.getEnabledHacks()) {
	    		tr.add(hack.getName());
	    	}
	    	
	    	if (mc.player.dimension != DimensionType.NETHER) {
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
	    	Vec3d eyepos = mc.player.getEyePosition(event.getPartialTicks());
	    	RayTraceResult lookedatblock = mc.world.rayTraceBlocks(eyepos, eyepos.add(mc.player.getLook(event.getPartialTicks()).scale(1024)), RayTraceFluidMode.SOURCE_ONLY, false, false);
	    	String lookedatblockstring = "None";
	    	if (lookedatblock != null && lookedatblock.type == RayTraceResult.Type.BLOCK) {
	    		lookedatblockstring = mc.world.getBlockState(lookedatblock.getBlockPos()).getBlock().getNameTextComponent().getUnformattedComponentText();
	    	}
	    	br.add(String.format("Looking at: %s", lookedatblockstring));
	    	
	    	for (int i = 0; i < tl.size(); i++) {
	    		mc.fontRenderer.drawStringWithShadow(tl.get(i), 2, mc.fontRenderer.FONT_HEIGHT*i + 2*i + 2, Integer.parseInt("FFFFFF", 16));
	    	}
	    	for (int i = 0; i < bl.size(); i++) {
	    		mc.fontRenderer.drawStringWithShadow(bl.get(i), 2, height - (mc.fontRenderer.FONT_HEIGHT*(i+1) + 2*i + 2), Integer.parseInt("FFFFFF", 16));
	    	}
	    	for (int i = 0; i < tr.size(); i++) {
	    		mc.fontRenderer.drawStringWithShadow(tr.get(i), width - mc.fontRenderer.getStringWidth(tr.get(i)) - 2, mc.fontRenderer.FONT_HEIGHT*i + 2*i + 2, Integer.parseInt("FFFFFF", 16));
	    	}
	    	for (int i = 0; i < br.size(); i++) {
	    		mc.fontRenderer.drawStringWithShadow(br.get(i), width - mc.fontRenderer.getStringWidth(br.get(i)) - 2, height - (mc.fontRenderer.FONT_HEIGHT*(i+1) + 2*i + 2), Integer.parseInt("FFFFFF", 16));
	    	}
	    	break;
	    default:
	    	break;
		}
    }
}