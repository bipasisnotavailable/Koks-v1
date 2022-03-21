package Bipas.modules.impl.visuals;

import Bipas.event.Event;
import Bipas.event.impl.EventBobbing;
import Bipas.modules.Module;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 22:21
 */
public class NoBob extends Module {

    public NoBob() {
        super("NoBob", "You hand dosnt bobbing anymore", Category.VISUALS);
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof EventBobbing) {
            EventBobbing eventBobbing = (EventBobbing) event;
            eventBobbing.setDistanceWalkedModified(0.0F);
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}