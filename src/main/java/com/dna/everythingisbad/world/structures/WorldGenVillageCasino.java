package com.dna.everythingisbad.world.structures;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Map;
import java.util.Random;

public class WorldGenVillageCasino extends WorldGenStructureBase {
    public StructureBoundingBox boundingBox;
    public WorldGenVillageCasino(){

    }



    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        position = position.add(1,0,1);
        WorldServer worldServer = (WorldServer) worldIn;
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.get(minecraftServer,new ResourceLocation(Reference.MOD_ID,"casino"));

        PlacementSettings settings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(getRotation()).setIgnoreStructureBlock(false);

        boundingBox = settings.getBoundingBox();
        BlockPos startPos = new BlockPos(position.getX(),worldIn.getTopSolidOrLiquidBlock(position).getY(),position.getZ());
        if(!isGroundAir(
                worldIn,
                startPos.down(),
                startPos.getX()+template.getSize().getX(),
                startPos.getZ()+template.getSize().getZ())
        ){
            template.addBlocksToWorld(worldIn, startPos, settings);
            Map<BlockPos,String> dataBlocks = template.getDataBlocks(position,settings);
            Object[] keys = dataBlocks.keySet().toArray();
            for(int i = 0;i<keys.length;i++){
                BlockPos blockPos = (BlockPos) keys[i];
                String type = dataBlocks.get(blockPos);
                worldIn.setBlockToAir(blockPos);
                if(type.equals("slot_machine")){
                    worldIn.setBlockState(blockPos, ModBlocks.SLOT_MACHINE.getDefaultState());

                }

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
