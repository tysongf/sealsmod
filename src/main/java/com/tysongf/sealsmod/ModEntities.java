package com.tysongf.sealsmod;

import com.tysongf.sealsmod.entity.ArcticSealEntity;
import com.tysongf.sealsmod.entity.HarborSealEntity;
import com.tysongf.sealsmod.entity.LeopardSealEntity;
import com.tysongf.sealsmod.entity.SealEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<ArcticSealEntity> ARCTIC_SEAL = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SealsMod.MOD_ID, "arctic_seal"),
            EntityType.Builder.create(ArcticSealEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9f, 0.6f)
                    .maxTrackingRange(10)
                    .build()
    );

    public static final EntityType<HarborSealEntity> HARBOR_SEAL = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SealsMod.MOD_ID, "harbor_seal"),
            EntityType.Builder.create(HarborSealEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9f, 0.6f)
                    .maxTrackingRange(10)
                    .build()
    );

    public static final EntityType<LeopardSealEntity> LEOPARD_SEAL = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SealsMod.MOD_ID, "leopard_seal"),
            EntityType.Builder.create(LeopardSealEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.2f, 0.7f)
                    .maxTrackingRange(10)
                    .build()
    );

    public static void registerEntities() {
        SealsMod.LOGGER.info("Registering entities for " + SealsMod.MOD_ID);
        
        FabricDefaultAttributeRegistry.register(ARCTIC_SEAL, SealEntity.createSealAttributes());
        FabricDefaultAttributeRegistry.register(HARBOR_SEAL, SealEntity.createSealAttributes());
        FabricDefaultAttributeRegistry.register(LEOPARD_SEAL, SealEntity.createSealAttributes());
    }
}
