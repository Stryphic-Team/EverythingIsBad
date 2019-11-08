package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.entity.EntityBullet;
import com.dna.everythingisbad.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemHuntingRifle extends ItemGunBase {
    public ItemHuntingRifle(String name) {
        super(name);
        delay = 20;

    }

    @Override
    public ItemCartridgeBase getCartridge() {
        return ModItems.RIFLE_CARTRIDGE;
    }

    @Override
    public void onShoot(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 1F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!worldIn.isRemote) {
            EntityBullet bulet = new EntityBullet(worldIn, playerIn);
            bulet.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0F);
            worldIn.spawnEntity(bulet);
        }
    }

    @Override
    public void onEmpty(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

    }

}
