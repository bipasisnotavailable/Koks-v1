package Bipas.modules.impl.combat;

import Bipas.event.Event;
import Bipas.event.impl.EventUpdate;
import Bipas.event.impl.MouseOverEvent;
import Bipas.modules.Module;
import Bipas.utilities.value.values.NumberValue;

/**
 * @author avox | lmao | kroko
 * @created on 06.09.2020 : 11:09
 */
public class LongArms extends Module {

    public NumberValue<Double> reach = new NumberValue<>("Extra Reach", 0.2D, 1D, 0D, this);

    public LongArms() {
        super("LongArms", "", Category.COMBAT);
        addValue(reach);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            setModuleInfo(reach.getDefaultValue().toString());
        }

        if (event instanceof MouseOverEvent) {
            ((MouseOverEvent) event).setReach(3 + reach.getDefaultValue());
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}