package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.DiaricGeneratorContainer;
import com.dna.everythingisbad.gui.container.DryerMachineContainer;
import com.dna.everythingisbad.gui.container.StupidCoreMachineContainer;
import com.dna.everythingisbad.tile.TileDiaricGenerator;
import com.dna.everythingisbad.tile.TileDryerMachine;
import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    private static int guiId = 0;
    public static final int GUI_STUPID_CORE_MACHINE = guiId++;
    public static final int GUI_DIARIC_GENERATOR = guiId++;
    public static final int GUI_DRYER_MACHINE = guiId++;
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == GUI_STUPID_CORE_MACHINE) return new StupidCoreMachineContainer(player.inventory, (TileStupidCoreMachine)world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == GUI_DIARIC_GENERATOR) return new DiaricGeneratorContainer(player.inventory, (TileDiaricGenerator)world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == GUI_DRYER_MACHINE) return new DryerMachineContainer(player.inventory, (TileDryerMachine) world.getTileEntity(new BlockPos(x,y,z)));

        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == GUI_STUPID_CORE_MACHINE) return new StupidCoreMachineGuiContainer(player.inventory, (TileStupidCoreMachine) world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == GUI_DIARIC_GENERATOR) return new DiaricGeneratorGuiContainer(player.inventory, (TileDiaricGenerator) world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == GUI_DRYER_MACHINE) return new DryerMachineGuiContainer(player.inventory, (TileDryerMachine) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
