package Bipas.modules.impl.utilities;

import Bipas.Koks;
import Bipas.event.Event;
import Bipas.event.impl.PacketEvent;
import Bipas.modules.Module;
import Bipas.utilities.value.values.BooleanValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;


public class AutoPlay extends Module {
    public BooleanValue<Boolean> Minemora = new BooleanValue<>("Minemora", false, this);
    public AutoPlay() {
        super("AutoPlay", "AutoPlay", Category.UTILITIES);
        addValue(Minemora);
    }
    private Packet packet;
    private int MinemoraDelay;
    private boolean readytojoin;
    private int MinemoraRegisterDelay;
    private boolean MinemoraReadytoRegister;
    private String Password = "Heyho123";

    @Override
    public void onEvent(Event event) {
        packet = ((PacketEvent) event).getPacket();
        if (Minemora.isToggled()){
            //join
        if (((PacketEvent) event).getPacket() instanceof S02PacketChat) {
            final S02PacketChat s02PacketChat = (S02PacketChat)packet;
            final String cp21 = s02PacketChat.getChatComponent().getUnformattedText();
            if ((cp21.contains("Has click en alguna de las siguientes opciones"))) {
                readytojoin = true;
                mc.thePlayer.addChatMessage(new ChatComponentText(Koks.getKoks().PREFIX + "§amessage found"));
            }

        }
        if (readytojoin && (MinemoraDelay < 200)) {
            MinemoraDelay++;
        }
        if (MinemoraDelay >= 200 && readytojoin) {
            readytojoin = false;
            MinemoraDelay = 0;
            mc.thePlayer.addChatMessage(new ChatComponentText(Koks.getKoks().PREFIX + "§a/join"));
            mc.thePlayer.sendChatMessage("/join");
        }
            //register
            if (((PacketEvent) event).getPacket() instanceof S02PacketChat) {
                final S02PacketChat s02PacketChat = (S02PacketChat)packet;
                final String cp21 = s02PacketChat.getChatComponent().getUnformattedText();
                if ((cp21.contains("/register <Tu Contrase"))) {
                    MinemoraReadytoRegister = true;
                    mc.thePlayer.addChatMessage(new ChatComponentText(Koks.getKoks().PREFIX + "§amessage found"));
                }

            }
            if (MinemoraReadytoRegister && (MinemoraRegisterDelay < 200)) {
                MinemoraRegisterDelay++;
            }
            if (MinemoraRegisterDelay >= 200 && MinemoraReadytoRegister) {
                MinemoraReadytoRegister = false;
                MinemoraRegisterDelay = 0;
                mc.thePlayer.addChatMessage(new ChatComponentText(Koks.getKoks().PREFIX + "§a/register"));
                mc.thePlayer.sendChatMessage("/register" + " " + Password + " " + Password);
            }
        }
    }
    @Override
    public void onEnable() {
        MinemoraDelay = 0;
        readytojoin = false;
        MinemoraRegisterDelay = 0;
        MinemoraReadytoRegister = false;
    }

    @Override
    public void onDisable() {

    }
}
