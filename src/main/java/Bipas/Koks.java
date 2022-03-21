package Bipas;

import Bipas.command.CommandManager;
import Bipas.event.EventManager;
import Bipas.files.FileManager;
import Bipas.gui.clickgui.ClickGUI;
import Bipas.gui.clickgui.commonvalue.CommonValueManager;
import Bipas.gui.configs.ConfigScreen;
import Bipas.gui.configsnew.DrawConfigManager;
import Bipas.gui.customhud.CustomHUD;
import Bipas.gui.customhud.valuehudsystem.ValueHUDManager;
import Bipas.gui.panelgui.PanelGUI;
import Bipas.hud.tabgui.TabGUI;
import Bipas.manager.ConfigManager;
import Bipas.modules.ModuleManager;
import Bipas.theme.Theme;
import Bipas.theme.ThemeManager;
import Bipas.utilities.value.ValueManager;
import net.minecraft.client.audio.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.util.StringJoiner;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 21:07
 */
public class Koks {

    //i love sex - r3l4y
        /**     ⣿⣿⣿⣿⣿⠟⠋⠄⠄⠄⠄⠄⠄⠄⢁⠈⢻⢿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⠃⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⡀⠭⢿⣿⣿⣿⣿
                ⣿⣿⣿⣿⡟⠄⢀⣾⣿⣿⣿⣷⣶⣿⣷⣶⣶⡆⠄⠄⠄⣿⣿⣿⣿
                ⣿⣿⣿⣿⡇⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠄⠄⢸⣿⣿⣿⣿
                ⣿⣿⣿⣿⣇⣼⣿⣿⠿⠶⠙⣿⡟⠡⣴⣿⣽⣿⣧⠄⢸⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣾⣿⣿⣟⣭⣾⣿⣷⣶⣶⣴⣶⣿⣿⢄⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⡟⣩⣿⣿⣿⡏⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣹⡋⠘⠷⣦⣀⣠⡶⠁⠈⠁⠄⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣍⠃⣴⣶⡔⠒⠄⣠⢀⠄⠄⠄⡨⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣦⡘⠿⣷⣿⠿⠟⠃⠄⠄⣠⡇⠈⠻⣿⣿⣿⣿
                ⣿⣿⣿⣿⡿⠟⠋⢁⣷⣠⠄⠄⠄⠄⣀⣠⣾⡟⠄⠄⠄⠄⠉⠙⠻
                ⡿⠟⠋⠁⠄⠄⠄⢸⣿⣿⡯⢓⣴⣾⣿⣿⡟⠄⠄⠄⠄⠄⠄⠄⠄
                ⠄⠄⠄⠄⠄⠄⠄⣿⡟⣷⠄⠹⣿⣿⣿⡿⠁⠄⠄⠄⠄⠄⠄⠄⠄*/
    private static final Koks KOKS;

    static {
        KOKS = new Koks();
    }

    public static Koks getKoks() {
        return KOKS;
    }

    public final String CLIENT_NAME = "bipas";
    public final String[] CLIENT_DEVELOPER = new String[] {"bipas", "R3L4Y", "zet99x"};
    public final String CLIENT_VERSION = "1.0.0";
    public final String PREFIX = "§c" + CLIENT_NAME + " §7>> §f";
    public boolean playingSound = false;

    public Color client_color = Color.PINK;
    private Theme.ThemeCategory themeCategory = Theme.ThemeCategory.JELLO;

    public ISound koksSound;
    public ModuleManager moduleManager;
    public ValueHUDManager valueHUDManager;
    public ValueManager valueManager;
    public CommonValueManager commonValueManager;
    public ThemeManager themeManager;
    public ClickGUI clickGUI;
    public PanelGUI panelGUI;
    public EventManager eventManager;
    public CommandManager commandManager;
    public FileManager fileManager;
    public ConfigScreen configScreen;
    public ConfigManager configManager;
    public CustomHUD customHUD;
    public TabGUI tabGUI;
    public DrawConfigManager drawConfigManager;
    public Bipas.gui.configsnew.ConfigManager configManagerFromScreen;

    public void initClient() {
        koksSound = PositionedSoundRecord.create(new ResourceLocation("Bipas.sound"));
        final StringJoiner joiner = new StringJoiner(", ");
        for(String author : CLIENT_DEVELOPER) {
            joiner.add(author);
        }
        Display.setTitle(CLIENT_NAME + " " + CLIENT_VERSION + " by " + joiner + " | Based on Koks");
        valueManager = new ValueManager();
        valueHUDManager = new ValueHUDManager();
        commonValueManager = new CommonValueManager();
        moduleManager = new ModuleManager();
        themeManager = new ThemeManager();
        clickGUI = new ClickGUI();
        panelGUI = new PanelGUI();
        commandManager = new CommandManager();
        eventManager = new EventManager();
        fileManager = new FileManager();
        configManager = new ConfigManager();
        if(!configManager.DIR.exists())configManager.DIR.mkdirs();
        configScreen = new ConfigScreen();
        customHUD = new CustomHUD();
        tabGUI = new TabGUI();
        drawConfigManager = new DrawConfigManager();
        configManagerFromScreen = new Bipas.gui.configsnew.ConfigManager();
        fileManager.createFiles();

    }

    public void shutdownClient() {
        fileManager.writeToAllFiles();
    }

    public void setThemeCategory(Theme.ThemeCategory themeCategory) {
        this.themeCategory = themeCategory;
    }

    public Theme.ThemeCategory getThemeCategory() {
        return themeCategory;
    }

}