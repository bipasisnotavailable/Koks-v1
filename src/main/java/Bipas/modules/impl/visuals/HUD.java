package Bipas.modules.impl.visuals;

import Bipas.Koks;
import Bipas.event.Event;
import Bipas.event.impl.EventItemRenderer;
import Bipas.event.impl.EventRender2D;
import Bipas.event.impl.KeyPressEvent;
import Bipas.hud.CrossHair;
import Bipas.hud.ModuleList;
import Bipas.hud.Watermark;
import Bipas.modules.Module;
import Bipas.theme.Theme;
import Bipas.utilities.value.values.BooleanValue;
import Bipas.utilities.value.values.ModeValue;

/**
 * @author avox | lmao | kroko
 * @created on 03.09.2020 : 22:23
 */
public class HUD extends Module {

    public ModuleList moduleList = new ModuleList();
    public Watermark watermark = new Watermark();
    private final Koks koks = Koks.getKoks();

    private BooleanValue<Boolean> tabGUI = new BooleanValue<>("Enabled", true, this);
    private BooleanValue<Boolean> tabGUI_shadow = new BooleanValue<>("Shadow", true, this);
    private BooleanValue<Boolean> tabGUI_client_color = new BooleanValue<>("Color", true, this);
    private BooleanValue<Boolean> tabGUICenteredString = new BooleanValue<>("Centered String", true, this);

    private BooleanValue<Boolean> shadowArrayList = new BooleanValue<>("Shadow", true, this);
    public ModeValue<String> watermarkStyle = new ModeValue<>("Watermark Style", "Simple", new String[]{"Simple", "funni", "Mario Kart"}, this);
    public BooleanValue<Boolean> customCrossHair = new BooleanValue<>("CrossHair", false, this);

    public ModeValue<String> tabGuiSettings = new ModeValue<>("TabGui Settings", new BooleanValue[]{tabGUI, tabGUI_shadow, tabGUI_client_color, tabGUICenteredString}, this);
    public ModeValue<String> arrayListSettings = new ModeValue<>("Arraylist Settings", new BooleanValue[]{shadowArrayList}, this);
    public ModeValue<String> Themes = new ModeValue<>("Themes", "Jello", new String[]{"MOON","JELLO","GAL","CLIENTUS","KLIENTUS","VEGA","LIQUIDSENSE","PERFORMANCE","SUICIDE","MODERN","REGULAR"}, this);

    public HUD() {
        super("HUD", "Its render the HUD", Category.VISUALS);
        addValue(tabGuiSettings);
        addValue(arrayListSettings);
        addValue(watermarkStyle);
        addValue(Themes);
        addValue(customCrossHair);
        this.setToggled(true);
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof EventItemRenderer) {
            EventItemRenderer e = (EventItemRenderer) event;
            switch (Themes.getSelectedMode()) {
                case "MOON":
                    koks.setThemeCategory(Theme.ThemeCategory.MOON);
                    break;
                case "JELLO":
                    koks.setThemeCategory(Theme.ThemeCategory.JELLO);
                    break;
                case "GAL":
                    koks.setThemeCategory(Theme.ThemeCategory.GAL);
                    break;
                case "CLIENTUS":
                    koks.setThemeCategory(Theme.ThemeCategory.CLIENTUS);
                    break;
                case "KLIENTUS":
                    koks.setThemeCategory(Theme.ThemeCategory.KLIENTUS);
                    break;
                case "VEGA":
                    koks.setThemeCategory(Theme.ThemeCategory.VEGA);
                    break;
                case "LIQUIDSENSE":
                    koks.setThemeCategory(Theme.ThemeCategory.LIQUIDSENSE);
                    break;
                case "PERFORMANCE":
                    koks.setThemeCategory(Theme.ThemeCategory.PERFORMANCE);
                    break;
                case "SUICIDE":
                    koks.setThemeCategory(Theme.ThemeCategory.SUICIDE);
                    break;
                case "MODERN":
                    koks.setThemeCategory(Theme.ThemeCategory.MODERN);
                    break;
                case "REGULAR":
                    koks.setThemeCategory(Theme.ThemeCategory.REGULAR);
                    break;
            }
        }

        if (event instanceof EventRender2D) {

            if (tabGUI.isToggled()) {
                tabGUI_shadow.setVisible(true);
                tabGUI_client_color.setVisible(true);
                tabGUICenteredString.setVisible(true);
            } else {
                tabGUI_shadow.setVisible(false);
                tabGUI_client_color.setVisible(false);
                tabGUICenteredString.setVisible(false);
            }

            switch (Koks.getKoks().getThemeCategory()) {
                case REGULAR:
                    moduleList.drawList(shadowArrayList.isToggled());
                    watermark.drawWatermark();
                    if (Koks.getKoks().tabGUI != null && tabGUI.isToggled()) {
                        if (watermarkStyle.getSelectedMode().equals("funni"))
                            Koks.getKoks().tabGUI.drawScreen(2, 70, 80, 15, this.tabGUI_shadow.isToggled(), tabGUI_client_color.isToggled(), tabGUICenteredString.isToggled());
                        else
                            Koks.getKoks().tabGUI.drawScreen(2, 20, 80, 15, this.tabGUI_shadow.isToggled(), tabGUI_client_color.isToggled(), tabGUICenteredString.isToggled());
                    }

                    if (customCrossHair.isToggled())
                        new CrossHair().drawCrosshair();
                    break;
                default:
                    Koks.getKoks().themeManager.getThemeList().forEach(theme -> {
                        if (theme.getThemeCategory()==Koks.getKoks().getThemeCategory()) {
                            theme.drawIngameTheme();
                        }
                    });
                    break;
            }
        }

        if (event instanceof KeyPressEvent) {
            switch (Koks.getKoks().getThemeCategory()) {
                case REGULAR:
                    if (Koks.getKoks().tabGUI != null && tabGUI.isToggled())
                        Koks.getKoks().tabGUI.keyPress(((KeyPressEvent) event).getKey());
                    break;
                default:
                    Koks.getKoks().themeManager.getThemeList().forEach(theme -> {
                        if (theme.getThemeCategory().equals(Koks.getKoks().getThemeCategory())) {
                            if (theme.drawTabGUI()) {
                                if (Koks.getKoks().tabGUI != null)
                                    Koks.getKoks().tabGUI.keyPress(((KeyPressEvent) event).getKey());
                            }
                        }
                    });
                    break;
            }
        }

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
