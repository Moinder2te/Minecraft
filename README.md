# Bioms of Infinity

Bioms of Infinity overhauls the End dimension with handcrafted biomes, structures, loot, and boss encounters. This repository now ships the first wave of Glowshroom Forest, Floating Gardens, and Shadow Isles content together with the Shadow King ritual.

## Developer Notes

- **Configuration**: The mod loads `config/biomsofinfinity.json5` on startup. Use `/boi config reload` to pick up changes without restarting. Boss behaviour is governed by the `bosses` section – by default they only spawn through altars.
- **Boss Commands**: `/boi boss enable|disable` toggles ritual summoning, `/boi boss resetcooldowns` wipes the cooldown state.
- **Datagen**: Run the Fabric datagen entrypoint to regenerate blockstates, models, loot tables, recipes, and advancements for the new content. Providers live under `net.liam.biomsofinfinity.datagen`.
- **Adding Biomes**: Create a builder in `content/biome`, add a `RegistryKey` in `ModBiomes`, wire placed features in `ModWorldGeneration`, and register weight defaults in `BOIConfig`.
- **Adding Bosses**: Extend `BaseBossEntity`, register an entity type in `registry/ModEntities`, and create an altar block that calls `BossCooldownManager`.
- **Assets**: To keep pull requests text-only, the PNG textures are not tracked. See `assets/bioms-of-infinity/textures/README.md` for the list of files to drop in locally before packaging a build.

## Building

Use the provided Gradle wrapper (`./gradlew build`) with Java 21. Run the datagen entrypoint to refresh data assets when adding new blocks or items.

## Troubleshooting

- **`Only Settings scripts can contain a pluginManagement {}`**: This happens when a `pluginManagement` block is added to `build.gradle`. Keep all plugin resolution rules inside `settings.gradle` (the repository already contains a configured block) and leave `build.gradle` with only the standard `plugins { ... }` declaration.
