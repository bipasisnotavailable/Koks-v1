package Bipas.modules.impl.movement;

import Bipas.Koks;
import Bipas.event.Event;
import Bipas.event.impl.AnimationEvent;
import Bipas.event.impl.EventUpdate;
import Bipas.modules.Module;
import Bipas.modules.impl.combat.Aura;
import Bipas.modules.impl.visuals.Animations;
import Bipas.modules.impl.world.BlockFlight;
import Bipas.utilities.value.values.ModeValue;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 21:08
 */
public class Sprint extends Module {

    public ModeValue<String> mode = new ModeValue<String>("Mode", "Intave", new String[]{"Intave", "Legit"}, this);

    public Sprint() {
        super("Sprint", "you sprint automaticaly", Category.MOVEMENT);
        addValue(mode);
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof AnimationEvent && Koks.getKoks().moduleManager.getModule(Animations.class).isToggled() && mc.thePlayer.isSprinting()) {
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
            setModuleInfo(mode.getSelectedMode());
            switch (mode.getSelectedMode()) {
                case "Legit":

                    if (mc.thePlayer.moveForward != 0 && !mc.gameSettings.keyBindBack.isKeyDown() && canSprint()) {
                        mc.thePlayer.setSprinting(true);
                    } else {
                        mc.thePlayer.setSprinting(false);
                    }
                    break;
                case "Intave":
                    if (canSprint())
                        mc.thePlayer.setSprinting(true);
                    break;
            }
        }
    }

    public boolean canSprint() {
        BlockFlight scaffoldWalk = Koks.getKoks().moduleManager.getModule(BlockFlight.class);
        Aura killAura = Koks.getKoks().moduleManager.getModule(Aura.class);
        if (scaffoldWalk.isToggled() && !(scaffoldWalk.sprint.isToggled()))
            return false;
        if (killAura.isToggled() && killAura.finalEntity != null && killAura.stopSprinting.isToggled())
            return false;
        return true;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {

    }

}