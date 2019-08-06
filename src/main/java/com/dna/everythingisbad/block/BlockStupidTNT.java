package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import net.minecraft.item.Item;

import java.util.Random;

public class BlockStupidTNT extends BlockTNT implements IHasModel {

    public BlockStupidTNT(String name, Material material){
        super();
        setRegistryName(name);
        setUnlocalizedName(name);
        setSoundType(SoundType.PLANT);
        setHardness(1f);
        setResistance(1.0f);
        setHarvestLevel("pickaxe",1);
        setLightLevel(1f);
        setCreativeTab(CreativeTabs.REDSTONE);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));



    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);

        if (worldIn.isBlockPowered(pos))
        {
            this.onBlockDestroyedByPlayer(worldIn, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)));
            worldIn.setBlockToAir(pos);

        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {

    }
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        if (!worldIn.isRemote)
        {
            Random random = new Random();
            for(int i = 0;i<30;i++){
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(
                        worldIn,
                        (double)pos.getX(),
                        (double)pos.getY(),
                        (double)pos.getZ(),
                        explosionIn.getExplosivePlacedBy());
                double x_vel = (double)((random.nextFloat()*50f)-25f);
                double y_vel = (double)((random.nextFloat()*50f)-25f);
                double z_vel = (double)((random.nextFloat()*50f)-25f);
                entitytntprimed.setVelocity(x_vel,y_vel,z_vel);
                worldIn.spawnEntity(entitytntprimed);
                worldIn.playSound((EntityPlayer)null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            //explode(worldIn,pos,getDefaultState(),explosionIn.getExplosivePlacedBy());
        }
    }
    public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter)
    {

        if (!worldIn.isRemote)
        {
            if (state == null || ((Boolean)state.getValue(EXPLODE)).booleanValue())
            {
                Random random = new Random();
                for(int i = 0;i<30;i++){
                    EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(
                            worldIn,
                            (double)pos.getX(),
                            (double)pos.getY(),
                            (double)pos.getZ(),
                            igniter);
                    double x_vel = (double)((random.nextFloat()*50f)-25f);
                    double y_vel = (double)((random.nextFloat()*50f)-25f);
                    double z_vel = (double)((random.nextFloat()*50f)-25f);
                    entitytntprimed.setVelocity(x_vel,y_vel,z_vel);
                    worldIn.spawnEntity(entitytntprimed);
                    worldIn.playSound((EntityPlayer)null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }
    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }


}
