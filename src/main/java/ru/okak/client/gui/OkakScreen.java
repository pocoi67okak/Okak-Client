package ru.okak.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import ru.okak.client.OkakClient;
import ru.okak.client.module.Module;

public class OkakScreen extends Screen {
    public OkakScreen() {
        super(Text.literal("Окак Клиент"));
    }

    @Override
    protected void init() {
        super.init();
        
        int yOffset = this.height / 4;
        for (Module module : OkakClient.moduleManager.getModules()) {
            this.addDrawableChild(ButtonWidget.builder(
                Text.literal(module.getRussionName() + ": " + (module.isEnabled() ? "ВКЛ" : "ВЫКЛ")),
                button -> {
                    module.toggle();
                    button.setMessage(Text.literal(module.getRussionName() + ": " + (module.isEnabled() ? "ВКЛ" : "ВЫКЛ")));
                }
            ).dimensions(this.width / 2 - 100, yOffset, 200, 20).build());
            
            yOffset += 24;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 4 - 30, 0xFFFFFFFF);
    }
}
