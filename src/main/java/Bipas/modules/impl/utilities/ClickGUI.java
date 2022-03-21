package Bipas.modules.impl.utilities;

import Bipas.Koks;
import Bipas.event.Event;
import Bipas.event.impl.EventUpdate;
import Bipas.modules.Module;
import org.lwjgl.input.Keyboard;

/**
 * @author avox | lmao | kroko
 * @created on 03.09.2020 : 15:49
 */
public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", "You open the ClickGUI", Category.UTILITIES);
        this.setKeyBind(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (mc.currentScreen != null) mc.displayGuiScreen(null);
            this.setToggled(false);
            mc.displayGuiScreen(Koks.getKoks().clickGUI);
        }
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {

    }
}
