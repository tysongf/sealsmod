package com.tysongf.sealsmod;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // Raw and cooked seal meat
    public static final Item RAW_SEAL_MEAT = registerItem("raw_seal_meat",
            new Item(new Item.Settings()
                    .food(new FoodComponent.Builder()
                            .nutrition(3)
                            .saturationModifier(0.3f)
                            .build())));

    public static final Item COOKED_SEAL_MEAT = registerItem("cooked_seal_meat",
            new Item(new Item.Settings()
                    .food(new FoodComponent.Builder()
                            .nutrition(8)
                            .saturationModifier(0.8f)
                            .build())));

    // Seal pelt (leather alternative)
    public static final Item SEAL_PELT = registerItem("seal_pelt",
            new Item(new Item.Settings()));

    // Spawn eggs
    public static final Item ARCTIC_SEAL_SPAWN_EGG = registerItem("arctic_seal_spawn_egg",
            new SpawnEggItem(ModEntities.ARCTIC_SEAL, 0xE8F4F8, 0x4A5A6A, new Item.Settings()));

    public static final Item HARBOR_SEAL_SPAWN_EGG = registerItem("harbor_seal_spawn_egg",
            new SpawnEggItem(ModEntities.HARBOR_SEAL, 0x8B7355, 0x4A3A2A, new Item.Settings()));

    public static final Item LEOPARD_SEAL_SPAWN_EGG = registerItem("leopard_seal_spawn_egg",
            new SpawnEggItem(ModEntities.LEOPARD_SEAL, 0x3A3A3A, 0xB8B8B8, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SealsMod.MOD_ID, name), item);
    }

    public static void registerItems() {
        SealsMod.LOGGER.info("Registering items for " + SealsMod.MOD_ID);
    }
}
