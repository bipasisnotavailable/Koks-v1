package Bipas.files.impl;

import Bipas.Koks;
import Bipas.files.Files;
import Bipas.modules.Module;
import Bipas.utilities.value.Value;
import Bipas.utilities.value.values.BooleanValue;
import Bipas.utilities.value.values.ModeValue;
import Bipas.utilities.value.values.NumberValue;

import java.io.BufferedReader;
import java.io.FileWriter;

/**
 * @author avox | lmao | kroko
 * @created on 05.09.2020 : 01:44
 */
public class Settings extends Files {

    public Settings() {
        super("settings");
    }

    @Override
    public void writeToFile(FileWriter fileWriter) throws Exception {
        for (Value value : Koks.getKoks().valueManager.getValues()) {
            if (value instanceof BooleanValue) {
                fileWriter.write(value.getModule().getModuleName() + ":" + value.getName() + ":" + ((BooleanValue) value).isToggled() + "\n");
            } else if (value instanceof ModeValue) {
                String text = value.getModule().getModuleName() + ":" + value.getName() + ":";
                if (((ModeValue) value).getObjects() != null) {
                    for (BooleanValue s : ((ModeValue) value).getObjects()) {
                        if (s.getModule() == value.getModule()) {
                            fileWriter.write(text + s.getName() + ":" + s.isToggled() + "\n");
                        }
                    }

                } else {
                    fileWriter.write(value.getModule().getModuleName() + ":" + value.getName() + ":" + ((ModeValue) value).getSelectedMode() + "\n");
                }

            } else if (value instanceof NumberValue) {
                if (((NumberValue) value).getMinDefaultValue() != null) {
                    fileWriter.write(value.getModule().getModuleName() + ":" + value.getName() + ":" + ((NumberValue) value).getMinDefaultValue() + ":" + ((NumberValue) value).getDefaultValue() + "\n");
                } else {
                    fileWriter.write(value.getModule().getModuleName() + ":" + value.getName() + ":" + ((NumberValue) value).getDefaultValue() + "\n");
                }
            }
        }
        fileWriter.close();
    }

    @Override
    public void readFromFile(BufferedReader fileReader) throws Exception {
        String line;
        while ((line = fileReader.readLine()) != null) {
            String[] args = line.split(":");
            Module module = Koks.getKoks().moduleManager.getModule(args[0]);
            Value value = Koks.getKoks().valueManager.getValue(module, args[1]);
            if (module != null && value != null) {
                if (value instanceof BooleanValue) {
                    ((BooleanValue) value).setToggled(Boolean.parseBoolean(args[2]));
                } else if (value instanceof ModeValue) {
                    if (((ModeValue) value).getObjects() == null) {
                        ((ModeValue) value).setSelectedMode(args[2]);
                    } else {
                        for (BooleanValue booleanValue : ((ModeValue) value).getObjects()) {
                            if (booleanValue.getModule() == module) {
                                if (booleanValue.getName().equalsIgnoreCase(args[2])) {
                                    booleanValue.setToggled(Boolean.parseBoolean(args[3]));
                                }
                            }
                        }
                    }
                } else if (value instanceof NumberValue) {
                    if (((NumberValue) value).getMinDefaultValue() != null) {
                        if (((NumberValue) value).getDefaultValue() instanceof Float) {
                            ((NumberValue) value).setMinDefaultValue(Float.parseFloat(args[2]));
                            ((NumberValue) value).setDefaultValue(Float.parseFloat(args[3]));
                        } else if (((NumberValue) value).getDefaultValue() instanceof Long) {
                            ((NumberValue) value).setMinDefaultValue(Long.parseLong(args[2]));
                            ((NumberValue) value).setDefaultValue(Long.parseLong(args[3]));
                        } else if (((NumberValue) value).getDefaultValue() instanceof Integer) {
                            ((NumberValue) value).setMinDefaultValue(Integer.parseInt(args[2]));
                            ((NumberValue) value).setDefaultValue(Integer.parseInt(args[3]));
                        } else if (((NumberValue) value).getDefaultValue() instanceof Double) {
                            ((NumberValue) value).setMinDefaultValue(Double.parseDouble(args[2]));
                            ((NumberValue) value).setDefaultValue(Double.parseDouble(args[3]));
                        }
                    } else {
                        if (((NumberValue) value).getDefaultValue() instanceof Float) {
                            ((NumberValue) value).setDefaultValue(Float.parseFloat(args[2]));
                        } else if (((NumberValue) value).getDefaultValue() instanceof Long) {
                            ((NumberValue) value).setDefaultValue(Long.parseLong(args[2]));
                        } else if (((NumberValue) value).getDefaultValue() instanceof Integer) {
                            ((NumberValue) value).setDefaultValue(Integer.parseInt(args[2]));
                        } else if (((NumberValue) value).getDefaultValue() instanceof Double) {
                            ((NumberValue) value).setDefaultValue(Double.parseDouble(args[2]));
                        }
                    }
                }
            }
        }
        fileReader.close();
    }
}
