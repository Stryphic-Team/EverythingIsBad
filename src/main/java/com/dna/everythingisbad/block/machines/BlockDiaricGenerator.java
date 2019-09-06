package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileDiaricGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDiaricGenerator extends BlockGeneratorBase {
    public BlockDiaricGenerator(String name) {
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileDiaricGenerator();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (!(te instanceof TileDiaricGenerator)) {
            return false;
        }

        playerIn.openGui(Main.instance, Reference.GUI_DIARIC_GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
