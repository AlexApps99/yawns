package io.github.yawnsmod.hacks;

import java.util.ArrayList;

import io.github.yawnsmod.Hack;
import io.github.yawnsmod.HackManager;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
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

	    	tl.add(I18n.format("yawnsmod.hacks.overlay.title", TextFormatting.GOLD, TextFormatting.UNDERLINE, TextFormatting.RESET, TextFormatting.DARK_GREEN, TextFormatting.ITALIC));
	    	tl.add(I18n.format("yawnsmod.hacks.overlay.fps", Minecraft.getDebugFPS()));
	    	tl.add(I18n.format("yawnsmod.hacks.overlay.biome", mc.player.world.getBiomeBody(mc.player.getPosition()).getDisplayName().getUnformattedComponentText()));

			try {
				ping = mc.player.connection.getPlayerInfo(mc.player.getGameProfile().getId()).getResponseTime();
			} catch (NullPointerException err) {}
			bl.add(I18n.format("yawnsmod.hacks.overlay.ping", ping));
			bl.add(I18n.format("yawnsmod.hacks.overlay.health", mc.player.getHealth(), mc.player.getMaxHealth()));
			bl.add(I18n.format("yawnsmod.hacks.overlay.saturation", mc.player.getFoodStats().getSaturationLevel()));
	    	bl.add(I18n.format("yawnsmod.hacks.overlay.time", time, LocalTime.MIN.plus(Duration.ofMinutes((long) ((3.0/50.0)*(time + 6000))))));
	    	
	    	for (Hack hack: HackManager.getInstance().getHacksByStatus()) {
	    		tr.add(hack.getName());
	    	}

			if (lookedatblock != null && lookedatblock.type == RayTraceResult.Type.BLOCK) {
				br.add(I18n.format("yawnsmod.hacks.overlay.block", mc.world.getBlockState(lookedatblock.getBlockPos()).getBlock().getNameTextComponent().getUnformattedComponentText()));
			} else {
				br.add(I18n.format("yawnsmod.hacks.overlay.block", I18n.format("block.minecraft.air")));
			}

			switch (mc.player.getHorizontalFacing()) {
				case NORTH:
					axis = I18n.format("yawnsmod.hacks.overlay.north");
					break;
				case EAST:
					axis = I18n.format("yawnsmod.hacks.overlay.east");
					break;
				case SOUTH:
					axis = I18n.format("yawnsmod.hacks.overlay.south");
					break;
				case WEST:
					axis = I18n.format("yawnsmod.hacks.overlay.west");
					break;
			}
			br.add(I18n.format("yawnsmod.hacks.overlay.facing", axis, MathHelper.wrapDegrees(mc.player.rotationYaw), MathHelper.wrapDegrees(mc.player.rotationPitch)));
	    	
	    	if (mc.player.dimension != DimensionType.NETHER) {
				br.add(I18n.format("yawnsmod.hacks.overlay.nether", mc.player.posX/8, mc.player.posY, mc.player.posZ/8));
		    	br.add(I18n.format("yawnsmod.hacks.overlay.overworld", mc.player.posX, mc.player.posY, mc.player.posZ));
	    	} else {
				br.add(I18n.format("yawnsmod.hacks.overlay.overworld", mc.player.posX*8, mc.player.posY, mc.player.posZ*8));
                br.add(I18n.format("yawnsmod.hacks.overlay.nether", mc.player.posX, mc.player.posY, mc.player.posZ));
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