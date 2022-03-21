package Bipas.utilities.value;

import Bipas.Koks;
import Bipas.modules.Module;
import Bipas.modules.impl.combat.Aura;
import Bipas.modules.impl.movement.Fly;

/**
 * @author avox | lmao | kroko
 * @created on 04.09.2020 : 16:43
 */
public class SetVisibility {

    public void setVisibility() {
        for (Module module : Koks.getKoks().moduleManager.getModules()) {
            if (module instanceof Aura) {
                if(((Aura) module).targetSettings.isExpanded()){
                    ((Aura) module).preferTarget.setVisible(!((Aura) module).targetMode.getSelectedMode().equals("Switch"));
                }
                ((Aura) module).preAimRange.setVisible(((Aura) module).preAim.isToggled());

                ((Aura) module).serverSideSwing.setVisible(((Aura) module).silentSwing.isToggled());
                ((Aura) module).swingChance.setVisible(((Aura) module).silentSwing.isToggled());
                ((Aura) module).ignoreTeam.setVisible(((Aura) module).player.isToggled());
                ((Aura) module).ignoreFriend.setVisible(((Aura) module).player.isToggled());
            }
            if(module instanceof Fly) {
                ((Fly) module).aac322boost.setVisible(((Fly) module).modeValue.getSelectedMode().equalsIgnoreCase("AAC3.2.2"));
            }
        }
    }

}