package koks.modules.impl.combat;
import koks.event.Event;
import koks.event.impl.PacketEvent;
import koks.modules.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;

import java.util.Locale;


public class Insults extends Module {
    public Insults() {
        super("Insults", "Insults", Category.COMBAT);
    }
    private Packet packet;



    @Override
    public void onEvent(Event event) {
        packet = ((PacketEvent) event).getPacket();

        if (((PacketEvent) event).getPacket() instanceof S02PacketChat) {
            final S02PacketChat s02PacketChat = (S02PacketChat)packet;
            final String cp21 = s02PacketChat.getChatComponent().getUnformattedText();
            if ((cp21.contains("Fanta") || cp21.contains("fanta")) && !cp21.contains("Ich fantasiere von Suizid")) {
                mc.thePlayer.sendChatMessage("Ich fantasiere von Suizid");
            }
            if ((cp21.contains("FDP") || cp21.contains("Fdp")|| cp21.contains("fdp")) && !cp21.contains("deletefdp.today")) {
                mc.thePlayer.sendChatMessage("deletefdp.today");
            }
            if ((cp21.contains("Intent") || cp21.contains("intent")|| cp21.contains("INTENT")) && !cp21.contains("watch me ddosing intent from my home ip (reel)")) {
                mc.thePlayer.sendChatMessage("watch me ddosing intent from my home ip (reel)");
            }
            if ((cp21.contains("Killed by") || cp21.contains("killed by")|| cp21.contains("KILLED BY")) && !cp21.contains("bipas client > you")) {
                mc.thePlayer.sendChatMessage("bipas client > you");
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