package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.*;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class Trajectory extends Hack {
    public Trajectory() {
        super("Trajectory", "Ever taken Physics class? Nope, me neither...", Category.DISPLAY, false);
    }

    // Huge thanks to Alexander01998 for his work on this in Wurst!
    // TODO Try to base on this: https://github.com/fr1kin/ForgeHax/blob/master/src/main/java/com/matt/forgehax/util/projectile/Projectile.java
    @SubscribeEvent
    public void afterRenderWorld(RenderWorldLastEvent event) {
        double yaw = Math.toRadians(mc.player.rotationYaw);
        double pitch = Math.toRadians(mc.player.rotationPitch);

        boolean usingBow;
        Item item = mc.player.inventory.getCurrentItem().getItem();

        if (!(
            (usingBow = item instanceof ItemBow)
            || item instanceof ItemSnowball
            || item instanceof ItemEgg
            || item instanceof ItemEnderPearl
            || item instanceof ItemSplashPotion
            || item instanceof ItemLingeringPotion
            || item instanceof ItemFishingRod
        )) {
            return;
        }

        // calculate starting position
        double arrowPosX = mc.player.lastTickPosX
                + (mc.player.posX - mc.player.lastTickPosX) * event.getPartialTicks()
                - Math.cos(yaw) * 0.16F;
        double arrowPosY = mc.player.lastTickPosY
                + (mc.player.posY - mc.player.lastTickPosY)
                * event.getPartialTicks()
                + mc.player.getEyeHeight() - 0.1;
        double arrowPosZ = mc.player.lastTickPosZ
                + (mc.player.posZ - mc.player.lastTickPosZ)
                * event.getPartialTicks()
                - Math.sin(yaw) * 0.16F;

        // calculate starting motion
        double arrowMotionFactor = usingBow ? 1F : 0.4F;
        double arrowMotionX = -Math.sin(yaw) * Math.cos(pitch) * arrowMotionFactor;
        double arrowMotionY = - Math.sin(pitch) * arrowMotionFactor;
        double arrowMotionZ = Math.cos(yaw) * Math.cos(pitch) * arrowMotionFactor;
        double arrowMotion = Math.sqrt(arrowMotionX * arrowMotionX + arrowMotionY * arrowMotionY + arrowMotionZ * arrowMotionZ);
        arrowMotionX /= arrowMotion;
        arrowMotionY /= arrowMotion;
        arrowMotionZ /= arrowMotion;
        if (usingBow) {
            double bowPower = (72000 - mc.player.getItemInUseCount()) / 20F;
            bowPower = (bowPower * bowPower + bowPower * 2F) / 3F;

            if (bowPower > 1F || bowPower <= 0.1F) {
                bowPower = 1F;
            }

            bowPower *= 3F;
            arrowMotionX *= bowPower;
            arrowMotionY *= bowPower;
            arrowMotionZ *= bowPower;

        } else {
            arrowMotionX *= 1.5D;
            arrowMotionY *= 1.5D;
            arrowMotionZ *= 1.5D;
        }

        // GL settings
        //GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(3);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);

        RenderManager renderManager = mc.getRenderManager();

        // draw trajectory line
        double gravity =
                usingBow ? 0.05D : item instanceof ItemPotion ? 0.4D
                        : item instanceof ItemFishingRod ? 0.15D : 0.03D;
        GL11.glColor4f(0, 1, 0, 0.75F);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for (int i = 0; i < 1000; i++) {
            GL11.glVertex3d(arrowPosX - renderManager.viewerPosX, arrowPosY - renderManager.viewerPosY,
                    arrowPosZ - renderManager.viewerPosZ);

            arrowPosX += arrowMotionX * 0.1;
            arrowPosY += arrowMotionY * 0.1;
            arrowPosZ += arrowMotionZ * 0.1;
            arrowMotionX *= 0.999D;
            arrowMotionY *= 0.999D;
            arrowMotionZ *= 0.999D;
            arrowMotionY -= gravity * 0.1;

            // todo this is a really bad way to check for collision
            if (mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(arrowPosX, arrowPosY, arrowPosZ)) != null) {
                break;
            }
        }
        GL11.glEnd();

        GL11.glPointSize(5);
        GL11.glBegin(GL11.GL_POINTS);
        GL11.glVertex3d(arrowPosX - renderManager.viewerPosX, arrowPosY - renderManager.viewerPosY, arrowPosZ - renderManager.viewerPosZ);
        GL11.glEnd();

        //GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}
