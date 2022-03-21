package Bipas.modules.impl.combat;

import Bipas.event.Event;
import Bipas.event.impl.EventUpdate;
import Bipas.modules.Module;
import Bipas.utilities.RandomUtil;
import Bipas.utilities.TimeUtil;
import Bipas.utilities.value.values.NumberValue;
import net.minecraft.entity.Entity;

/**
 * @author avox | lmao | kroko
 * @created on 06.09.2020 : 11:08
 */
public class TriggerBot extends Module {

    public NumberValue<Integer> cps = new NumberValue<>("CPS", 7, 12, 20, 1, this);
    private final RandomUtil randomUtil = new RandomUtil();
    private final TimeUtil timeUtil = new TimeUtil();

    public TriggerBot() {
        super("TriggerBot", "You hit enemies when you look at them", Category.COMBAT);
        addValue(cps);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            setModuleInfo(cps.getMinDefaultValue() + ", " + cps.getDefaultValue());
            Entity entity = mc.objectMouseOver.entityHit;
            double finalCPS = cps.getMinValue().equals(cps.getMaxValue()) ? cps.getMaxValue() : randomUtil.randomInt(cps.getMinValue(), cps.getMaxValue());
            if (entity != null) {
                if (timeUtil.hasReached((long) (1000 / (finalCPS + (finalCPS > 10 ? 5 : 0))))) {
                    mc.thePlayer.swingItem();
                    mc.playerController.attackEntity(mc.thePlayer, entity);
                    timeUtil.reset();
                }
            }
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

}