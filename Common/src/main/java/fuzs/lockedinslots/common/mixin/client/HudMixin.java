package fuzs.lockedinslots.common.mixin.client;

import fuzs.lockedinslots.common.LockedInSlots;
import fuzs.lockedinslots.common.client.handler.NoSlotInteractionHandler;
import fuzs.lockedinslots.common.config.ClientConfig;
import fuzs.lockedinslots.common.config.WorldSlotsStorage;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
abstract class HudMixin {

    @Inject(method = "extractItemHotbar",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                     shift = At.Shift.AFTER,
                     ordinal = 0))
    private void extractItemHotbar(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo callback) {
        float alpha = (float) LockedInSlots.CONFIG.get(ClientConfig.class).guiHotbarOverlayAlpha;
        if (alpha > 0.0F) {
            for (int slot : WorldSlotsStorage.getLockedSlots()) {
                if (slot < Inventory.getSelectionSize()) {
                    guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                            NoSlotInteractionHandler.LOCKED_SLOT_LOCATION,
                            guiGraphics.guiWidth() / 2 - 91 + 3 + slot * 20,
                            guiGraphics.guiHeight() - 22 + 3,
                            16,
                            16,
                            ARGB.white(alpha));
                } else {
                    // the locked slots set is sorted, so after we are past hotbar indices, we can stop
                    break;
                }
            }
        }
    }
}
