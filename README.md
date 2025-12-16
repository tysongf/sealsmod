# Seals Mod for Minecraft 1.21 (Fabric)

A Minecraft mod that adds three types of tameable seals to the game!

## Features

### Seal Types
- **Arctic Seal**: Spawns in snowy and frozen biomes (snowy beaches, frozen oceans, ice spikes)
- **Harbor Seal**: Spawns in temperate ocean and beach biomes
- **Leopard Seal**: Spawns in cold/frozen oceans (rarer, more aggressive, hunts fish)

### Behaviors
- **Swimming**: Seals are excellent swimmers and prefer water
- **Sunbathing**: Seals will occasionally come ashore to sunbathe
- **Hunting**: Leopard seals actively hunt fish (cod, salmon, tropical fish)
- **Tameable**: Feed any seal cod or salmon to tame it (33% chance per fish)
- **Breedable**: Tamed seals can be bred with cod or salmon

### Items
- **Raw Seal Meat**: Dropped by seals, can be cooked
- **Cooked Seal Meat**: Restores 8 hunger (4 food points) and 0.8 saturation
- **Seal Pelt**: Dropped by seals, can be used as a leather alternative
- **Spawn Eggs**: Available in creative mode for each seal type

## Building the Mod

1. Make sure you have Java 21 installed:
   ```bash
   java --version
   ```

2. Build the mod using Gradle:
   ```bash
   ./gradlew build
   ```

3. The compiled JAR will be in `build/libs/sealsmod-1.0.0.jar`

## Installation

1. Install Fabric Loader for Minecraft 1.21
2. Install Fabric API
3. Place the mod JAR in your `.minecraft/mods` folder
4. Launch Minecraft with the Fabric profile

## Development Setup

1. Import the project into IntelliJ IDEA or Eclipse
2. Run `./gradlew genSources` to generate Minecraft sources
3. Use `./gradlew runClient` to test the mod

## Customization

### Textures
The mod includes textures. You can replace them with custom textures:
- Entity textures: `src/main/resources/assets/sealsmod/textures/entity/`
- Item textures: `src/main/resources/assets/sealsmod/textures/item/`

Entity textures should be 64x32 pixels in the standard Minecraft mob format.
Item textures should be 16x16 pixels.

### Spawn Rates
Edit `ModSpawning.java` to adjust spawn weights and group sizes.

### Behaviors
Edit `SealEntity.java` to modify AI goals and behaviors.
