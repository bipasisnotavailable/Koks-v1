package koks.modules.impl.utilities;

import koks.event.Event;
import koks.event.impl.PacketEvent;
import koks.modules.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;

import java.util.Locale;


public class AutoPlay extends Module {
    public AutoPlay() {
        super("AutoPlay", "AutoPlay", Category.UTILITIES);
    }
    private Packet packet;
    private int delay;
    private boolean readytojoin;

    @Override
    public void onEvent(Event event) {
        packet = ((PacketEvent) event).getPacket();

        if (((PacketEvent) event).getPacket() instanceof S02PacketChat) {
            final S02PacketChat s02PacketChat = (S02PacketChat)packet;
            final String cp21 = s02PacketChat.getChatComponent().getUnformattedText();
            if ((cp21.contains("Has click en alguna de las siguientes opciones"))) {
                readytojoin = true;
            }
            if (readytojoin = true) {
                delay++;
            }
            if (delay >= 100) {
                mc.thePlayer.sendChatMessage("/join");
                readytojoin = false;
                delay = 0;
            }
        }
    }
    @Override
    public void onEnable() {
    delay = 0;
    readytojoin = false;
    }

    @Override
    public void onDisable() {

    }
}
