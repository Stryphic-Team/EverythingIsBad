package com.dna.everythingisbad.entityhandlers.handlers;

import cofh.core.init.CoreEnchantments;
import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PlayerSoulHandler extends PlayerHandlerBase {


    @Override
    public void playerInitialization(EntityPlayer player) {
        super.playerInitialization(player);
        ItemStack soulStack = new ItemStack(ModItems.SOUL_ITEM, 1, 0);
        NBTTagCompound soulCompound = new NBTTagCompound();
        soulCompound.setString("player_name", player.getDisplayNameString());
        soulStack.setTagCompound(soulCompound);

        // Enchants it with soulbound
        soulStack.addEnchantment(CoreEnchantments.soulbound, 1);

        // Give it to the player, or drop it if they cant fit it (for whatever reason)
        if (!player.inventory.addItemStackToInventory(soulStack)) {
            player.dropItem(soulStack, false);
        } else {
            player.inventory.addItemStackToInventory(soulStack);
        }
    }

    @Override
    public void playerRespawn(EntityPlayer player) {
        super.playerRespawn(player);
        int inventorySize = player.inventory.getSizeInventory();
        for(int i = 0;i<inventorySize;i++){
            ItemStack itemStack = player.inventory.getStackInSlot(i);
            if(itemStack.getItem() == ModItems.SOUL_ITEM){
                NBTTagList enchantments = itemStack.getEnchantmentTagList();
                if(enchantments.tagCount() == 0){
                    itemStack.addEnchantment(CoreEnchantments.soulbound,1);
                }

            }
        }
    }
}
