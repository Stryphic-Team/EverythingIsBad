package com.dna.everythingisbad.world.structures;

import com.dna.everythingisbad.init.ModLootTables;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.DungeonHooks;

import java.util.Map;
import java.util.Random;

public class WorldGenTwinTowers extends WorldGenStructureBase {
    ResourceLocation[] lootTables;

    public WorldGenTwinTowers(){
        lootTables = new ResourceLocation[]{
                LootTableList.CHESTS_SPAWN_BONUS_CHEST,
                LootTableList.CHESTS_END_CITY_TREASURE,
                LootTableList.CHESTS_SIMPLE_DUNGEON,
                LootTableList.CHESTS_VILLAGE_BLACKSMITH,
                LootTableList.CHESTS_ABANDONED_MINESHAFT,
                LootTableList.CHESTS_NETHER_BRIDGE,
                LootTableList.CHESTS_STRONGHOLD_LIBRARY,
                LootTableList.CHESTS_STRONGHOLD_CROSSING,
                LootTableList.CHESTS_STRONGHOLD_CORRIDOR,
                LootTableList.CHESTS_DESERT_PYRAMID,
                LootTableList.CHESTS_JUNGLE_TEMPLE,
                LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER,
                ModLootTables.CHEST_EVERYTHINGBAD_LOOT
        };
    }
    @Override
    public boolean generate(World world, Random rand, BlockPos position) {

        WorldServer worldServer = (WorldServer) world;
        MinecraftServer minecraftServer = world.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.get(minecraftServer,new ResourceLocation(Reference.MOD_ID,"towers"));

        PlacementSettings settings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(getRotation()).setIgnoreStructureBlock(false);
        BlockPos structureSize = template.getSize();
        template.addBlocksToWorld(world, position, settings);
        Map<BlockPos,String> dataBlocks = template.getDataBlocks(position,settings);
        Object[] keys = dataBlocks.keySet().toArray();
        for(int i = 0;i<keys.length;i++){
            BlockPos blockPos = (BlockPos) keys[i];
            String type = dataBlocks.get(blockPos);
            //Removes the structure blocks
            world.setBlockToAir(blockPos);
            if(type.equals("mob_spawn")){
                //gets the tile entity of the mob spawner and sets it to a random spawner
                TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner) world.getTileEntity(blockPos.down());
                mobSpawner.getSpawnerBaseLogic().setEntityId(DungeonHooks.getRandomDungeonMob(rand));
                int rand_x = rand.nextInt(16) - (16/2);
                int rand_z = rand.nextInt(16) - (16/2);
                if(rand.nextFloat() < 0.1){
                    EntityTNTPrimed stupidTNT = new EntityTNTPrimed(world);
                    stupidTNT.setPosition(rand_x + blockPos.getX(),blockPos.getY(),rand_z + blockPos.getZ());
                    world.spawnEntity(stupidTNT);
                }

            }
            if(type.equals("chest")){
                TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos.down());
                ResourceLocation randomLootTable = lootTables[rand.nextInt(lootTables.length)];
                chest.setLootTable(randomLootTable, rand.nextLong());
            }
        }



        return true;
    }
}
