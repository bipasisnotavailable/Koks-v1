package Bipas.modules.impl.movement;

import Bipas.Koks;
import Bipas.event.Event;
import Bipas.event.impl.EventMove;
import Bipas.event.impl.MotionEvent;
import Bipas.modules.Module;
import Bipas.modules.impl.combat.Aura;
import Bipas.utilities.MovementUtil;

/**
 * @author avox | lmao | kroko
 * @created on 07.09.2020 : 22:34
 */
public class TargetStrafe extends Module {

    public TargetStrafe() {
        super("TargetStrafe", "you are rennen herum des gegners", Category.MOVEMENT);
    }

    int direction;
    MovementUtil movementUtil = new MovementUtil();

    public void strafe(Event event, double speed) {

        if (event instanceof MotionEvent) {
            if (((MotionEvent) event).getType() == MotionEvent.Type.PRE) {
                mc.gameSettings.keyBindForward.pressed = false;
                mc.gameSettings.keyBindBack.pressed = false;
                if (mc.thePlayer.isCollidedHorizontally) {
                    if (direction == 0)
                        direction = 1;
                    else
                        direction = 0;
                }
                if (mc.gameSettings.keyBindLeft.pressed) {
                    direction = 0;
                    mc.gameSettings.keyBindLeft.pressed = false;
                } else if (mc.gameSettings.keyBindRight.pressed) {
                    direction = 1;
                    mc.gameSettings.keyBindRight.pressed = false;
                }
            }
        }

        if (event instanceof EventMove) {
            if (allowStrafing()) {
                mc.thePlayer.setSprinting(true);
                if (mc.thePlayer.getDistanceToEntity(Koks.getKoks().moduleManager.getModule(Aura.class).finalEntity) <= 0) {
                    movementUtil.setSpeedEvent(speed, Koks.getKoks().moduleManager.getModule(Aura.class).yaw, false, true, direction == 0, direction == 1);
                } else {
                    if (mc.thePlayer.getDistanceToEntity(Koks.getKoks().moduleManager.getModule(Aura.class).finalEntity) >= 1) {
                        movementUtil.setSpeedEvent(speed, Koks.getKoks().moduleManager.getModule(Aura.class).yaw, true, false, direction == 0, direction == 1);
                    } else {
                        movementUtil.setSpeedEvent(speed, Koks.getKoks().moduleManager.getModule(Aura.class).yaw, false, false, direction == 0, direction == 1);
                    }
                }
            }
        }
    }

    @Override
    public void onEvent(Event event) {

    }

    public boolean allowStrafing() {
        return Koks.getKoks().moduleManager.getModule(Aura.class).finalEntity != null && isToggled();
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
