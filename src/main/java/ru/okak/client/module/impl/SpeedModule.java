package ru.okak.client.module.impl;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;
import ru.okak.client.module.Module;

public class SpeedModule extends Module {
    public SpeedModule() {
        super("Speed", "Скорость (+2 блока)");
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (client.player == null) return;

        Vec3d velocity = client.player.getVelocity();
        // Only boost when moving horizontally on the ground
        if (client.player.isOnGround() && velocity.horizontalLengthSquared() > 0.0001) {
            client.player.setVelocity(velocity.x * 1.5, velocity.y, velocity.z * 1.5);
        }
    }
}
