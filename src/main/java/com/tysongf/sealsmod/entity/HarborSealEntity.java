package com.tysongf.sealsmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class HarborSealEntity extends SealEntity {
    public HarborSealEntity(EntityType<? extends SealEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return new HarborSealEntity(com.tysongf.sealsmod.ModEntities.HARBOR_SEAL, world);
    }

    public static boolean canSpawn(EntityType<? extends HarborSealEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, net.minecraft.util.math.random.Random random) {
        return (world.getFluidState(pos).isIn(net.minecraft.registry.tag.FluidTags.WATER) ||
                world.getBlockState(pos.down()).isOpaqueFullCube(world, pos.down()))
                && world.getBaseLightLevel(pos, 0) > 8;
    }
}
