package com.tysongf.sealsmod;

import com.tysongf.sealsmod.entity.ArcticSealEntity;
import com.tysongf.sealsmod.entity.HarborSealEntity;
import com.tysongf.sealsmod.entity.LeopardSealEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModSpawning {
    public static void registerSpawns() {
        SealsMod.LOGGER.info("Registering spawns for " + SealsMod.MOD_ID);

        // Arctic Seal spawns in frozen/snowy biomes
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(
                        BiomeKeys.SNOWY_BEACH,
                        BiomeKeys.FROZEN_OCEAN,
                        BiomeKeys.DEEP_FROZEN_OCEAN,
                        BiomeKeys.SNOWY_PLAINS,
                        BiomeKeys.ICE_SPIKES,
                        BiomeKeys.FROZEN_RIVER
                ),
                SpawnGroup.CREATURE,
                ModEntities.ARCTIC_SEAL,
                7, // weight
                1, // min group size
                7  // max group size
        );

        // Harbor Seal spawns in temperate ocean/beach biomes
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(
                        BiomeKeys.BEACH,
                        BiomeKeys.OCEAN,
                        BiomeKeys.LUKEWARM_OCEAN,
                        BiomeKeys.COLD_OCEAN,
                        BiomeKeys.RIVER,
                        BiomeKeys.STONY_SHORE
                ),
                SpawnGroup.CREATURE,
                ModEntities.HARBOR_SEAL,
                7,
                1,
                7
        );

        // Leopard Seal spawns in cold oceans and frozen areas (rarer)
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(
                        BiomeKeys.FROZEN_OCEAN,
                        BiomeKeys.DEEP_FROZEN_OCEAN,
                        BiomeKeys.COLD_OCEAN,
                        BiomeKeys.DEEP_COLD_OCEAN
                ),
                SpawnGroup.CREATURE,
                ModEntities.LEOPARD_SEAL,
                7,
                1,
                7
        );

        // Set spawn restrictions
        SpawnRestriction.register(
                ModEntities.ARCTIC_SEAL,
                SpawnLocationTypes.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                ArcticSealEntity::canSpawn
        );

        SpawnRestriction.register(
                ModEntities.HARBOR_SEAL,
                SpawnLocationTypes.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                HarborSealEntity::canSpawn
        );

        SpawnRestriction.register(
                ModEntities.LEOPARD_SEAL,
                SpawnLocationTypes.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                LeopardSealEntity::canSpawn
        );
    }
}
