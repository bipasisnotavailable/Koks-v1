package Bipas.modules.impl.player;

import Bipas.event.Event;
import Bipas.event.impl.EventUpdate;
import Bipas.event.impl.MotionEvent;
import Bipas.modules.Module;
import Bipas.utilities.RotationUtil;
import Bipas.utilities.value.values.NumberValue;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * @author avox | lmao | kroko
 * @created on 04.09.2020 : 18:18
 */
public class BedFucker extends Module {

    NumberValue<Integer> range = new NumberValue<Integer>("Range", 10, 20, 0, this);

    public BedFucker() {
        super("BedFucker", "You break the bed automaticaly", Category.PLAYER);
        addValue(range);
    }

    public BlockPos blockPos;
    public float curYaw,curPitch;

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {

            if (this.blockPos == null) {

                for (int x = range.getDefaultValue() * -1; x < range.getDefaultValue(); x++) {
                    for (int y = range.getDefaultValue() * -1; y < range.getDefaultValue(); y++) {
                        for (int z = range.getDefaultValue() * -1; z < range.getDefaultValue(); z++) {
                            BlockPos blockPos = mc.thePlayer.getPosition().add(x, y, z);

                            if (mc.theWorld.getBlockState(blockPos).getBlock().equals(Blocks.bed)) {
                                this.blockPos = blockPos;
                            }
                        }
                    }
                }
            } else {
                mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, this.blockPos, EnumFacing.DOWN));
                if (mc.theWorld.getBlockState(this.blockPos).getBlock() == Blocks.air) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.blockPos, EnumFacing.DOWN));
                    this.blockPos = null;
                }
            }
        }

        if (event instanceof MotionEvent) {
            MotionEvent motionEvent = (MotionEvent) event;
            if (motionEvent.getType() == MotionEvent.Type.PRE) {
                if (this.blockPos != null) {
                    RotationUtil rotationUtil = new RotationUtil();
                    float[] rots = rotationUtil.faceBlock(this.blockPos, false,curYaw,curPitch,360);
                    curYaw = rots[0];
                    curPitch = rots[1];
                    motionEvent.setYaw(curYaw);
                    motionEvent.setPitch(curPitch);
                }else{
                    curYaw = mc.thePlayer.rotationYaw;
                    curPitch = mc.thePlayer.rotationYaw;
                }
            }
        }
    }

    @Override
    public void onEnable() {
        curYaw = mc.thePlayer.rotationYaw;
        curPitch = mc.thePlayer.rotationPitch;
    }

    @Override
    public void onDisable() {
        this.blockPos = null;
    }
}
