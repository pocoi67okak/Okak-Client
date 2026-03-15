package ru.okak.client.module;

import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;
import ru.okak.client.module.impl.XrayModule;
import ru.okak.client.module.impl.EspModule;
import ru.okak.client.module.impl.SpeedModule;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void init() {
        modules.add(new XrayModule());
        modules.add(new EspModule());
        modules.add(new SpeedModule());
    }

    public List<Module> getModules() {
        return modules;
    }

    public void onTick(MinecraftClient client) {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick(client);
            }
        }
    }
}
