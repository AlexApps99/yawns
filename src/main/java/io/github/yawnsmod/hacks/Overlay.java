package io.github.yawnsmod.hacks;

import java.util.ArrayList;

import io.github.yawnsmod.Hack;
import io.github.yawnsmod.HackManager;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

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
	
	@SubscribeEvent
	public void afterRenderGameOverlay(RenderGameOverlayEvent.Post event) {
    	/*                                                                                              *\
    	  Todo:
    	  - Add TPS
    	  - Add armor enchantments + durability (possibly using icons, possibly using text)
    	  - Add held item enchantments + durability (possibly using icons, possibly using text)
    	  - Add potion effects, duration, level (overlayed over icons or as text without icons onscreen)
    	  - Move mod list down as to not overlap the potion icons (only when they're visible)
    	  - Put all things into en_us.json to be localized
    	  - Handle as textcomponents instead of strings
    	\*                                                                                              */
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) { // How often does this get called per frame?
			final int width = mc.mainWindow.getScaledWidth();
			final int height = mc.mainWindow.getScaledHeight();

			// Top-left text
			final List<String> tl = new ArrayList<>();
			// Top-right text
			final List<String> tr = new ArrayList<>();
			// Bottom-left text
			final List<String> bl = new ArrayList<>();
			// Bottom-right text
			final List<String> br = new ArrayList<>();

			long time = mc.world.getDayTime();
			int ping = -1;
			String axis = "??";
			Vec3d eyepos = mc.player.getEyePosition(event.getPartialTicks());
			RayTraceResult lookedatblock = mc.world.rayTraceBlocks(eyepos, eyepos.add(mc.player.getLook(event.getPartialTicks()).scale(1024)), RayTraceFluidMode.SOURCE_ONLY);

	    	tl.add(TextFormatting.GOLD+""+ TextFormatting.UNDERLINE+"YAWNS Mod"+TextFormatting.RESET+" "+TextFormatting.DARK_GREEN+""+TextFormatting.ITALIC+"(in-dev garbage edition)"+TextFormatting.RESET);
	    	tl.add(String.format("%d FPS", Minecraft.getDebugFPS()));
	    	tl.add(String.format("Biome: %s", mc.player.world.getBiomeBody(mc.player.getPosition()).getDisplayName().getUnformattedComponentText()));

			try {
				ping = mc.player.connection.getPlayerInfo(mc.player.getGameProfile().getId()).getResponseTime();
			} catch (NullPointerException err) {}
			bl.add(String.format("Ping: %d", ping));
			bl.add(String.format("Health: %.1f/%.1f", mc.player.getHealth(), mc.player.getMaxHealth()));
			bl.add(String.format("Saturation: %.1f/20.0", mc.player.getFoodStats().getSaturationLevel()));
	    	bl.add(String.format("%d (%2$tI:%2$tM %2$Tp)", time, LocalTime.MIN.plus(Duration.ofMinutes((long) ((3.0/50.0)*(time + 6000))))));
	    	
	    	for (Hack hack: HackManager.getInstance().getHacksByStatus()) {
	    		tr.add(hack.getName());
	    	}

			if (lookedatblock != null && lookedatblock.type == RayTraceResult.Type.BLOCK) {
				br.add(String.format("Looking at: %s", mc.world.getBlockState(lookedatblock.getBlockPos()).getBlock().getNameTextComponent().getUnformattedComponentText()));
			} else {
				br.add("Looking at: Nothing");
			}

			switch (mc.player.getHorizontalFacing()) {
				case NORTH:
					axis = "North -Z";
					break;
				case EAST:
					axis = "East +X";
					break;
				case SOUTH:
					axis = "South +Z";
					break;
				case WEST:
					axis = "West -X";
					break;
			}
			br.add(String.format("Facing %s (%.2f/%.2f)", axis, MathHelper.wrapDegrees(mc.player.rotationYaw), MathHelper.wrapDegrees(mc.player.rotationPitch)));
	    	
	    	if (mc.player.dimension != DimensionType.NETHER) {
				br.add(String.format("Nether: %,.3f %,.5f %,.3f", mc.player.posX/8, mc.player.posY, mc.player.posZ/8));
		    	br.add(String.format("Overworld: %,.3f %,.5f %,.3f", mc.player.posX, mc.player.posY, mc.player.posZ));
	    	} else {
				br.add(String.format("Overworld: %,.3f %,.5f %,.3f", mc.player.posX*8, mc.player.posY, mc.player.posZ*8));
                br.add(String.format("Nether: %,.3f %,.5f %,.3f", mc.player.posX, mc.player.posY, mc.player.posZ));
	    	}
	    	
	    	for (int i = 0; i < tl.size(); i++) {
	    		mc.fontRenderer.drawStringWithShadow(tl.get(i), 2, mc.fontRenderer.FONT_HEIGHT*i + 2*i + 2, 0xFFFFFF);
	    	}
	    	for (int i = 0; i < bl.size(); i++) {
	    		mc.fontRenderer.drawStringWithShadow(bl.get(bl.size()-1-i), 2, height - (mc.fontRenderer.FONT_HEIGHT*(i+1) + 2*i + 2), 0xFFFFFF);
	    	}
	    	for (int i = 0; i < tr.size(); i++) {
	    		mc.fontRenderer.drawStringWithShadow(tr.get(i), width - mc.fontRenderer.getStringWidth(tr.get(i)) - 2, mc.fontRenderer.FONT_HEIGHT*i + 2*i + 2, 0xFFFFFF);
	    	}
	    	for (int i = 0; i < br.size(); i++) {
	    		mc.fontRenderer.drawStringWithShadow(br.get(br.size()-1-i), width - mc.fontRenderer.getStringWidth(br.get(br.size()-1-i)) - 2, height - (mc.fontRenderer.FONT_HEIGHT*(i+1) + 2*i + 2), 0xFFFFFF);
	    	}
		}
    }
}