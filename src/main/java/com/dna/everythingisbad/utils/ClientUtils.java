package com.dna.everythingisbad.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
@SideOnly(Side.CLIENT)
public class ClientUtils {
    private static MinecraftServer SERVER_INSTANCE = FMLCommonHandler.instance().getMinecraftServerInstance();
    public static void SpawnItem(ItemStack itemStack){
        List<EntityPlayerMP> player_list = SERVER_INSTANCE.getPlayerList().getPlayers();
        for(EntityPlayerMP player:player_list){
            World world = SERVER_INSTANCE.getWorld(player.dimension);
            player.inventory.addItemStackToInventory(itemStack);
        }
    }

}
