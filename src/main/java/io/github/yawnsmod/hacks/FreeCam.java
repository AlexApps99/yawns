package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
//import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
//import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FreeCam extends Hack {
    public FreeCam() {
        super("Freecam", "Fake noclip", Category.DISPLAY, true);
    }

    // Todo - needs serious work, different event
    //@SubscribeEvent
    //public void onCameraUpdate(CameraSetup event) {
    //    mc.setRenderViewEntity();
    //}
}
