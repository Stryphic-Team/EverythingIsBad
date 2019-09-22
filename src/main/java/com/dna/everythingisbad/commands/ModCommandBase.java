package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.init.ModCommands;
import net.minecraft.command.CommandBase;

public abstract class ModCommandBase extends CommandBase {
    protected String name;
    public ModCommandBase(String name){
        this.name = name;
        ModCommands.COMMANDS.add(this);
    }

    @Override
    public String getName() {
        return name;
    }
}
