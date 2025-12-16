package com.tysongf.sealsmod.entity;

import com.tysongf.sealsmod.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class SealEntity extends AnimalEntity {
    private static final TrackedData<Boolean> SUNBATHING = DataTracker.registerData(SealEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> MOISTNESS = DataTracker.registerData(SealEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private int sunbathingTime = 0;

    public SealEntity(EntityType<? extends SealEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SUNBATHING, false);
        builder.add(MOISTNESS, 2400);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimAroundGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.5));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.2, Ingredient.ofItems(Items.COD, Items.SALMON, Items.TROPICAL_FISH), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(5, new SunbatheGoal(this));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createSealAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.COD) || stack.isOf(Items.SALMON);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.isBreedingItem(itemStack)) {
            if (!this.isTamed()) {
                // Taming with fish
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
                if (this.random.nextInt(3) == 0) {
                    this.setOwner(player);
                    this.getWorld().sendEntityStatus(this, (byte) 7); // Heart particles
                    return ActionResult.SUCCESS;
                }
                this.getWorld().sendEntityStatus(this, (byte) 6); // Smoke particles
                return ActionResult.CONSUME;
            }
        }

        return super.interactMob(player, hand);
    }

    public boolean isSunbathing() {
        return this.dataTracker.get(SUNBATHING);
    }

    public void setSunbathing(boolean sunbathing) {
        this.dataTracker.set(SUNBATHING, sunbathing);
    }

    @Override
    public void tick() {
        super.tick();

        // Update moistness
        if (this.isInsideWaterOrBubbleColumn() || this.isWet()) {
            this.dataTracker.set(MOISTNESS, 2400);
        } else if (this.dataTracker.get(MOISTNESS) > 0) {
            this.dataTracker.set(MOISTNESS, this.dataTracker.get(MOISTNESS) - 1);
        }

        // Handle sunbathing
        if (this.isSunbathing()) {
            sunbathingTime++;
            if (sunbathingTime > 200 && this.random.nextInt(100) == 0) {
                this.setSunbathing(false);
                sunbathingTime = 0;
            }
        }
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_DOLPHIN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_DOLPHIN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_DOLPHIN_DEATH;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Sunbathing", this.isSunbathing());
        nbt.putInt("Moistness", this.dataTracker.get(MOISTNESS));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setSunbathing(nbt.getBoolean("Sunbathing"));
        this.dataTracker.set(MOISTNESS, nbt.getInt("Moistness"));
    }

    // Custom sunbathing goal
    static class SunbatheGoal extends Goal {
        private final SealEntity seal;
        private BlockPos targetPos;

        public SunbatheGoal(SealEntity seal) {
            this.seal = seal;
        }

        @Override
        public boolean canStart() {
            if (seal.isInsideWaterOrBubbleColumn()) {
                return false;
            }
            if (seal.isSunbathing()) {
                return false;
            }
            if (seal.getRandom().nextInt(200) != 0) {
                return false;
            }

            // Look for a suitable spot on shore
            BlockPos pos = seal.getBlockPos();
            for (int i = 0; i < 10; i++) {
                BlockPos randomPos = pos.add(
                    seal.getRandom().nextInt(10) - 5,
                    0,
                    seal.getRandom().nextInt(10) - 5
                );

                if (seal.getWorld().isSkyVisible(randomPos) &&
                    seal.getWorld().isAir(randomPos.up()) &&
                    !seal.getWorld().getFluidState(randomPos).isEmpty() == false) {
                    targetPos = randomPos;
                    return true;
                }
            }
            return false;
        }

        @Override
        public void start() {
            if (targetPos != null) {
                seal.getNavigation().startMovingTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1.0);
            }
        }

        @Override
        public boolean shouldContinue() {
            return !seal.isSunbathing() && targetPos != null &&
                   seal.getBlockPos().isWithinDistance(targetPos, 2.0);
        }

        @Override
        public void tick() {
            if (targetPos != null && seal.getBlockPos().isWithinDistance(targetPos, 2.0)) {
                seal.setSunbathing(true);
            }
        }
    }

    // Custom swimming goal for seals
    static class SwimAroundGoal extends SwimGoal {
        private final SealEntity seal;

        public SwimAroundGoal(SealEntity seal) {
            super(seal);
            this.seal = seal;
        }

        @Override
        public boolean canStart() {
            return seal.isInsideWaterOrBubbleColumn() && seal.getY() < seal.getWorld().getSeaLevel() - 2;
        }
    }
}
