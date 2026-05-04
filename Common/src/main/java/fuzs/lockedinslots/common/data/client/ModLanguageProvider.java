package fuzs.lockedinslots.common.data.client;

import fuzs.lockedinslots.common.LockedInSlots;
import fuzs.lockedinslots.common.client.handler.NoSlotInteractionHandler;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.addKeyCategory(LockedInSlots.MOD_ID, LockedInSlots.MOD_NAME);
        builder.add(NoSlotInteractionHandler.LOCK_SLOT_KEY_MAPPING, "Lock Slot");
        builder.add(NoSlotInteractionHandler.KEY_SLOT_UNLOCK, "Hold %s to Unlock Slot");
    }
}
