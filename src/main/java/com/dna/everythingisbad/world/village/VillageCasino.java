package com.dna.everythingisbad.world.village;

import com.dna.everythingisbad.world.structures.WorldGenVillageCasino;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

import java.util.List;
import java.util.Random;

public class VillageCasino extends Village {
    StructureBoundingBox startPos;
    public VillageCasino(){
    }
    public VillageCasino(StructureVillagePieces.Start villagePiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, EnumFacing facing)
    {
        super(villagePiece, par2);

        this.setCoordBaseMode(facing);
        this.boundingBox = par4StructureBoundingBox;
    }
    @Override
    public boolean addComponentParts(World worldIn, java.util.Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
        WorldGenVillageCasino villageCasino = new WorldGenVillageCasino();
        villageCasino.generate(worldIn,randomIn,new BlockPos(structureBoundingBoxIn.minX,structureBoundingBoxIn.minY,structureBoundingBoxIn.minZ));
        return true;
    }
    public static class VillageManager implements IVillageCreationHandler
    {
        @Override
        public Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5)
        {

            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 12, 15, 10, facing);
            return (!canVillageGoDeeper(box))||(StructureComponent.findIntersecting(pieces, box)!=null)?null: new VillageCasino(startPiece, p5, random, box, facing);
        }

        @Override
        public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i)
        {
            return new StructureVillagePieces.PieceWeight(VillageCasino.class, 15, MathHelper.getInt(random, 0+i, 1+i));
        }

        @Override
        public Class<?> getComponentClass()
        {
            return VillageCasino.class;
        }
    }

}
