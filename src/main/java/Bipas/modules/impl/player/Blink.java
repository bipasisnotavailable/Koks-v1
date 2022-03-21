package Bipas.modules.impl.player;

import Bipas.event.Event;
import Bipas.event.impl.EventRender3D;
import Bipas.event.impl.PacketEvent;
import Bipas.modules.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

import java.util.ArrayList;

/**
 * @author avox | lmao | kroko
 * @created on 10.09.2020 : 09:01
 */
public class Blink extends Module {

    public ArrayList<Packet> packets = new ArrayList<>();
    public double x, y, z, yaw;

    public Blink() {
        super("Blink", "You dont send any packets to the server", Category.PLAYER);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRender3D) {
/*            mc.getRenderManager().doRenderEntity(mc.thePlayer, x, y, z, (float) yaw, ((EventRender3D) event).getPartialTicks(), false);
            mc.getRenderManager().doRenderEntity(mc.thePlayer, x, y, z, (float) yaw, ((EventRender3D) event).getPartialTicks(), true);
            mc.getRenderManager().renderEntityWithPosYaw(mc.thePlayer, x, y, z, (float) yaw, ((EventRender3D) event).getPartialTicks());*/
        }

        if (event instanceof PacketEvent) {
            PacketEvent e = (PacketEvent) event;
            if (e.getType() == PacketEvent.Type.SEND) {
                if (e.getPacket() instanceof C03PacketPlayer) {
                    packets.add(e.getPacket());
                    event.setCanceled(true);
                }
            }
        }
    }

    @Override
    public void onEnable() {
        x = mc.thePlayer.posX;
        y = mc.thePlayer.posY;
        z = mc.thePlayer.posZ;
        yaw = mc.thePlayer.rotationYaw;
    }

    @Override
    public void onDisable() {
        packets.forEach(mc.thePlayer.sendQueue.getNetworkManager()::sendPacket);
        packets.clear();
    }

}