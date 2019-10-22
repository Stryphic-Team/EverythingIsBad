package com.dna.everythingisbad.tile.misc;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;

public class TileAutomatedTellerMachine extends TileDeviceBase {
    public TileAutomatedTellerMachine() {
        super("automated_teller_machine");
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(ModBlocks.AUTOMATED_TELLER_MACHINE.getLocalizedName());
    }

    @Override
    public void update() {

    }
}
