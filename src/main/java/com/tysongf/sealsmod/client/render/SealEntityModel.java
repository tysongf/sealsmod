package com.tysongf.sealsmod.client.render;

import com.tysongf.sealsmod.entity.SealEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class SealEntityModel extends EntityModel<SealEntity> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart flipperLeft;
    private final ModelPart flipperRight;

    public SealEntityModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.tail = root.getChild("tail");
        this.flipperLeft = root.getChild("flipper_left");
        this.flipperRight = root.getChild("flipper_right");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        // Body - main cylinder
        modelPartData.addChild("body", 
            ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-4F, -3F, -8F, 8F, 6F, 16F),
            ModelTransform.pivot(0F, 20F, 0F));

        // Head - slightly smaller box at front
        modelPartData.addChild("head",
            ModelPartBuilder.create()
                .uv(0, 22)
                .cuboid(-3F, -2.5F, -5F, 6F, 5F, 5F),
            ModelTransform.pivot(0F, 19.5F, -8F));

        // Tail/rear flippers - at the back
        modelPartData.addChild("tail",
            ModelPartBuilder.create()
                .uv(32, 0)
                .cuboid(-5F, 0F, 0F, 10F, 2F, 6F),
            ModelTransform.pivot(0F, 21F, 8F));

        // Front flippers
        modelPartData.addChild("flipper_left",
            ModelPartBuilder.create()
                .uv(0, 32)
                .cuboid(0F, 0F, -2F, 6F, 1F, 4F),
            ModelTransform.pivot(4F, 22F, -4F));

        modelPartData.addChild("flipper_right",
            ModelPartBuilder.create()
                .uv(0, 32)
                .cuboid(-6F, 0F, -2F, 6F, 1F, 4F),
            ModelTransform.pivot(-4F, 22F, -4F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(SealEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;

        // Swimming animation
        if (entity.isInsideWaterOrBubbleColumn()) {
            this.body.pitch = -0.2F;
            this.tail.yaw = MathHelper.cos(limbAngle * 0.6662F) * 0.4F * limbDistance;
            this.flipperLeft.roll = MathHelper.cos(animationProgress * 0.13F) * 0.5F;
            this.flipperRight.roll = -MathHelper.cos(animationProgress * 0.13F) * 0.5F;
        } else {
            // On land
            this.body.pitch = 0.1F;
            this.tail.yaw = 0F;
            
            if (entity.isSunbathing()) {
                // Relaxed pose when sunbathing
                this.flipperLeft.roll = 0.3F;
                this.flipperRight.roll = -0.3F;
            } else {
                // Wiggling when moving on land
                this.flipperLeft.roll = MathHelper.cos(limbAngle * 0.6662F) * 0.8F * limbDistance;
                this.flipperRight.roll = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 0.8F * limbDistance;
            }
        }
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        body.render(matrices, vertices, light, overlay, color);
        head.render(matrices, vertices, light, overlay, color);
        tail.render(matrices, vertices, light, overlay, color);
        flipperLeft.render(matrices, vertices, light, overlay, color);
        flipperRight.render(matrices, vertices, light, overlay, color);
    }
}
