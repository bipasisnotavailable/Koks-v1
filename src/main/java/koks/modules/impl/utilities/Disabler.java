package koks.modules.impl.utilities;

import koks.event.Event;
import koks.event.impl.EventUpdate;
import koks.modules.Module;
import koks.utilities.TimeUtil;
import koks.utilities.value.values.ModeValue;
import net.minecraft.network.Packet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import koks.utilities.TimerUtil;
import koks.event.impl.*;


public class Disabler extends Module{
    public TimerUtil timer = new TimerUtil();
    public static List<Packet> transactions;
    public static List<Packet> keepAlives;
    public double x,y,z;

    public ModeValue<String> mode = new ModeValue<>("Mode", "BlocksMC combat", new String[]{"Verus combat"}, this);

    public Disabler() {
        super("Disabler", "Disable anticheats", Module.Category.PLAYER);
        ArrayList<String> modes = new ArrayList<>();
        addValue(mode);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            setModuleInfo(mode.getSelectedMode());
            PacketEvent e = (PacketEvent)event;
            switch (mode.getSelectedMode()) {
                case "Verus Combat":
                    if (mc.thePlayer.ticksExisted % 100 == 0 && e.getPacket() instanceof C03PacketPlayer) {
                        double x = mc.thePlayer.posX,
                                y = mc.thePlayer.posY,
                                z = mc.thePlayer.posZ;
                        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y - 14.36D, z, false));
                        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, false));
                    }
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
