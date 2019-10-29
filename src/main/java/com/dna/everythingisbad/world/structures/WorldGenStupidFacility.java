package com.dna.everythingisbad.world.structures;

import com.dna.everythingisbad.gui.container.StupidCoreMachineContainer;
import com.dna.everythingisbad.init.ModLootTables;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
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
import net.minecraftforge.common.DungeonHooks;

import java.util.Map;
import java.util.Random;

public class WorldGenStupidFacility extends WorldGenStructureBase {

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        WorldServer worldServer = (WorldServer) world;
        MinecraftServer minecraftServer = world.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();

        // Add on Stupid Facility modules here!!!
        Template labTemplate = templateManager.get(minecraftServer, new ResourceLocation(Reference.MOD_ID, "stupid_facility_lab"));
        Template warehouseTemplate = templateManager.get(minecraftServer, new ResourceLocation(Reference.MOD_ID, "stupid_facility_warehouse"));
        Template greenhouseTemplate = templateManager.get(minecraftServer, new ResourceLocation(Reference.MOD_ID, "stupid_facility_green_house"));

        // And like, register them, I guess!!!
        Template[] STUPID_FACILITY_TEMPLATES = new Template[]{
                labTemplate, warehouseTemplate, greenhouseTemplate};

        // Picks one of the templates randomly
        int threeSidedDie = rand.nextInt(STUPID_FACILITY_TEMPLATES.length);
        Template currentTemplate = STUPID_FACILITY_TEMPLATES[threeSidedDie];

        PlacementSettings settings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(getRotation()).setIgnoreStructureBlock(false);
        BlockPos structureSize = currentTemplate.getSize();

        //BlockPos startPos = new BlockPos(position.getX(), position.getY(), position.getZ());
//        if (!isGroundAir(
//                world,
//                startPos.down(),
//                startPos.getX() + currentTemplate.getSize().getX(),
//                startPos.getZ() + currentTemplate.getSize().getZ())
//        ) {
        // Foundation
        for (int x = position.getX(); x < position.getX() + structureSize.getX(); x++){
            for (int z = position.getZ(); z < position.getZ() + structureSize.getZ(); z++){
                //BlockPos currentTopBlock = world.getTopSolidOrLiquidBlock(new BlockPos(x,0,z));
                int currentHeight = world.getHeight(x,z);

                if (currentHeight < position.getY()){
                    for (int y = currentHeight; y < position.getY(); y++){
                        world.setBlockState( new BlockPos( x, y, z ), Blocks.DIRT.getDefaultState() );
                    }
                }
            }
        }

        currentTemplate.addBlocksToWorld(world, position, settings);
        Map<BlockPos, String> dataBlocks = currentTemplate.getDataBlocks(position, settings);
        Object[] keys = dataBlocks.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            BlockPos blockPos = (BlockPos) keys[i];
            String type = dataBlocks.get(blockPos);
            //Removes the structure blocks
            world.setBlockToAir(blockPos);

            if (type.equals("chest")) {
                TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos.down());
                ResourceLocation randomLootTable = ModLootTables.CHEST_EVERYTHINGBAD_LOOT;
                chest.setLootTable(randomLootTable, rand.nextLong());
            }
            // TODO: Make unique loot table with "good stuff"
            if (type.equals("chest_bonus")) {
                TileEntityChest chest = (TileEntityChest) world.getTileEntity(blockPos.down());
                ResourceLocation randomLootTable = ModLootTables.CHEST_EVERYTHINGBAD_LOOT;
                chest.setLootTable(randomLootTable, rand.nextLong());
            }
        }
        return true;
    }

    public boolean isGroundAir(World world,BlockPos startPos,int xBound,int zBound){
        for(int x = startPos.getX();x < xBound;x++){
            for(int z = startPos.getZ();z < zBound;z++){
                if(world.getBlockState(new BlockPos(x,startPos.getY(),z)) == Blocks.AIR.getDefaultState()){
                    return true;
                }
            }
        }
        return false;
    }
}