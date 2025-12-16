package com.tysongf.sealsmod.client.render;

import com.tysongf.sealsmod.SealsMod;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer SEAL = new EntityModelLayer(
            Identifier.of(SealsMod.MOD_ID, "seal"), "main");

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(SEAL, SealEntityModel::getTexturedModelData);
    }
}
