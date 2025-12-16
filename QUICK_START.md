# Seals Mod - Quick Start Guide

## What You Have

A complete Fabric mod for Minecraft 1.21 that adds three types of tameable seals!

## Building the Mod

1. Navigate to the mod directory:
   ```bash
   cd sealsmod
   ```

2. Make the Gradle wrapper executable (if on Linux/Mac):
   ```bash
   chmod +x gradlew
   ```

3. Build the mod:
   ```bash
   ./gradlew build
   ```

   The compiled JAR will be in: `build/libs/sealsmod-1.0.0.jar`

## Testing in Development

Run the game directly with the mod loaded:
```bash
./gradlew runClient
```

This will download Minecraft 1.21, set up the environment, and launch the game with your mod loaded.

## Installing in Your Minecraft

1. Install Fabric Loader for Minecraft 1.21 from https://fabricmc.net/use/
2. Download Fabric API from https://modrinth.com/mod/fabric-api
3. Copy both Fabric API and your `sealsmod-1.0.0.jar` to `.minecraft/mods/`
4. Launch Minecraft with the Fabric profile

## Finding Seals In-Game

- **Arctic Seals**: Look in frozen oceans, snowy beaches, ice spikes biomes
- **Harbor Seals**: Look on regular beaches and in oceans
- **Leopard Seals**: Rarer, found in cold/frozen oceans

Or use Creative mode and get spawn eggs from the Creative inventory!

## Taming Seals

1. Find a wild seal
2. Hold cod or salmon
3. Right-click/interact with the seal
4. Repeat until hearts appear (33% chance per fish)
5. Tamed seals can be bred with more fish!

## What Seals Do

- **Swim** gracefully in water
- **Hunt fish** (especially Leopard Seals)
- **Sunbathe** on beaches when they feel like it
- **Drop items** when defeated: Raw Seal Meat and Seal Pelts
- **Follow** their owner when tamed

## Items Added

- **Raw Seal Meat** - Can be cooked in furnace, smoker, or campfire
- **Cooked Seal Meat** - Restores 8 hunger (4 food icons)
- **Seal Pelt** - Crafting material, can be used like leather

## Troubleshooting

**Build fails?**
- Make sure Java 21 is installed: `java --version`
- Delete `.gradle` folder and try again

**Mod doesn't load?**
- Check you have Fabric Loader AND Fabric API installed
- Check the Minecraft version matches (1.21)
- Look in `.minecraft/logs/latest.log` for error messages

**Seals don't spawn?**
- They spawn naturally but may take time
- Use `/locate biome` to find the right biomes
- Use spawn eggs in Creative mode for testing

## Customizing Your Mod

### Change the Mod ID
Replace "sealsmod" and "tysongf" throughout the project:
- Package names: `com.tysongf.sealsmod`
- Mod ID in fabric.mod.json
- Resource paths

### Better Textures
Replace placeholder textures in:
- `src/main/resources/assets/sealsmod/textures/entity/` (64x32 pixels)
- `src/main/resources/assets/sealsmod/textures/item/` (16x16 pixels)

Recommended tool: Blockbench (free 3D modeling tool for Minecraft)

### Add More Features
Check out the Fabric Wiki: https://fabricmc.net/wiki/

## Files Reference

- **Main code**: `src/main/java/com/tysongf/sealsmod/`
- **Textures**: `src/main/resources/assets/sealsmod/textures/`
- **Translations**: `src/main/resources/assets/sealsmod/lang/en_us.json`
- **Recipes**: `src/main/resources/data/sealsmod/recipes/`
- **Documentation**: `README.md` and `PROJECT_STRUCTURE.md`

## Need Help?

- Fabric Discord: https://discord.gg/v6v4pMv
- Fabric Wiki: https://fabricmc.net/wiki/
- Minecraft Forge Forums (concepts apply to Fabric too)

## Have Fun!

Your seals mod is ready to go! Build it, test it, customize it, and share it with friends!
