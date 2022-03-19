package koks.utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class MoveUtil {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static boolean isMoving() {
        return mc.thePlayer != null && (mc.thePlayer.movementInput.moveForward != 0f || mc.thePlayer.movementInput.moveStrafe != 0f);
    }

    public static void strafe(Double speed) {
        if (!isMoving()) return;
        float yaw = (float) getDirection();
        EntityPlayerSP thePlayer = mc.thePlayer;
        thePlayer.motionX = -Math.sin(yaw) * speed;
        thePlayer.motionZ = Math.cos(yaw) * speed;
    }

    public static double getDirection() {
        EntityPlayerSP thePlayer = mc.thePlayer;
        Float rotationYaw = thePlayer.rotationYaw;
        if (thePlayer.moveForward < 0f) rotationYaw += 180f;
        Float forward = 1f;
        if (thePlayer.moveForward < 0f) forward = -0.5f; else if (thePlayer.moveForward > 0f) forward = 0.5f;
        if (thePlayer.moveStrafing > 0f) rotationYaw -= 90f * forward;
        if (thePlayer.moveStrafing < 0f) rotationYaw += 90f * forward;
        return Math.toRadians(rotationYaw);
    }
}
