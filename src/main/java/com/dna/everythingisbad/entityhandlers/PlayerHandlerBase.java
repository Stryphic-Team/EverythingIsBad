package com.dna.everythingisbad.entityhandlers;

import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.utils.handlers.CommonEventHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import scala.util.Random;

public abstract class PlayerHandlerBase {
    protected Random random = RandomUtils.random;
    public PlayerHandlerBase(){
        CommonEventHandler.PLAYER_HANDLERS.add(this);
    }
    //Called once per tick
    public void playerTick(EntityPlayer player){}
    //Called when a player joins the server
    public void playerJoined(EntityPlayer player){}
    //Called when a player leaves the server
    public void playerLeave(EntityPlayer player){}
    //Runs before initialization
    public void playerPreInitialization(EntityPlayer player){}
    //Used for things that only need to be run on the player's first join
    public void playerInitialization(EntityPlayer player){}
    //Runs after player is initialized
    public void playerPostInitialization(EntityPlayer player){}
    //Called when a player breaks a block
    public void playerBreakBlock(EntityPlayer player, IBlockState state){}
    //Called when a player respawns
    public void playerRespawn(EntityPlayer player){}
    //Called when a player smelts a item in a furnace
    public void playerSmeltItem(EntityPlayer player, ItemStack smelting){}
    //Called when the player dies
    public void playerDied(EntityPlayer player){}
    // Called once per tick on the EntityPlayerSP
    public void clientPlayerTick(EntityPlayer player){};
    // Called when the overlays are rendered
    public void gameOverlayEvent(RenderGameOverlayEvent event){};

    public void clientPlayerJoined(EntityPlayer player){};
    //Called when a player catches a fish
    public void playerCaughtFish(EntityPlayer entityPlayer, NonNullList<ItemStack> drops) {
    }
    //called when a player kills a entity living
    public void playerKilledLiving(EntityPlayer player, Entity entity) { }
}
