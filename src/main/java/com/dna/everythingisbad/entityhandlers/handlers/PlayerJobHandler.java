package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.utils.helpers.FormatHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class PlayerJobHandler extends PlayerHandlerBase {
    String[] oreSuffixes = new String[]{
            "thermalfoundation:ore",
            "_ore"
    };
    @Override
    public void playerBreakBlock(EntityPlayer player, IBlockState state) {
        super.playerBreakBlock(player, state);
        playerMinerHandler(player,state);
    }

    @Override
    public void playerCaughtFish(EntityPlayer entityPlayer, NonNullList<ItemStack> drops) {
        super.playerCaughtFish(entityPlayer, drops);
        playerFisherHandler(entityPlayer);
    }

    @Override
    public void playerKilledLiving(EntityPlayer player, Entity entity) {
        super.playerKilledLiving(player, entity);
        playerMonsterHunterHandler(player,entity);
    }

    private void playerMonsterHunterHandler(EntityPlayer player, Entity entity) {
        if(entity instanceof EntityMob){
            payPlayer(player,RandomUtils.fromRangeF(10f,25f),true);
        }
    }

    private void playerMinerHandler(EntityPlayer player, IBlockState state){
        if(state.getBlock().equals(Blocks.STONE)){
            payPlayer(player,0.01f + RandomUtils.fromRangeF(0f,0.1f),false);
        }

        if(isOre(state)){
            payPlayer(player,1f + RandomUtils.fromRangeF(0f,1.0f),false);
        }
    }
    private void playerFisherHandler(EntityPlayer player){
        payPlayer(player,RandomUtils.fromRangeF(5f,20f),true);
    }
    private void payPlayer(EntityPlayer player,float amount,boolean alwaysPay){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null){
            if(RandomUtils.percentChance(15) || alwaysPay) {
                playerProperties.setBalance(playerProperties.getBalance() + amount);
                playerProperties.saveNBTData(player.getEntityData());
                player.sendMessage(new TextComponentString("You have received " + FormatHelper.formatMoney(amount)).setStyle(new Style().setColor(TextFormatting.GREEN)));
            }
        }
    }
    public boolean isOre(IBlockState state){
        for(String suffix:oreSuffixes){
            String registryName = state.getBlock().getRegistryName().toString();
            if(registryName.contains(suffix)){
                return true;
            }
        }
        return false;
    }
}
