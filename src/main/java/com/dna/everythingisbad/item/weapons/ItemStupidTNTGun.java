package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemStupidTNTGun extends ItemGunBase {
    public ItemStupidTNTGun(String name){
        super(name);
        delay = 30;
    }

    @Override
    public ItemCartridgeBase getCartridge() {
        return ModItems.STUPID_TNT_CARTRIDGE_ITEM;
    }

    @Override
    public void onShoot(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            EntityStupidTNT entityStupidTNT = new EntityStupidTNT(worldIn, playerIn);
            entityStupidTNT.setPower(15f);
            entityStupidTNT.setFuse(30);

            entityStupidTNT.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 5f, 0.5F);
            worldIn.spawnEntity(entityStupidTNT);
        }
    }

    @Override
    public void onEmpty(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
    }


}
