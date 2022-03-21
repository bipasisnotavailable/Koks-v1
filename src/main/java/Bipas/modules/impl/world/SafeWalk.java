package Bipas.modules.impl.world;

import Bipas.event.Event;
import Bipas.event.impl.SafeWalkEvent;
import Bipas.modules.Module;

/**
 * @author avox | lmao | kroko
 * @created on 06.09.2020 : 11:08
 */
public class SafeWalk extends Module {

    public SafeWalk() {
        super("SafeWalk", "You cant fall down from a block", Category.WORLD);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof SafeWalkEvent) {
            if (mc.thePlayer.onGround)
                ((SafeWalkEvent) event).setSafe(true);
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}