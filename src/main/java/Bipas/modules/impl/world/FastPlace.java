package Bipas.modules.impl.world;

import Bipas.event.Event;
import Bipas.event.impl.EventUpdate;
import Bipas.modules.Module;

/**
 * @author avox | lmao | kroko
 * @created on 06.09.2020 : 11:08
 */
public class FastPlace extends Module {

    public FastPlace() {
        super("FastPlace", "You can place fast", Category.WORLD);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            mc.rightClickDelayTimer = 0;
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        mc.rightClickDelayTimer = 4;
    }

}