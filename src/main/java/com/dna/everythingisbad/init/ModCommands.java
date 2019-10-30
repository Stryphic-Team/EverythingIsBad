package com.dna.everythingisbad.init;

import com.dna.everythingisbad.commands.*;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.ArrayList;

public class ModCommands {
    public static ArrayList<ModCommandBase> COMMANDS = new ArrayList<ModCommandBase>();
    public static final ModCommandBase COMMAND_GET_BALANCE = new CommandGetBalance("balance");
    public static final ModCommandBase COMMAND_GIVE_MONEY = new CommandGiveMoney("money");
    public static final ModCommandBase COMMAND_GET_STATISTICS = new CommandGetStatistics("statistics");
    public static final ModCommandBase COMMAND_RANDOM_TELEPORT = new CommandRandomTeleport("rtp");
    public static final ModCommandBase COMMAND_HELP = new CommandHelp("help");
    public static void init(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandEverythingIsBad("everythingisbad"));
    }
}
