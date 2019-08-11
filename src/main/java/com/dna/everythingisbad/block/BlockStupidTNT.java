package com.dna.everythingisbad.block;

import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

public class BlockStupidTNT extends BlockExplodingBase {
    public final float range = 25f;
    public final int count = 15;
    public BlockStupidTNT(String name, Material material){
        super();
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setSoundType(SoundType.PLANT);
        setHardness(1f);
        setResistance(1.0f);
        setHarvestLevel("pickaxe",1);
        setLightLevel(1f);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));



    }
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);

        if (worldIn.isBlockPowered(pos))
        {
            this.onBlockDestroyedByPlayer(worldIn, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)));
            worldIn.setBlockToAir(pos);

        }
    }

    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        super.onBlockDestroyedByExplosion(worldIn,pos,explosionIn);
        if (!worldIn.isRemote)
        {
            Random random = new Random();
            for(int i = 0;i<count;i++){
                EntityStupidTNT entitytntprimed = new EntityStupidTNT(
                        worldIn,
                        (double)pos.getX(),
                        (double)pos.getY(),
                        (double)pos.getZ(),
                        explosionIn.getExplosivePlacedBy());
                double x_vel = (double)((random.nextFloat()*range)-(range/2));
                double y_vel = (double)((random.nextFloat()*range)-(range/2));
                double z_vel = (double)((random.nextFloat()*range)-(range/2));
                entitytntprimed.setVelocity(x_vel,y_vel,z_vel);
                worldIn.spawnEntity(entitytntprimed);
                worldIn.playSound((EntityPlayer)null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

        }
    }
    @Override
    public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter)
    {

        if (!worldIn.isRemote)
        {
            if (((Boolean)state.getValue(EXPLODE)).booleanValue())
            {
                Random random = new Random();
                for(int i = 0;i<count;i++){
                    EntityStupidTNT entitystupidtnt = new EntityStupidTNT(
                            worldIn,
                            (double)pos.getX(),
                            (double)pos.getY(),
                            (double)pos.getZ(),
                            igniter);
                    double x_vel = (double)((random.nextFloat()*range)-(range/2));
                    double y_vel = (double)((random.nextFloat()*range)-(range/2));
                    double z_vel = (double)((random.nextFloat()*range)-(range/2));
                    entitystupidtnt.setVelocity(x_vel,y_vel,z_vel);
                    worldIn.spawnEntity(entitystupidtnt);
                    worldIn.playSound((EntityPlayer)null, entitystupidtnt.posX, entitystupidtnt.posY, entitystupidtnt.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }
}
