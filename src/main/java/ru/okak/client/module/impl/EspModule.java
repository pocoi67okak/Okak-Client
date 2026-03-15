package ru.okak.client.module.impl;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import ru.okak.client.module.Module;

public class EspModule extends Module {
    public EspModule() {
        super("ESP", "ЕСП (Мобы, Игроки)");
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        
        for (Entity entity : client.world.getEntities()) {
            if (entity == client.player) continue;

            double dist = client.player.distanceTo(entity);
            if (dist <= 20.0) {
                entity.setGlowing(true); // Soft ESP via Glowing effect
            } else {
                entity.setGlowing(false);
            }
        }
    }

    @Override
    protected void onDisable() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null) {
            for (Entity entity : client.world.getEntities()) {
                entity.setGlowing(false);
            }
        }
    }
}
