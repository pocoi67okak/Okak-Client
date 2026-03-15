package ru.okak.client.module.impl;

import net.minecraft.client.MinecraftClient;
import ru.okak.client.module.Module;

public class EspModule extends Module {
    public static boolean espEnabled = false;

    public EspModule() {
        super("ESP", "ЕСП (Мобы, Игроки)");
    }

    @Override
    protected void onEnable() {
        espEnabled = true;
    }

    @Override
    protected void onDisable() {
        espEnabled = false;
    }
}
