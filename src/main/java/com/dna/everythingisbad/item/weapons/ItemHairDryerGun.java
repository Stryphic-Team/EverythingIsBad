package com.dna.everythingisbad.item.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemHairDryerGun extends ItemGunBase {
    public ItemHairDryerGun(String name){
        super(name);
        ignoreAmmo = true;

    }

    @Override
    public ItemCartridgeBase getCartridge() {
        return null;
    }

    @Override
    public void onShoot(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote)
        {
            EntitySnowball entitysnowball = new EntitySnowball(worldIn,playerIn);
            entitysnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitysnowball);
        }
    }

    @Override
    public void onEmpty(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

    }
}


