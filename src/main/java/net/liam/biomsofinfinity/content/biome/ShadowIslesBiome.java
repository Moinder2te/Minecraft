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
 * Dark ritual islands anchored by shadowstone and thorny flora.
 */
public final class ShadowIslesBiome {

    private ShadowIslesBiome() {
    }

    public static Biome create(Registerable<Biome> context) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        GenerationSettings.LookupBackedBuilder generation = new GenerationSettings.LookupBackedBuilder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        generation.feature(net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.SHADOWSTONE_ORE_PLACED_KEY));
        generation.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.UMBRAL_THORN_PATCH_PLACED_KEY));
        generation.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.RITUAL_LANTERN_PLACED_KEY));

        return new Biome.Builder()
                .precipitation(false)
                .temperature(0.2f)
                .downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x1A1A1A)
                        .waterFogColor(0x0D0D0D)
                        .skyColor(0x222233)
                        .fogColor(0x1B1725)
                        .grassColor(0x2F4035)
                        .foliageColor(0x1F2A28)
                        .particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.008f))
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(new MusicSound(SoundEvents.MUSIC_DRAGON, 6000, 24000, true))
                        .build())
                .generationSettings(generation.build())
                .spawnSettings(spawnSettings.build())
                .build();
    }
}
