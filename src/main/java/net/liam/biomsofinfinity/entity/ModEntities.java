package net.liam.biomsofinfinity.entity;

import net.liam.biomsofinfinity.content.boss.ShadowKingEntity;
import net.minecraft.entity.EntityType;

public class ModEntities {

    public static final EntityType<MistSpiritEntity> MIST_SPIRIT = net.liam.biomsofinfinity.registry.ModEntities.MIST_SPIRIT;
    public static final EntityType<MistWolfEntity> MIST_WOLF = net.liam.biomsofinfinity.registry.ModEntities.MIST_WOLF;
    public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM = net.liam.biomsofinfinity.registry.ModEntities.CRYSTAL_GOLEM;
    public static final EntityType<CrystalSpiderEntity> CRYSTAL_SPIDER = net.liam.biomsofinfinity.registry.ModEntities.CRYSTAL_SPIDER;
    public static final EntityType<ShadowKingEntity> SHADOW_KING = net.liam.biomsofinfinity.registry.ModEntities.SHADOW_KING;

    public static void registerModEntities() {
        net.liam.biomsofinfinity.registry.ModEntities.bootstrap();
    }
}
