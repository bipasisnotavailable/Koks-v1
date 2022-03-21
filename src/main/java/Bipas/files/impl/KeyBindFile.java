package Bipas.files.impl;

import Bipas.Koks;
import Bipas.files.Files;
import Bipas.modules.Module;

import java.io.BufferedReader;
import java.io.FileWriter;

/**
 * @author avox | lmao | kroko
 * @created on 03.09.2020 : 16:30
 */
public class KeyBindFile extends Files {

    public KeyBindFile() {
        super("keybinds");
    }

    @Override
    public void writeToFile(FileWriter fileWriter) throws Exception {
        for(Module module : Koks.getKoks().moduleManager.getModules()) {
            fileWriter.write(module.getModuleName() + ":" + module.getKeyBind() + "\n");
        }
        fileWriter.close();
    }

    @Override
    public void readFromFile(BufferedReader fileReader) throws Exception {
        String line;
        while((line = fileReader.readLine()) != null) {
            String[] args = line.split(":");
            Module module = Koks.getKoks().moduleManager.getModule(args[0]);
            if(module != null) {
                int key = Integer.parseInt(args[1]);
                module.setKeyBind(key);
            }
        }
        fileReader.close();
    }
}
