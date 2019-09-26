package com.dna.everythingisbad.init;

import com.dna.everythingisbad.commands.CommandGetBalance;
import com.dna.everythingisbad.commands.CommandGiveMoney;
import com.dna.everythingisbad.commands.ModCommandBase;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.ArrayList;

public class ModCommands {
    public static ArrayList<ModCommandBase> COMMANDS = new ArrayList<ModCommandBase>();
    public static final ModCommandBase COMMAND_GET_BALANCE = new CommandGetBalance("getbalance");
    public static final ModCommandBase COMMAND_GIVE_MONEY = new CommandGiveMoney("givemoney");
    public static void init(FMLServerStartingEvent event){
        for(ModCommandBase commandBase:COMMANDS){
           event.registerServerCommand(commandBase);
        }
    }
}
