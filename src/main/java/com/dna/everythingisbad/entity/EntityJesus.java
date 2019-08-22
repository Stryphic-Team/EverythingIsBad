package com.dna.everythingisbad.entity;


import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModLootTables;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

//TODO make the entity quote bible quotes with tts
public class EntityJesus extends EntityZombie{

    public EntityJesus(World worldIn) {
        super(worldIn);
        setAIMoveSpeed(2f);
    }
    @Override
    protected ResourceLocation getLootTable()
    {
        Main.logger.info(ModLootTables.ENTITY_JESUS_LOOT.getResourcePath());
        return ModLootTables.ENTITY_JESUS_LOOT;
    }
}