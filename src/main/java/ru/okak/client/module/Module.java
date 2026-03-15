package ru.okak.client.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    private final String name;
    private final String russionName;
    private boolean enabled;

    public Module(String name, String russionName) {
        this.name = name;
        this.russionName = russionName;
        this.enabled = false;
    }

    public String getName() {
        return name;
    }

    public String getRussionName() {
        return russionName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    protected void onEnable() {}
    protected void onDisable() {}
    public void onTick(MinecraftClient client) {}
}
