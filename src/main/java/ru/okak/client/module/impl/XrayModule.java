package ru.okak.client.module.impl;

import net.minecraft.client.MinecraftClient;
import ru.okak.client.module.Module;

public class XrayModule extends Module {
    public static boolean xrayEnabled = false;

    public XrayModule() {
        super("Xray", "Икс-Рей (Уголь и Медь)");
    }

    @Override
    protected void onEnable() {
        xrayEnabled = true;
        reloadChunks();
    }

    @Override
    protected void onDisable() {
        xrayEnabled = false;
        reloadChunks();
    }

    private void reloadChunks() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.worldRenderer != null) {
            client.worldRenderer.reload();
        }
    }
}
