package koks.modules.impl.movement;

import koks.Koks;
import koks.event.Event;
import koks.event.impl.AnimationEvent;
import koks.event.impl.EventUpdate;
import koks.modules.Module;
import koks.modules.impl.visuals.Animations;
import koks.utilities.TimeUtil;
import koks.utilities.value.Value;
import koks.utilities.value.values.BooleanValue;
import koks.utilities.value.values.ModeValue;
import koks.utilities.value.values.NumberValue;
import net.minecraft.client.Minecraft;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 21:08
 */
public class Sprint extends Module {

    public BooleanValue<Boolean> b = new BooleanValue<>("BooleanTest", true, this);
    public ModeValue<String> b2 = new ModeValue<>("BooleanTest", "AAC", new String[]{"AAC", "NCP"}, this);

    public NumberValue<Float> vFloat = new NumberValue<>("BooleanTest", 10F, 1000F, 0F, this);
    public NumberValue<Double> vDouble = new NumberValue<>("BooleanTest", 10D, 100D, 0D, this);
    public NumberValue<Integer> vInteger = new NumberValue<>("BooleanTest", 10, 100, 0, this);
    public NumberValue<Long> vLong = new NumberValue<>("BooleanTest", 10L, 100000L, 0L, this);


    public Sprint() {
        super("Sprint", Category.MOVEMENT);
        Koks.getKoks().valueManager.addValue(b);
        Koks.getKoks().valueManager.addValue(b2);

        Koks.getKoks().valueManager.addValue(vFloat);
        Koks.getKoks().valueManager.addValue(vDouble);
        Koks.getKoks().valueManager.addValue(vInteger);
        Koks.getKoks().valueManager.addValue(vLong);
    }

    TimeUtil timeUtil = new TimeUtil();

    @Override
    public void onEvent(Event event) {

        if (event instanceof AnimationEvent && Koks.getKoks().moduleManager.getModule(Animations.class).isToggled()) {
            AnimationEvent a = (AnimationEvent) event;

            a.setBody(0.4F, 0, 0);
            a.setLeftLegPos(0, -0.1F, 0.27F);
            a.setRightLegPos(0, -0.1F, 0.27F);
            a.setLeftArm(2F, 0, 0);
            a.setRightArm(2F, 0, 0);
            a.setHead(0.8F, 0, 0);
            a.setRightLeg(a.getRightLeg()[0] + 0.3F, a.getRightLeg()[1], a.getRightLeg()[2]);
            a.setLeftLeg(a.getLeftLeg()[0] + 0.3F, a.getLeftLeg()[1], a.getLeftLeg()[2]);

        }

        if (event instanceof EventUpdate) {
            if (mc.thePlayer.moveForward != 0 && !mc.gameSettings.keyBindBack.isKeyDown() && canSprint()) {
                mc.thePlayer.setSprinting(true);
            }
            if (timeUtil.isDelayComplete(vLong.getDefaultValue())) {
                System.out.println("INTEGER:" + vInteger.getDefaultValue());
                System.out.println("FLOAT:" + vFloat.getDefaultValue());
                System.out.println("LONG:" + vLong.getDefaultValue());
                System.out.println("DOUBLE:" + vDouble.getDefaultValue());


                long secs = vLong.getDefaultValue() / 1000;
                long mins = secs / 60;
                long restsecs = secs % 60;
                System.out.printf("Time " + "%02d:%02d", mins, restsecs);
                timeUtil.reset();

            }
        }
    }

    public boolean canSprint() {
        return true;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {

    }

}