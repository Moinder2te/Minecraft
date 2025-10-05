package net.liam.biomsofinfinity.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.liam.biomsofinfinity.Biomsofinfinity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<MistSpiritEntity> MIST_SPIRIT = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Biomsofinfinity.MOD_ID, "mist_spirit"),
            EntityType.Builder.create(MistSpiritEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6f, 1.95f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Biomsofinfinity.MOD_ID, "mist_spirit"))));

    public static final EntityType<MistWolfEntity> MIST_WOLF = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Biomsofinfinity.MOD_ID, "mist_wolf"),
            EntityType.Builder.create(MistWolfEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 0.85f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Biomsofinfinity.MOD_ID, "mist_wolf"))));

    public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Biomsofinfinity.MOD_ID, "crystal_golem"),
            EntityType.Builder.create(CrystalGolemEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1.4f, 2.7f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Biomsofinfinity.MOD_ID, "crystal_golem"))));

    public static final EntityType<CrystalSpiderEntity> CRYSTAL_SPIDER = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Biomsofinfinity.MOD_ID, "crystal_spider"),
            EntityType.Builder.create(CrystalSpiderEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.7f, 0.5f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Biomsofinfinity.MOD_ID, "crystal_spider"))));

    public static void registerModEntities() {
        Biomsofinfinity.LOGGER.info("Registering ModEntities for " + Biomsofinfinity.MOD_ID);

        FabricDefaultAttributeRegistry.register(MIST_SPIRIT, MistSpiritEntity.createMistSpiritAttributes());
        FabricDefaultAttributeRegistry.register(MIST_WOLF, MistWolfEntity.createMistWolfAttributes());
        FabricDefaultAttributeRegistry.register(CRYSTAL_GOLEM, CrystalGolemEntity.createCrystalGolemAttributes());
        FabricDefaultAttributeRegistry.register(CRYSTAL_SPIDER, CrystalSpiderEntity.createCrystalSpiderAttributes());
    }
}
