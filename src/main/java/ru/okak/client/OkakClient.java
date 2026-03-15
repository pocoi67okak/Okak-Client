package ru.okak.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.okak.client.module.ModuleManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class OkakClient implements ClientModInitializer {
    public static final String MOD_ID = "okakclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public static ModuleManager moduleManager;
    private static KeyBinding guiKeyBinding;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Okak Client for 1.21.8...");
        moduleManager = new ModuleManager();
        moduleManager.init();

        guiKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.okakclient.open_gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.okakclient.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (guiKeyBinding.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new ru.okak.client.gui.OkakScreen());
                }
            }
            moduleManager.onTick(client);
        });
    }
}
