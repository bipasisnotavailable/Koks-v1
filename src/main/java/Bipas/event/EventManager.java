package Bipas.event;

import Bipas.Koks;
import Bipas.modules.Module;

public class EventManager {

    public void onEvent(Event event) {
        for(Module module : Koks.getKoks().moduleManager.getModules()) {
            try{
            if(module.isToggled()) {
                    module.onEvent(event);
                }
            }catch(Exception ignored) {
            }
        }
    }
}
