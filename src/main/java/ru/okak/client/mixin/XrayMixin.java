package ru.okak.client.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.okak.client.module.impl.XrayModule;

@Mixin(AbstractBlock.class)
public abstract class XrayMixin {

    private boolean isTargetOre(BlockState state) {
        String name = Registries.BLOCK.getId(state.getBlock()).getPath();
        return name.equals("coal_ore") || name.equals("deepslate_coal_ore") ||
               name.equals("copper_ore") || name.equals("deepslate_copper_ore");
    }

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void onGetRenderType(BlockState state, CallbackInfoReturnable<BlockRenderType> cir) {
        if (XrayModule.xrayEnabled) {
            if (!isTargetOre(state)) {
                cir.setReturnValue(BlockRenderType.INVISIBLE);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Inject(method = "getAmbientOcclusionLightLevel", at = @At("HEAD"), cancellable = true)
    private void onGetAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (XrayModule.xrayEnabled) {
            cir.setReturnValue(1.0f);
        }
    }

    @Inject(method = "isOpaque", at = @At("HEAD"), cancellable = true)
    private void onIsOpaque(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (XrayModule.xrayEnabled) {
            cir.setReturnValue(false);
        }
    }
}
