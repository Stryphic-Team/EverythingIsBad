package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == Reference.GUI_STUPID_CORE_MACHINE) return new StupidCoreMachineContainer(player.inventory, (TileStupidCoreMachine)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == Reference.GUI_STUPID_CORE_MACHINE) return new StupidCoreMachineContainerGui(player.inventory, (TileStupidCoreMachine) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
