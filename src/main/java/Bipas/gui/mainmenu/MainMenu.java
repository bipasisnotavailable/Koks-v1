package Bipas.gui.mainmenu;

import java.io.IOException;

import Bipas.Koks;
import Bipas.alt.GuiAltLogin;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import net.minecraft.client.gui.*;

public class MainMenu extends GuiScreen {

    public MainMenu() {

    }

    public void initGui() {

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(new ResourceLocation("client/generals/yoshi.gif"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);

        this.drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);

        String[] buttons = {"Singleplayer", "Multiplayer", "Altmanager", "Themes", "Settings", "Quit"};

        int count = 0;
        for (String button : buttons) {
            float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8 - mc.fontRendererObj.getStringWidth(button) / 2f;
            float y = height - 20;
            boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(button) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT;


            this.drawCenteredString(mc.fontRendererObj, button, (int) ((width / buttons.length) * count + (width / buttons.length) / 2f + 8), height - 20, hovered ? 0xff00ffff : -1);
            count++;
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(width / 2f,height / 2f, 0);
        GlStateManager.scale(3, 3, 1);
        GlStateManager.translate(-(width / 2f), -(height / 2f), 0);
        this.drawCenteredString(mc.fontRendererObj, Koks.getKoks().CLIENT_NAME, width / 2f,height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f, -1);
        GlStateManager.popMatrix();
    }


    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        String[] buttons = {"Singleplayer", "Multiplayer", "Altmanager", "Themes","Settings", "Quit"};

        int count = 0;
        for (String button : buttons) {
            float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8 - mc.fontRendererObj.getStringWidth(button) / 2f;
            float y = height - 20;
            if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(button) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
                switch (button) {
                    case "Singleplayer":
                        mc.displayGuiScreen(new GuiSelectWorld(this));
                        break;
                    case "Multiplayer":
                        mc.displayGuiScreen(new GuiMultiplayer(this));
                        break;
                    case "Altmanager":
                        mc.displayGuiScreen(new GuiAltLogin(this));
                        break;
                    //case "Themes":
                        //this.mc.displayGuiScreen(new ThemeSelection());
                        //break;
                    case "Settings":
                        mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                        break;
                    case "Quit":
                        mc.shutdown();
                        break;
                }
            }
            count++;
        }
    }


    public void onGuiClosed() {

    }
}
