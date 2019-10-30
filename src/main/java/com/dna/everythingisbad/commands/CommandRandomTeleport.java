package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandRandomTeleport extends ModCommandBase {

    public CommandRandomTeleport(String name) {
        super(name);
    }

    @Override
    public String getDocumentation() {
        return "Teleports you to a random location";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/everythingbad rtp";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender instanceof EntityPlayerMP){
            EntityPlayerMP entityPlayer = (EntityPlayerMP) sender;
            World world = server.getWorld((entityPlayer).dimension);
            int x = RandomUtils.fromRangeI(-2000000,2000000);
            int z = RandomUtils.fromRangeI(-2000000,2000000);
            int y = world.getTopSolidOrLiquidBlock(new BlockPos(x,0,z)).getY();
            entityPlayer.connection.setPlayerLocation(x, y, z,entityPlayer.cameraYaw,entityPlayer.cameraPitch);
            //entityPlayer.setPosition((double)x,(double)y,(double)z);
        }
    }
}
