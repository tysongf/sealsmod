package com.tysongf.sealsmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SalmonEntity;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class LeopardSealEntity extends SealEntity {
    public LeopardSealEntity(EntityType<? extends SealEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        // Leopard seals are more aggressive and hunt fish
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2, false));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, CodEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, SalmonEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, TropicalFishEntity.class, true));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return new LeopardSealEntity(com.tysongf.sealsmod.ModEntities.LEOPARD_SEAL, world);
    }

    public static boolean canSpawn(EntityType<? extends LeopardSealEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, net.minecraft.util.math.random.Random random) {
        return (world.getFluidState(pos).isIn(net.minecraft.registry.tag.FluidTags.WATER) ||
                world.getBlockState(pos.down()).isOpaqueFullCube(world, pos.down()))
                && world.getBaseLightLevel(pos, 0) > 8;
    }
}
