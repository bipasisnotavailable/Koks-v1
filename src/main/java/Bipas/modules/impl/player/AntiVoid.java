package Bipas.modules.impl.player;

import Bipas.event.Event;
import Bipas.event.impl.EventUpdate;
import Bipas.modules.Module;
import Bipas.utilities.value.values.ModeValue;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

/**
 * @author avox | lmao | kroko
 * @created on 07.09.2020 : 16:27
 */
public class AntiVoid extends Module {
    public ModeValue<String> Mode = new ModeValue<>("Mode", "Matrix", new String[]{"OldHypixel", "Matrix"}, this);
    public AntiVoid() {
        super("AntiVoid", "you canot fall in the void", Category.PLAYER);
        addValue(Mode);
    }

    @Override
    public void onEvent(Event event) { //HYPIXEL
        if (Mode.getSelectedMode().equals("OldHypixel")) {
            if (event instanceof EventUpdate) {
                double distance = 0;

                for (int i = 0; i < 256; i++) {
                    BlockPos currentPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - i, mc.thePlayer.posZ);
                    if ((this.mc.theWorld.getBlockState(currentPos).getBlock() != Blocks.air) && (this.mc.theWorld.getBlockState(currentPos).getBlock() != Blocks.grass) && (this.mc.theWorld.getBlockState(currentPos).getBlock() != Blocks.tallgrass) && (this.mc.theWorld.getBlockState(currentPos).getBlock() != Blocks.red_flower) && (this.mc.theWorld.getBlockState(currentPos).getBlock() != Blocks.yellow_flower)) {
                        distance = mc.thePlayer.posY - currentPos.getY();
                    }
                }


                if (mc.thePlayer.fallDistance > 5 && distance == 0) {
                    mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 6D, mc.thePlayer.posZ, false));
                }
                if (mc.thePlayer.onGround)
                    mc.thePlayer.fallDistance = 0;

            }
        }
        if (Mode.getSelectedMode().equals("Matrix")){
            if(mc.thePlayer.fallDistance > 3){
                mc.thePlayer.jump();
                mc.thePlayer.fallDistance = 0;
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
