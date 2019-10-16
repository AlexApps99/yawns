package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class Tracers extends Hack {
    public Tracers() {
        super("Tracers", "Shows where the players are", Hack.Category.DISPLAY, false);
    }

    private <T extends Entity> void drawEntityLine(T e, float width, float r, float g, float b, float a) {
        // todo fix line width not always being correct
        //GL11.glLineWidth(width);
        GL11.glColor4f(r, g, b, a);
        GL11.glVertex3d(0, 0, 0);
        GL11.glVertex3d(e.posX - mc.player.posX, e.posY - mc.player.posY, e.posZ - mc.player.posZ);
    }

    private float distanceFadeClamp(float currentDistance, float startValue, float endValue, float endDistance) {
        float distance = -(endValue/endDistance) * currentDistance + startValue;
        if (distance < endValue) {
            distance = endValue;
        }
        return distance;
    }

    // https://www.minecraftforge.net/forum/topic/33464-18solved-drawing-a-simple-line/?do=findComment&comment=177754
    @SubscribeEvent
    public void afterRenderWorld(RenderWorldLastEvent event) {
        // Saves previous state of OpenGL
        // khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glPushMatrix.xml
        // khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glPushAttrib.xml
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);

        // Configures attributes
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST); // Makes tracer draw on top of everything
        // Line appearance settings
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);

        GL11.glBegin(GL11.GL_LINES); // khronos.org/opengl/wiki/Primitive#Line_primitives
        for (Entity e: mc.world.getEntities(EntityLiving.class, (e) -> e.isCreatureType(EnumCreatureType.MONSTER, false) || e.hasCustomName())) {
            drawEntityLine(e, 3, (float) e.posX % 2, (float) e.posX % 2, (float) e.posX % 2, distanceFadeClamp(e.getDistance(mc.player), 1, 0.25f, 32));
        }

        for (Entity e: mc.world.getEntities(EntityOtherPlayerMP.class, (e) -> true)) {
            // It works, but I should probably hide fake players e.g. game selectors in hypixel lobby
            drawEntityLine(e, 3, (float) e.posX % 2, (float) e.posX % 2, (float) e.posX % 2, distanceFadeClamp(e.getDistance(mc.player), 1, 0.25f, 32));
        }
        GL11.glEnd();

        // Restores previous state of OpenGL
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}