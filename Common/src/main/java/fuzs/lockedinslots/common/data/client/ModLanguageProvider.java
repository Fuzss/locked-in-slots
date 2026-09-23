package fuzs.lockedinslots.common.data.client;

import fuzs.lockedinslots.common.LockedInSlots;
import fuzs.lockedinslots.common.client.handler.NoSlotInteractionHandler;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.addKeyCategory(LockedInSlots.MOD_ID, LockedInSlots.MOD_NAME);
        this.add(NoSlotInteractionHandler.LOCK_SLOT_KEY_MAPPING, "Lock Slot");
        this.add(NoSlotInteractionHandler.KEY_SLOT_UNLOCK, "Hold %s to Unlock Slot");
    }
}
