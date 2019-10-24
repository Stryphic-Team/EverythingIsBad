package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.gui.GuiHandler;
import com.dna.everythingisbad.tile.TileDeviceBase;
import com.dna.everythingisbad.tile.generators.TileCoalGenerator;
import com.dna.everythingisbad.tile.processing.TileDryerMachine;
import com.dna.everythingisbad.tile.processing.TileIncubator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockIncubator extends BlockMachineBase {
    TileDeviceBase tileDeviceBase;
    public BlockIncubator(String name) {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        tileDeviceBase = new TileIncubator();
        return tileDeviceBase;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (!(te instanceof TileIncubator)) {
            return false;
        }

        playerIn.openGui(Main.instance, GuiHandler.GUI_INCUBATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
