package fuzs.lockedinslots.neoforge.client;

import fuzs.lockedinslots.common.LockedInSlots;
import fuzs.lockedinslots.common.client.LockedInSlotsClient;
import fuzs.lockedinslots.common.data.client.ModLanguageProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = LockedInSlots.MOD_ID, dist = Dist.CLIENT)
public class LockedInSlotsNeoForgeClient {

    public LockedInSlotsNeoForgeClient() {
        ClientModConstructor.construct(LockedInSlots.MOD_ID, LockedInSlotsClient::new);
        DataProviderHelper.registerDataProviders(LockedInSlots.MOD_ID, ModLanguageProvider::new);
    }
}
