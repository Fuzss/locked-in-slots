package fuzs.lockedinslots.fabric.client;

import fuzs.lockedinslots.common.LockedInSlots;
import fuzs.lockedinslots.common.client.LockedInSlotsClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class LockedInSlotsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(LockedInSlots.MOD_ID, LockedInSlotsClient::new);
    }
}
