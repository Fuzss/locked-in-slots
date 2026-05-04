package fuzs.lockedinslots.common.mixin.client;

import fuzs.lockedinslots.common.client.handler.NoSlotInteractionHandler;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
abstract class AbstractContainerScreenMixin extends Screen {

    protected AbstractContainerScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "extractSlot",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 0),
            slice = @Slice(from = @At(value = "INVOKE",
                                      target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;recalculateQuickCraftRemaining()V")))
    protected void extractSlot(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY, CallbackInfo callback) {
        // Render our item icon for slots that have an item in them,
        // vanilla only renders for empty slots which we handle in the other method.
        if (slot.hasItem() && slot.isActive()) {
            NoSlotInteractionHandler.getNoItemIcon(slot)
                    .ifPresent((Identifier identifier) -> graphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                            identifier,
                            slot.x,
                            slot.y,
                            16,
                            16));
        }
    }

    @ModifyVariable(method = "extractSlot", at = @At("STORE"))
    protected Identifier extractSlot(Identifier icon, GuiGraphicsExtractor graphics, Slot slot) {
        // replace the vanilla item icon in case one is present
        return NoSlotInteractionHandler.getNoItemIcon(slot).orElse(icon);
    }
}
