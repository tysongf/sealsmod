# Seals Mod - Complete Project Structure

## Directory Structure

```
sealsmod/
├── build.gradle                 # Gradle build configuration
├── gradle.properties            # Version and dependency configuration
├── settings.gradle              # Gradle settings
├── gradlew                      # Gradle wrapper (Unix)
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
├── README.md                    # Documentation
├── create_textures.py          # Texture generation script
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── tysongf/
        │           └── sealsmod/
        │               ├── SealsMod.java           # Main mod class
        │               ├── ModItems.java           # Item registration
        │               ├── ModEntities.java        # Entity registration
        │               ├── ModSpawning.java        # Spawn configuration
        │               ├── client/
        │               │   └── SealsModClient.java # Client initializer
        │               │   └── render/
        │               │       ├── SealEntityModel.java    # 3D model
        │               │       ├── SealEntityRenderer.java # Renderer
        │               │       └── ModModelLayers.java     # Model layer registration
        │               └── entity/
        │                   ├── SealEntity.java         # Base seal class
        │                   ├── ArcticSealEntity.java   # Arctic variant
        │                   ├── HarborSealEntity.java   # Harbor variant
        │                   └── LeopardSealEntity.java  # Leopard variant
        └── resources/
            ├── fabric.mod.json              # Mod metadata
            ├── assets/
            │   └── sealsmod/
            │       ├── lang/
            │       │   └── en_us.json       # English translations
            │       ├── models/
            │       │   └── item/
            │       │       ├── raw_seal_meat.json
            │       │       ├── cooked_seal_meat.json
            │       │       ├── seal_pelt.json
            │       │       └── *_spawn_egg.json
            │       └── textures/
            │           ├── entity/
            │           │   ├── arctic_seal.png
            │           │   ├── harbor_seal.png
            │           │   └── leopard_seal.png
            │           └── item/
            │               ├── raw_seal_meat.png
            │               ├── cooked_seal_meat.png
            │               └── seal_pelt.png
            └── data/
                └── sealsmod/
                    ├── recipes/
                    │   ├── cooked_seal_meat.json
                    │   ├── cooked_seal_meat_from_smoking.json
                    │   └── cooked_seal_meat_from_campfire.json
                    └── loot_tables/
                        └── entities/
```

## Key Files Explained

### Java Classes

**SealsMod.java**: Main entry point that initializes the mod
**ModItems.java**: Registers all items (meat, pelts, spawn eggs)
**ModEntities.java**: Registers all seal entity types
**ModSpawning.java**: Configures where and how seals spawn

**SealEntity.java**: Base class with all common seal behaviors:
- Swimming AI
- Sunbathing behavior
- Taming with fish
- Breeding mechanics
- Custom drops (meat and pelts)

**ArcticSealEntity.java**: White/light gray seals for frozen biomes
**HarborSealEntity.java**: Brown/tan seals for temperate oceans
**LeopardSealEntity.java**: Dark seals that hunt fish aggressively

**SealEntityModel.java**: 3D model definition (body, head, tail, flippers)
**SealEntityRenderer.java**: Handles rendering and texture selection

### Resource Files

**fabric.mod.json**: Mod metadata and entry points
**en_us.json**: English translations for all items and entities
**Item models**: JSON files defining how items appear in inventory
**Recipes**: Smelting, smoking, and campfire cooking for seal meat
**Textures**: PNG files for entities and items (currently placeholders)

## Getting Started

1. **Build the mod**:
   ```bash
   ./gradlew build
   ```

2. **Run in development**:
   ```bash
   ./gradlew runClient
   ```

3. **Install in Minecraft**:
   - Copy `build/libs/sealsmod-1.0.0.jar` to your mods folder
   - Requires Fabric Loader and Fabric API for Minecraft 1.21

## Customization Tips

### Change spawn rates
Edit `ModSpawning.java`, modify the weight values (currently 8 for Arctic/Harbor, 4 for Leopard)

### Adjust taming chance
Edit `SealEntity.java`, find `if (this.random.nextInt(3) == 0)` and change the 3 to another number (higher = harder to tame)

### Modify drops
Edit `SealEntity.java`, find the `dropLoot` method and adjust item amounts

### Create custom textures
Replace the PNG files in `textures/entity/` and `textures/item/` with your own 64x32 and 16x16 textures respectively

## Next Steps

- Create better textures (hire an artist or use tools like Blockbench)
- Add seal sounds (currently using dolphin sounds as placeholders)
- Add more seal variants or behaviors
- Create a Creative tab for the mod items
- Add configuration file support for spawn rates and behavior tuning
