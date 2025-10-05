package net.liam.biomsofinfinity.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.liam.biomsofinfinity.Biomsofinfinity;
import net.liam.biomsofinfinity.content.boss.BaseBossEntity;
import net.liam.biomsofinfinity.content.boss.ShadowKingEntity;
import net.liam.biomsofinfinity.entity.CrystalGolemEntity;
import net.liam.biomsofinfinity.entity.CrystalSpiderEntity;
import net.liam.biomsofinfinity.entity.MistSpiritEntity;
import net.liam.biomsofinfinity.entity.MistWolfEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Registers all entities, including the new boss framework.
 */
public final class ModEntities {

    private ModEntities() {
    }

    public static final String SHADOW_KING_ID = "shadow_king";

    public static final EntityType<MistSpiritEntity> MIST_SPIRIT = register("mist_spirit",
            EntityType.Builder.create(MistSpiritEntity::new, SpawnGroup.MONSTER).dimensions(0.6f, 1.95f));

    public static final EntityType<MistWolfEntity> MIST_WOLF = register("mist_wolf",
            EntityType.Builder.create(MistWolfEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 0.85f));

    public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM = register("crystal_golem",
            EntityType.Builder.create(CrystalGolemEntity::new, SpawnGroup.MONSTER).dimensions(1.4f, 2.7f));

    public static final EntityType<CrystalSpiderEntity> CRYSTAL_SPIDER = register("crystal_spider",
            EntityType.Builder.create(CrystalSpiderEntity::new, SpawnGroup.MONSTER).dimensions(0.7f, 0.5f));

    public static final EntityType<ShadowKingEntity> SHADOW_KING = register(SHADOW_KING_ID,
            EntityType.Builder.create(ShadowKingEntity::new, SpawnGroup.MONSTER).dimensions(1.0f, 3.2f).fireImmune());

    private static <T extends net.minecraft.entity.Entity> EntityType<T> register(String path, EntityType.Builder<T> builder) {
        Identifier id = Identifier.of(Biomsofinfinity.MOD_ID, path);
        if (Registries.ENTITY_TYPE.getIds().contains(id)) {
            return (EntityType<T>) Registries.ENTITY_TYPE.get(id);
        }
        return Registry.register(Registries.ENTITY_TYPE, id, builder.build(path));
    }

    public static void bootstrap() {
        FabricDefaultAttributeRegistry.register(MIST_SPIRIT, MistSpiritEntity.createMistSpiritAttributes());
        FabricDefaultAttributeRegistry.register(MIST_WOLF, MistWolfEntity.createMistWolfAttributes());
        FabricDefaultAttributeRegistry.register(CRYSTAL_GOLEM, CrystalGolemEntity.createCrystalGolemAttributes());
        FabricDefaultAttributeRegistry.register(CRYSTAL_SPIDER, CrystalSpiderEntity.createCrystalSpiderAttributes());
        FabricDefaultAttributeRegistry.register(SHADOW_KING, BaseBossEntity.createBossAttributes());
    }
}
