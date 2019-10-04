package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class Tracers extends Hack {
    public Tracers() {
        super("Tracers", "Shows where the players are", Hack.Category.DISPLAY, false);
    }

    // https://www.minecraftforge.net/forum/topic/33464-18solved-drawing-a-simple-line/?do=findComment&comment=177754
    @SubscribeEvent
    public void afterRenderWorld(RenderWorldLastEvent event) {
        // todo use GlStateManager
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
        //GL11.glEnable(GL11.GL_LINE_SMOOTH);
        // relativity.net.au/gaming/java/Transparency.html
        //GL11.glEnable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        ///GL11.glColor4f(1F, 1F, 1F, 0.5F);
        GL11.glColor3f(1f, 0.5f, 0f);
        GL11.glLineWidth(2f);

        GL11.glBegin(GL11.GL_LINES); // khronos.org/opengl/wiki/Primitive#Line_primitives
        for (Entity entity: mc.world.getEntities(Entity.class, (e) -> e.hasCustomName() || e instanceof EntityOtherPlayerMP)) {
            // todo get this working
            GL11.glVertex3d(mc.player.posX, mc.player.posY, mc.player.posZ);
            GL11.glVertex3d(entity.posX, entity.posY, entity.posZ);
        }
        GL11.glEnd();

        // Restores previous state of OpenGL
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}