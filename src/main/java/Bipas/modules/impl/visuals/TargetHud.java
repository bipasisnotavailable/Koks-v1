package Bipas.modules.impl.visuals;

import Bipas.event.Event;
import Bipas.event.impl.EventRender2D;
import Bipas.modules.Module;
import Bipas.utilities.value.values.ModeValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;
import java.text.DecimalFormat;

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
