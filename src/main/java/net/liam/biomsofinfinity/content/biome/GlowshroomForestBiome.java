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
 * Builder utility for the Glowshroom Forest biome.
 */
public final class GlowshroomForestBiome {

    private GlowshroomForestBiome() {
    }

    public static Biome create(Registerable<Biome> context) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        GenerationSettings.LookupBackedBuilder generation = new GenerationSettings.LookupBackedBuilder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        generation.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.GLOWSHROOM_PATCH_PLACED_KEY));
        generation.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.LUMINESCENT_MOSS_PATCH_PLACED_KEY));

        return new Biome.Builder()
                .precipitation(false)
                .temperature(0.6f)
                .downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x1F3D5B)
                        .waterFogColor(0x112233)
                        .skyColor(0x3F6BAA)
                        .fogColor(0x5BD8FF)
                        .grassColor(0x4ED9B7)
                        .foliageColor(0x7FEFE7)
                        .particleConfig(new BiomeParticleConfig(ParticleTypes.END_ROD, 0.00625f))
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(new MusicSound(SoundEvents.MUSIC_END, 6000, 24000, true))
                        .build())
                .generationSettings(generation.build())
                .spawnSettings(spawnSettings.build())
                .build();
    }
}
