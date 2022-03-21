package Bipas.modules.impl.combat;
import Bipas.event.Event;
import Bipas.event.impl.PacketEvent;
import Bipas.modules.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;

import java.util.Random;


public class Insults extends Module {
    public Insults() {
        super("Insults", "Insults", Category.COMBAT);
    }
    private Packet packet;



    @Override
    public void onEvent(Event event) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789012345678901234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        packet = ((PacketEvent) event).getPacket();

        if (((PacketEvent) event).getPacket() instanceof S02PacketChat) {
            final S02PacketChat s02PacketChat = (S02PacketChat)packet;
            final String cp21 = s02PacketChat.getChatComponent().getUnformattedText();
            if ((cp21.contains("Fanta") || cp21.contains("fanta")) && !cp21.contains("Ich fantasiere von Suizid")) {
                mc.thePlayer.sendChatMessage("Ich fantasiere von Suizid " + saltStr);
            }
            if ((cp21.contains("FDP") || cp21.contains("Fdp")|| cp21.contains("fdp")) && !cp21.contains("deletefdp.today")) {
                mc.thePlayer.sendChatMessage("deletefdp.today " + saltStr);
            }
            if ((cp21.contains("Intent") || cp21.contains("intent")|| cp21.contains("INTENT")) && !cp21.contains("watch me ddosing intent from my home ip (reel)")) {
                mc.thePlayer.sendChatMessage("watch me ddosing intent from my home ip (reel) " + saltStr);
            }
            if ((cp21.contains("Killed by") || cp21.contains("killed by " + mc.thePlayer.getName() )|| cp21.contains("KILLED BY" + mc.thePlayer.getName() )) && !cp21.contains("bipas client > you")) {
                mc.thePlayer.sendChatMessage("bipas client > you " + saltStr);
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