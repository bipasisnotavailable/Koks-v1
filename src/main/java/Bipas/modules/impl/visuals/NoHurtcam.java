package Bipas.modules.impl.visuals;

import Bipas.event.Event;
import Bipas.event.impl.HurtCameraEvent;
import Bipas.modules.Module;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 22:21
 */
public class NoHurtcam extends Module {

    public NoHurtcam() {
        super("NoHurtcam", "Its disable the hurt cam", Category.VISUALS);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof HurtCameraEvent) {
            event.setCanceled(true);
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}