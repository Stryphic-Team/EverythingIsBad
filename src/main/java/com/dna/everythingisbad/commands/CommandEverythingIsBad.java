package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.init.ModCommands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandEverythingIsBad extends CommandBase {

    String name;
    public CommandEverythingIsBad(String name) {
        this.name = name;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/everthingisbad <sub command>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        boolean executed = false;
        for(ModCommandBase command: ModCommands.COMMANDS){
            if(args.length != 0 && command.getName().equals(args[0])){
                String[] newArgs = new String[args.length - 1];
                for(int i = 1;i<args.length;i++){
                    newArgs[i - 1] = args[i];
                }
                if(command.checkPermission(server,sender)) {
                    command.execute(server, sender, newArgs);
                }
                executed = true;
            }
            for(String alias:command.getAliases()){
                if(args.length != 0 && alias.equals(args[0])){
                    String[] newArgs = new String[args.length - 1];
                    for(int i = 1;i<args.length;i++){
                        newArgs[i - 1] = args[i];
                    }
                    if(command.checkPermission(server,sender)) {
                        command.execute(server, sender, newArgs);
                    }
                    executed = true;
                }
            }

        }
        if(!executed) {
            String[] newArgs = new String[0];
            ModCommands.COMMAND_HELP.execute(server, sender, newArgs);
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        ArrayList<String> completions = new ArrayList<String>();
        for(ModCommandBase command: ModCommands.COMMANDS){
            if(args.length > 0){
                if(command.getName().startsWith(args[0])) {
                    if(command.checkPermission(server,sender)) {
                        completions.add(command.getName());
                    }
                }
                for(String alias:command.getAliases()){
                    if(alias.startsWith(args[0])) {
                        if(command.checkPermission(server,sender)) {
                            completions.add(alias);
                        }
                    }
                }
            }else{
                completions.add(command.getName());
            }
            if(command.getName().equals(args[0])){
                String[] newArgs = new String[args.length - 1];
                for(int i = 1;i<args.length;i++){
                    newArgs[i - 1] = args[i];
                }
//                completions = new ArrayList<String>();
                return command.getTabCompletions(server,server,newArgs,targetPos);
            }
        }

        return completions;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;

    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> alises = new ArrayList<String>();
        alises.add("eib");
        alises.add("bad");
        return alises;
    }
}
