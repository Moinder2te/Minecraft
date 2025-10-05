package net.liam.biomsofinfinity.content.biome;

import net.liam.biomsofinfinity.world.feature.ModPlacedFeatures;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

/**
 * Airy floating garden biome with soft petals and levitation vines.
 */
public final class FloatingGardensBiome {

    private FloatingGardensBiome() {
    }

    public static Biome create(Registerable<Biome> context) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        GenerationSettings.LookupBackedBuilder generation = new GenerationSettings.LookupBackedBuilder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        generation.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.DREAM_BLOSSOM_PATCH_PLACED_KEY));
        generation.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.SKY_VINE_PATCH_PLACED_KEY));

        return new Biome.Builder()
                .precipitation(false)
                .temperature(0.8f)
                .downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x74D3FF)
                        .waterFogColor(0x5AB5E0)
                        .skyColor(0xFFE3F7)
                        .fogColor(0xC5F5FF)
                        .grassColor(0xE0FFC7)
                        .foliageColor(0xF9B7FF)
                        .particleConfig(new BiomeParticleConfig(ParticleTypes.CLOUD, 0.01f))
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(new MusicSound(SoundEvents.MUSIC_DISC_FAR, 6000, 24000, false))
                        .build())
                .generationSettings(generation.build())
                .spawnSettings(spawnSettings.build())
                .build();
    }
}
