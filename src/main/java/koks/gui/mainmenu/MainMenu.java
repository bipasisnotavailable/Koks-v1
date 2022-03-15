package koks.gui.mainmenu;

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
                    case "Themes":
                        this.mc.displayGuiScreen(new ThemeSelection());
                        break;
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
