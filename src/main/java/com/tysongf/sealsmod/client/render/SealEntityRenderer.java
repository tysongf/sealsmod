package com.tysongf.sealsmod.client.render;

import com.tysongf.sealsmod.SealsMod;
import com.tysongf.sealsmod.entity.ArcticSealEntity;
import com.tysongf.sealsmod.entity.HarborSealEntity;
import com.tysongf.sealsmod.entity.LeopardSealEntity;
import com.tysongf.sealsmod.entity.SealEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class SealEntityRenderer extends MobEntityRenderer<SealEntity, SealEntityModel> {
    private static final Identifier ARCTIC_SEAL_TEXTURE = Identifier.of(SealsMod.MOD_ID, "textures/entity/arctic_seal.png");
    private static final Identifier HARBOR_SEAL_TEXTURE = Identifier.of(SealsMod.MOD_ID, "textures/entity/harbor_seal.png");
    private static final Identifier LEOPARD_SEAL_TEXTURE = Identifier.of(SealsMod.MOD_ID, "textures/entity/leopard_seal.png");

    public SealEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SealEntityModel(context.getPart(ModModelLayers.SEAL)), 0.5f);
    }

    @Override
    public Identifier getTexture(SealEntity entity) {
        if (entity instanceof ArcticSealEntity) {
            return ARCTIC_SEAL_TEXTURE;
        } else if (entity instanceof HarborSealEntity) {
            return HARBOR_SEAL_TEXTURE;
        } else if (entity instanceof LeopardSealEntity) {
            return LEOPARD_SEAL_TEXTURE;
        }
        return ARCTIC_SEAL_TEXTURE;
    }
}
