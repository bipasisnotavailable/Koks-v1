package koks.gui.mainmenu;

import koks.Koks;
import koks.alt.GuiAltLogin;
import koks.utilities.RenderUtils;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;

import com.google.common.collect.Lists;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.thealtening.AltService;
import koks.ClientSwitch;
import koks.Koks;
import koks.account.Account;
import koks.alt.GuiAltLogin;
import koks.theme.ThemeSelection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NewMainMenu extends GuiScreen implements GuiYesNoCallback{

    @Override
    public void initGui() {
        String[] displayNames = new String[]{"Singleplayer", "Multiplayer", "Options", "themes", "Alt Manager", "Exit"};
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
            this.mc.displayGuiScreen(new ThemeSelection());
        }

        if (button.id == 4) {
            mc.displayGuiScreen(new GuiAltLogin(this));

        }

        if (button.id == 5) {
            this.mc.shutdown();
        }


        super.actionPerformed(button);
    }


    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}






