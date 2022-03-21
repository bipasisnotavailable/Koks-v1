package koks.modules.impl.movement;

import koks.Koks;
import koks.event.Event;
import koks.event.impl.AnimationEvent;
import koks.event.impl.EventPacket;
import koks.event.impl.EventUpdate;
import koks.event.impl.PacketEvent;
import koks.modules.Module;
import koks.modules.impl.movement.modes.HypixelFly;
import koks.modules.impl.visuals.Animations;
import koks.utilities.*;
import koks.utilities.value.values.ModeValue;
import koks.utilities.value.values.NumberValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import java.util.ArrayList;

import static koks.utilities.StrafeUtil.SetMotion;

/**
 * @author avox | lmao | kroko
 * @created on 04.09.2020 : 18:14
 */
public class Fly extends Module {

    Timer timer = new Timer();
    private final ArrayList<Packet> packets = new ArrayList<>();
    private int stage;
    private int glidedelay;
    private int matrixmotion;
    private int matrixtick;
    private double EnableHeight;
    private final HypixelFly hypixelFly;
    private final TimeUtil timeUtil = new TimeUtil();
    private final MovementUtil movementUtil = new MovementUtil();
    public final ModeValue<String> modeValue = new ModeValue<>("Mode", "Hypixel", new String[]{"Hypixel", "Verus", "Verusdmg", "VerusLagback",  "AAC3.2.2", "Minemora", "MCCentral", "MCCentral 2", "Matrix", "NCPiston"}, this);
    public final NumberValue<Integer> aac322boost = new NumberValue<Integer>("AAC3.2.2-Boost", 9, 10, 5, this);
    private boolean verusB3733SpoofGround = false;




    private int counter;

    public boolean firstLagback = false;

    public ArrayList<C03PacketPlayer> savedC03Packets = new ArrayList<>();



    public Fly() {
        super("Fly", "Flight", Category.MOVEMENT);
        addValue(aac322boost);
        Koks.getKoks().valueManager.addValue(modeValue);
        hypixelFly = new HypixelFly();
    }
    public static boolean hurt = false;

    @Override
    public void onEvent(Event event) {
        switch (modeValue.getSelectedMode()) {
            case "Hypixel":
                hypixelFly.onEvent(event);
                break;
        }

        if (event instanceof EventUpdate) {
            setModuleInfo(modeValue.getSelectedMode());
            switch (modeValue.getSelectedMode()) {
                case "AAC3.2.2":
                    aac322();
                    break;
                case "Minemora":
                    minemora();
                    break;
                case "MCCentral":
                    mccentral();
                    break;
                case "MCCentral 2":
                    mccentral2();
                    break;
                case "Matrix":
                    matrix();
                    break;
                case "NCPiston":
                    NCPiston();
                    break;
                case "Verus":
                    Verus();
                    break;
                case "Verusdmg":
                    Verusdmg();
                    break;
                case "VerusLagback":
                    VerusLagback();
                    break;

            }
        }

        if (event instanceof PacketEvent && modeValue.getSelectedMode().equals("MCCentral 2")) {
            if (((PacketEvent) event).getType() == PacketEvent.Type.SEND && ((PacketEvent) event).getPacket() instanceof C03PacketPlayer) {
                packets.add(((PacketEvent) event).getPacket());
                //event.setCanceled(true);
            }
        }

        if (event instanceof AnimationEvent && Koks.getKoks().moduleManager.getModule(Animations.class).isToggled()) {
            AnimationEvent animationEvent = (AnimationEvent) event;
            animationEvent.setRightLeg(1.5F, 0F, 0F);
            animationEvent.setLeftLeg(1.5F, 0F, 0F);
            animationEvent.setBody(1.5F, 0F, 0F);
            animationEvent.setBodyPos(0, 0.7F, -0.5F);
            animationEvent.setHead(0.6F, 0F, 0F);
            animationEvent.setLeftArm(-1.5F, 0F, 0F);
            animationEvent.setRightArm(-1.5F, 0F, 0F);
            animationEvent.setRightArmPos(0, 0.7F, -0.5F);
            animationEvent.setLeftArmPos(0, 0.7F, -0.5F);
            animationEvent.setHeadPos(0, 0.7F, -0.5F);
        }
    }

    public void minemora() {
        if(mc.thePlayer.fallDistance>0){
            mc.timer.timerSpeed = 0.7;
        } else {
            mc.timer.timerSpeed = 1;
        }
        if(mc.thePlayer.onGround){
            mc.thePlayer.jump();
        } else if (mc.thePlayer.fallDistance>0) {
            glidedelay++;
            if (glidedelay >= 4) {
                glidedelay = 0;
                mc.thePlayer.motionY = 0.015;
            } else {
                mc.thePlayer.motionY = -0.0784;
            }
        }
    }

    public void mccentral() {
        System.out.println(mc.thePlayer.fallDistance);
        mc.thePlayer.motionY = 0;

        MovementUtil movementUtil = new MovementUtil();
        if(mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindForward.pressed) {
            movementUtil.setSpeed(1.5F);
        }
        if(mc.gameSettings.keyBindJump.pressed) {
            mc.thePlayer.motionY += 0.1;
        }
        if(mc.gameSettings.keyBindSneak.pressed) {
            mc.thePlayer.motionY -= 0.1;

        }
    }

    public void Verus() {
        if(EnableHeight > mc.thePlayer.posY) {
            mc.thePlayer.jump();
            mc.thePlayer.onGround = true;
            mc.thePlayer.fallDistance = 0;
        }
    }
    public void VerusLagback() {
        SetMotion(6);


    }

    public void Verusdmg() {

        mc.thePlayer.motionY = 0.004f;

        if(mc.thePlayer.hurtTime != 0) {
            MoveUtil.strafe(0.7873d);
        } else {
            MoveUtil.strafe(0.4873d);
        }

        if(mc.gameSettings.keyBindJump.pressed) {
//					mc.thePlayer.motionY = 0.1f;
        } else if(mc.gameSettings.keyBindSneak.pressed) {
            mc.thePlayer.motionY = -1;
        }

        if(mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0) {

        } else {
            mc.thePlayer.motionX = 0;
            mc.thePlayer.motionZ = 0;
        }
        mc.thePlayer.onGround = true;
    }




    public void mccentral2() {
        if (mc.gameSettings.keyBindJump.isKeyDown())
            mc.thePlayer.motionY = 0.5;
        else if (mc.gameSettings.keyBindSneak.isKeyDown())
            mc.thePlayer.motionY = -0.5;
        else
            mc.thePlayer.motionY = 0;

        mc.thePlayer.setSneaking(false);
        movementUtil.setSpeed(0.4);
        if (mc.gameSettings.keyBindSprint.isKeyDown())
            mc.thePlayer.speedInAir = 0.6F;
        else
            mc.thePlayer.speedInAir = 0.2F;

        double addY = new RandomUtil().randomDouble(-0.05, 0.10);

        mc.thePlayer.motionY += addY;
    }

    public void matrix() {
        if (mc.thePlayer.fallDistance>0) {
            mc.timer.timerSpeed = 0.5;
            if (mc.thePlayer.ticksExisted % 3 == 0) {
                mc.thePlayer.motionY = -0.005;
                matrixmotion--;
            }
        }
    }

    public void aac322() {
        if (mc.thePlayer.posY <= -70) {
            mc.thePlayer.motionY = aac322boost.getDefaultValue();
        }
        if (mc.gameSettings.keyBindSprint.pressed && !mc.thePlayer.onGround) {
            mc.timer.timerSpeed = 0.1F;
            mc.rightClickDelayTimer = 0;
        } else {
            mc.timer.timerSpeed = 1F;
            mc.rightClickDelayTimer = 6;
        }
    }

    public void NCPiston() {

            mc.thePlayer.motionY = 0;


    }

    @Override
    public void onEnable() {
        stage = 0;
        glidedelay = 0;
        timeUtil.reset();
        switch (modeValue.getSelectedMode()) {
            case "Hypixel":
                hypixelFly.onEnable();
                break;
            case "Minemora":
                break;
            case "Matrix":
                matrixmotion = 6;
                if (mc.thePlayer.onGround)
                    mc.thePlayer.jump();
                matrixtick = 0;
                break;
            case "Verus":
                EnableHeight = mc.thePlayer.posY;
                break;
            case "Verusdmg":
                if (mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0, 3.0001, 0).expand(0, 0, 0)).isEmpty()) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.0001, mc.thePlayer.posZ, false));
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
                }
                mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.42, mc.thePlayer.posZ);
                break;
            case "VerusLagback":
                mc.thePlayer.capabilities.allowFlying = true;
                    mc.thePlayer.capabilities.isFlying = true;
                    mc.thePlayer.motionY = 0;
                    mc.thePlayer.onGround = true;
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY - 3.1, mc.thePlayer.posZ, false));
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));

                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY - 3.1, mc.thePlayer.posZ, false));
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));

                }

        }


    @Override
    public void onDisable() {
        switch (modeValue.getSelectedMode()) {

            case "MCCentral":
                mc.thePlayer.motionX = 0;
                mc.thePlayer.motionZ = 0;
            case "Hypixel":
                hypixelFly.onDisable();
                break;
            case "AAC3.2.2":
                mc.timer.timerSpeed = 1F;
                mc.rightClickDelayTimer = 6;
                break;
            case "Minemora":
                mc.timer.timerSpeed = 1.0;
                break;
            case "MCCentral 2":
                mc.thePlayer.speedInAir = 0.02F;
                mc.timer.timerSpeed = 1.0;
                mc.thePlayer.motionX = 0;
                mc.thePlayer.motionZ = 0;
                break;
            case "Matrix":
                mc.timer.timerSpeed = 1;
                break;
            case "Verus":
                break;
            case "Verusdmg":
                break;
            case "VerusLagback":
                mc.thePlayer.capabilities.allowFlying = false;
                mc.thePlayer.capabilities.isFlying = false;
                mc.thePlayer.onGround = false;
                break;
        }
    }


}
