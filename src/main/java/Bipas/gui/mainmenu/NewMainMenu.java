package Bipas.gui.mainmenu;

import Bipas.Koks;
import Bipas.alt.GuiAltLogin;
import Bipas.utilities.RenderUtils;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;

import net.minecraft.client.renderer.GlStateManager;

public class NewMainMenu extends GuiScreen implements GuiYesNoCallback{

    @Override
    public void initGui() {
        String[] displayNames = new String[]{"Singleplayer", "Multiplayer", "Options", "Alt Manager", "Exit"};
        int offset = height / 2 - -30;
        for (int i = 0; i < displayNames.length; i++) {
            String name = displayNames[i];
            buttonList.add(new GuiButton(i, width / 2 - 50, offset, 100, 20, name));
            offset += 28;
        }

        super.initGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(new ResourceLocation("client/generals/yoshi.gif"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);

        this.drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);

        ResourceLocation background = new ResourceLocation("client/generals/yoshi.gif");
        this.mc.getTextureManager().bindTexture(background);
        RenderUtils.drawImage(0, 0, 0, 0, width, height, width, height);
        GlStateManager.pushMatrix();
        GlStateManager.translate(width / 2f,height / 2f, 0);
        GlStateManager.scale(7, 7, 1);
        GlStateManager.translate(-(width / 2f), -(height / 2f), 0);
        this.drawCenteredString(mc.fontRendererObj, Koks.getKoks().CLIENT_NAME, width / 2f,height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f, -1);
        GlStateManager.popMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);


    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }

        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 3) {
            mc.displayGuiScreen(new GuiAltLogin(this));

        }

        if (button.id == 4) {
            this.mc.shutdown();
        }


        super.actionPerformed(button);
    }


    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}






