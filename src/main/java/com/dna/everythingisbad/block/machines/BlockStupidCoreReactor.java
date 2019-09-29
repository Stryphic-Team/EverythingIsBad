package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.gui.GuiHandler;
import com.dna.everythingisbad.tile.generators.TileStupidCoreReactor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockStupidCoreReactor extends BlockGeneratorBase {
    public BlockStupidCoreReactor(String name) {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileStupidCoreReactor();
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (!(te instanceof TileStupidCoreReactor)) {
            return false;
        }

        playerIn.openGui(Main.instance, GuiHandler.GUI_STUPID_CORE_REACTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add("This machine has a very explosive personality. Wink. Wink.");
    }
}
