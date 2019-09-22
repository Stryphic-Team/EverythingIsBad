package com.dna.everythingisbad.world.structures;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenWoolBlock extends WorldGenStructureBase {

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        WorldServer worldServer = (WorldServer) worldIn;
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.get(minecraftServer,new ResourceLocation(Reference.MOD_ID,"wool_block"));

        PlacementSettings settings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(getRotation()).setIgnoreStructureBlock(false);
        template.addBlocksToWorld(worldIn, position, settings);
        return true;
    }
}
