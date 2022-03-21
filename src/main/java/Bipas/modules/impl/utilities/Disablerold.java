package Bipas.modules.impl.utilities;

import Bipas.event.Event;
import Bipas.event.impl.PacketEvent;
import Bipas.modules.Module;
import Bipas.utilities.value.values.ModeValue;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;

/**
 * @author avox | lmao | kroko
 * @created on 04.09.2020 : 16:39
 */
public class Disablerold extends Module {

    public ModeValue<String> mode = new ModeValue<String>("Mode", "Hypixel", new String[]{"Hypixel"}, this);

    public Disablerold() {
        super("Disabler", "You disable the anticheat", Category.UTILITIES);
        addValue(mode);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof PacketEvent) {
            if (mode.getSelectedMode().equalsIgnoreCase("Hypixel")) {
                if (((PacketEvent) event).getType() == PacketEvent.Type.RECIVE) {
                    if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel") && Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null) {

                        if (((PacketEvent) event).getPacket() instanceof S32PacketConfirmTransaction) {
                            final S32PacketConfirmTransaction s32PacketConfirmTransaction = (S32PacketConfirmTransaction) ((PacketEvent) event).getPacket();
                            if (s32PacketConfirmTransaction.getActionNumber() < 0) {
                                event.setCanceled(true);
                            }
                        }
                    }
                }
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
