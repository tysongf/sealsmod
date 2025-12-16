package com.tysongf.sealsmod.client;

import com.tysongf.sealsmod.ModEntities;
import com.tysongf.sealsmod.client.render.ModModelLayers;
import com.tysongf.sealsmod.client.render.SealEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SealsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelLayers.registerModelLayers();
        
        EntityRendererRegistry.register(ModEntities.ARCTIC_SEAL, SealEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.HARBOR_SEAL, SealEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LEOPARD_SEAL, SealEntityRenderer::new);
    }
}
