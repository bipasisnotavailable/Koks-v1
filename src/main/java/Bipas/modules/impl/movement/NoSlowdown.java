package Bipas.modules.impl.movement;

import Bipas.event.Event;
import Bipas.event.impl.EventUpdate;
import Bipas.modules.Module;
import Bipas.utilities.value.values.BooleanValue;
import Bipas.utilities.value.values.NumberValue;

/**
 * @author avox | lmao | kroko
 * @created on 05.09.2020 : 23:31
 */
public class NoSlowdown extends Module {

    public NumberValue<Integer> speedInPercent = new NumberValue<>("Speed in Percent", 20, 100, 20, this);
    public BooleanValue<Boolean> sprint = new BooleanValue<>("Sprint", true, this);

    public NoSlowdown() {
        super("NoSlowdown", "You are not slow when you doing sachen", Category.MOVEMENT);
        addValue(speedInPercent);
        addValue(sprint);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            setModuleInfo(speedInPercent.getDefaultValue().toString() + (sprint.isToggled() ? ", Sprint" : ""));
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}