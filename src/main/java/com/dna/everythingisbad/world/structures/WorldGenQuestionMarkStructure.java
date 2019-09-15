package com.dna.everythingisbad.world.structures;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenQuestionMarkStructure extends WorldGenerator {
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        WorldServer worldServer = (WorldServer) worldIn;
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.get(minecraftServer,new ResourceLocation(Reference.MOD_ID,"question_mark_structure"));
        PlacementSettings settings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(getRotation()).setIgnoreStructureBlock(false);
        BlockPos boundingBox = template.getSize();
        int structureIndex = rand.nextInt(5);
        settings.setBoundingBox(
                new StructureBoundingBox(
                        position.getX(),
                        position.getY(),
                        position.getZ()+(structureIndex),
                        position.getX()+5,
                        position.getY(),
                        position.getZ()+(structureIndex)
                )
        );

        template.addBlocksToWorld(worldIn, position, settings);
        return true;
    }
    public static Rotation getRotation() {
        Random random = new Random();

        if (random.nextInt(2) == 1) {
            return Rotation.CLOCKWISE_180;
        } else {
            return Rotation.NONE;
        }
    }
}
