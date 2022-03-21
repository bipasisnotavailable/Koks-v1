package koks.modules.impl.utilities;

import koks.event.Event;
import koks.event.impl.EventUpdate;
import koks.modules.Module;
import koks.utilities.PacketUtil;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.apache.commons.lang3.RandomUtils;




public class Disabler extends Module{

    int jumpTimeout = 0;
    double yStart = 0;
    private boolean expectedTeleport;
    public TimerUtil timer = new TimerUtil();
    public static List<Packet> transactions;
    public static List<Packet> keepAlives;
    public double x,y,z;
    private LinkedList<Packet> packetQueue = new LinkedList();
    private int current;
    private static CopyOnWriteArrayList<C0FPacketConfirmTransaction> bypassList = new CopyOnWriteArrayList();
    private static SplittableRandom random = new SplittableRandom();
    private int memeTick;
    private final Queue j = new ConcurrentLinkedDeque();
    private C03PacketPlayer.C06PacketPlayerPosLook aac5QueuedPacket = null;
    private int aac5SameReach = 5;
    List<Packet<INetHandlerPlayServer>> packetBuffer = new ArrayList<Packet<INetHandlerPlayServer>>();
    public int confirmtranscounter;
    public int confirmtransactioncounter;
    public ArrayList<Packet> delayedtransactions2 = new ArrayList();
    public static transient CopyOnWriteArrayList<Packet> packets = new CopyOnWriteArrayList();
    int currentTransaction = 0;
    double x2;
    double y2;
    S08PacketPlayerPosLook packet;


    public ModeValue<String> mode = new ModeValue<>("Mode", "Verus Combat", new String[]{"Verus combat"}, this);

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
                        if(((EventPacket) event).getPacket() instanceof C0FPacketConfirmTransaction || ((EventPacket) event).getPacket() instanceof C00PacketKeepAlive) {
                            event.setCanceled(true);
                        }
                    break;
            }
        }

    }




    @Override
    public void onEnable() {
        switch (mode.getSelectedMode()) {
        }
    }


    @Override
    public void onDisable() {


    }

}
