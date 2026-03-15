package ru.okak.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.okak.client.module.impl.EspModule;

@Mixin(Entity.class)
public abstract class EntityGlowMixin {

    @Inject(method = "isGlowing", at = @At("HEAD"), cancellable = true)
    private void onIsGlowing(CallbackInfoReturnable<Boolean> cir) {
        if (EspModule.espEnabled) {
            Entity self = (Entity)(Object) this;
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && self != client.player) {
                double dist = client.player.distanceTo(self);
                if (dist <= 20.0) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
