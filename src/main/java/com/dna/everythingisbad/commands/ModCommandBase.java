package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.init.ModCommands;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.command.CommandBase;

import java.util.ArrayList;

public abstract class ModCommandBase extends CommandBase {
    protected String name;
    private ArrayList<String> aliases = new ArrayList<String>();
    public ModCommandBase(String name){
        this.name = Reference.RESOURCE_PREFIX + name;
        ModCommands.COMMANDS.add(this);

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void addAlias(String alias){
        aliases.add(Reference.RESOURCE_PREFIX+alias);
    }
}
