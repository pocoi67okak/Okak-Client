package ru.okak.client.module.impl;

import net.minecraft.client.MinecraftClient;
import ru.okak.client.module.Module;

public class SpeedModule extends Module {
    public SpeedModule() {
        super("Speed", "Скорость (+2 блока)");
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (client.player == null) return;

        // "максимум на 2 блока" - this implies +2 blocks per second roughly.
        // Base walking speed is ~4.3 blocks/sec. Adding some velocity multiplicator.
        if (client.player.forwardSpeed > 0 || client.player.sidewaysSpeed != 0) {
            if (client.player.isOnGround()) {
                // A very simple soft speedup
                client.player.setVelocity(client.player.getVelocity().multiply(1.2, 1.0, 1.2));
            }
        }
    }
}
