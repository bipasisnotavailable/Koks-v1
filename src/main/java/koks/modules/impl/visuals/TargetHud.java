package koks.modules.impl.visuals;

import koks.Koks;
import koks.event.Event;
import koks.event.impl.EventItemRenderer;
import koks.event.impl.EventRender2D;
import koks.event.impl.EventRender3D;
import koks.modules.Module;
import koks.utilities.value.values.ModeValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TargetHud extends Module {

    public static Minecraft mc = Minecraft.getMinecraft();
    private String mode;
    private static Color COLOR;
    private EntityOtherPlayerMP target;
    private double healthBarWidth;
    private double hudHeight;
    public EntityLivingBase lastEnt;
    public float lastHealth;
    public float damageDelt;
    public float lastPlayerHealth;
    public float damageDeltToPlayer;
    public DecimalFormat format;
    public double animation;


    public TargetHud() {
        super("TargetHud", "TargetHud", Category.VISUALS);
    }

    public ModeValue<String> TargetHud = new ModeValue<>("TargetHud", "Novoline", new String[]{"Novoline", "Astolfo"}, this);

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRender2D) {
            EventRender2D e = (EventRender2D) event;
            switch (TargetHud.getSelectedMode()) {

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
