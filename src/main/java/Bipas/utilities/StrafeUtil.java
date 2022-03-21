package Bipas.utilities;

import net.minecraft.client.Minecraft;

public class StrafeUtil {

    public static void SetMotion(double speed) {
        Object mc = Minecraft.getMinecraft();
        double forward =  Minecraft.getMinecraft().thePlayer.movementInput.moveForward;
        double strafe =  Minecraft.getMinecraft().thePlayer.movementInput.moveStrafe;
        float yaw = Minecraft.getMinecraft().thePlayer.rotationYaw;
        if ((forward == 0.0D) && (strafe == 0.0D)) {
            Minecraft.getMinecraft().thePlayer.motionX = 0;
            Minecraft.getMinecraft().thePlayer.motionZ = 0;
        } else {
            if (forward != 0.0D) {
                if (strafe > 0.0D) {
                    yaw += (forward > 0.0D ? -45 : 45);
                } else if (strafe < 0.0D) {
                    yaw += (forward > 0.0D ? 45 : -45);
                }
                strafe = 0.0D;
                if (forward > 0.0D) {
                    forward = 1;
                } else if (forward < 0.0D) {
                    forward = -1;
                }
            }
            Minecraft.getMinecraft().thePlayer.motionX = forward * speed * Math.cos(Math.toRadians(yaw + 90.0F)) + strafe * speed * Math.sin(Math.toRadians(yaw + 90.0F));
            Minecraft.getMinecraft().thePlayer.motionZ = forward * speed * Math.sin(Math.toRadians(yaw + 90.0F)) - strafe * speed * Math.cos(Math.toRadians(yaw + 90.0F));

        }
    }
}
