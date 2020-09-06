package koks.modules;

import koks.Koks;
import koks.event.Event;
import koks.files.impl.KeyBindFile;
import koks.utilities.value.Value;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.io.FileWriter;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 21:08
 */
public abstract class Module {

    public Minecraft mc = Minecraft.getMinecraft();

    private String moduleName,displayName;
    private Category moduleCategory;
    private boolean visible = true, enabled, bypassed;
    private int keyBind;

    public Module(String moduleName, Category moduleCategory) {
        this.moduleName = moduleName;
        this.moduleCategory = moduleCategory;
        this.displayName = moduleName;
    }

    public enum Category {
        COMBAT, MOVEMENT, PLAYER, UTILITIES, VISUALS, WORLD;
    }

    public abstract void onEvent(Event event);
    public abstract void onEnable(); //Fixxen das es richtig funktioniert
    public abstract void onDisable();

    public void addValue(Value value) {
        Koks.getKoks().valueManager.addValue(value);
    }

    public void toggle() {
        if (enabled) {
            onDisable();
            enabled = false;
        } else {
            onEnable();
            enabled = true;
        }
    }

    public void setToggled(boolean enabled) {
        if (!enabled) {
            if(mc.thePlayer != null) {
                onDisable();
            }
            this.enabled = false;
        } else {
            if(mc.thePlayer != null) {
                onEnable();
            }
            this.enabled = true;
        }

    }

    public boolean isBypassed() {
        return bypassed;
    }

    public void setBypassed(boolean bypassed) {
        this.bypassed = bypassed;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Category getModuleCategory() {
        return moduleCategory;
    }

    public void setModuleCategory(Category moduleCategory) {
        this.moduleCategory = moduleCategory;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = getModuleName() + " §7" + displayName;
    }

    public void setDisplayName(String moduleName, String displayName) {
        this.displayName = moduleName + " §7" + displayName;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isToggled() {
        return enabled;
    }

    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }
}