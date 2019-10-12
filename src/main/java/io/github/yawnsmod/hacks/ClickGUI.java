package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClickGUI extends Hack {
    public ClickGUI() {
        super("ClickGUI", "The most useful hack of them all", Hack.Category.DISPLAY, false);
    }

    // Todo
    // Consider nuklear if imgui is too difficult (github.com/LWJGL/lwjgl3/blob/master/modules/samples/src/test/java/org/lwjgl/demo/nuklear/GLFWDemo.java)
    // Also, making a native gui code type menu like wurst's tabgui could be an easier/better option

    @SubscribeEvent
    public void afterRenderGameOverlay(RenderGameOverlayEvent.Post event) {

    }
}
