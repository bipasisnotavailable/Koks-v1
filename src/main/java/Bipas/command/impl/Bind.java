package Bipas.command.impl;

import Bipas.Koks;
import Bipas.command.Command;
import Bipas.modules.Module;
import org.lwjgl.input.Keyboard;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 22:01
 */
public class Bind extends Command {

    public Bind() {
        super("Bind", "SetKey");
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            sendError("§7(§4BAD USAGE§7)", "§c§l.bind §7<§c§lMODULE§7> §7<§c§lKEY§7>", true);
            return;
        }

        String moduleArg = args[0].toUpperCase();
        String keyArg = args[1].toUpperCase();

        for (Module module : Koks.getKoks().moduleManager.getModules()) {
            if (module.getModuleName().equalsIgnoreCase(moduleArg)) {
                module.setKeyBind(KeytoInt(keyArg));

                try {
                    Koks.getKoks().shutdownClient();
                } catch (Exception e) {
                    System.out.println("Failed save Keybind!");
                }
                sendmsg("§fSet Key of §b§l" + moduleArg + " §fto §b§l" + Keyboard.getKeyName(Keyboard.getKeyIndex(keyArg)) + "§f", true);
            }
        }
    }

}