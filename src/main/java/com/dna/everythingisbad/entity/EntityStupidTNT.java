package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModEntities;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityStupidTNT extends EntityTNTPrimed implements IHasModel {
    public EntityStupidTNT(World worldIn) {
        super(worldIn);

    }

    public EntityStupidTNT(World worldIn, double x, double y, double z, EntityLivingBase igniter) {
        super(worldIn, x, y, z, igniter);

    }
    @Override
    public void setVelocity(double x, double y, double z)
    {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

    }
    @Override
    public void registerModels() {

        Main.proxy.registerItemRenderer(Item.getItemFromBlock(ModBlocks.STUPID_TNT_BLOCK), 0, "inventory");
    }
}
