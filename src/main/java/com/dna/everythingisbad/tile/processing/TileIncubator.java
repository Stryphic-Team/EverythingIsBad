package com.dna.everythingisbad.tile.processing;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.tile.TileMachineBase;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;

public class TileIncubator extends TileMachineBase {

    public TileIncubator() {
        super("incubator");
        this.itemStackHadler = new ModItemHandler(1);
        setFinishedProgress(24000);
        itemStackHadler.setSlotConfig(0,true,true);
        fluidHandler = null;
        energyUsedPerTick = 50;
    }

    @Override
    public boolean hasNecessaryItems() {
        // If there is a baby in the slot, then you are GOOD TO GO
        return (this.itemStackHadler.getStackInSlot(0).getItem() == ModItems.BABY_ITEM ||
                this.itemStackHadler.getStackInSlot(0).getItem() == Items.EGG);
    }

    @Override
    public void insertOutput() {
        if (!this.world.isRemote){

            // spawns directly in front of the block based off of the blockstate's facing
            IBlockState blockState = this.world.getBlockState(this.pos);
            EnumFacing facing = blockState.getValue(BlockHorizontal.FACING);
            BlockPos spawnpos = this.pos.offset(facing,1);

            if (this.itemStackHadler.getStackInSlot(0).getItem() == ModItems.BABY_ITEM){
                String name = this.itemStackHadler.getStackInSlot(0).getDisplayName();
                // random villager profession id assigned (new to 0.2.0; before this you could only get farmer types)
                EntityVillager entityVillager = new EntityVillager(this.world, RandomUtils.random.nextInt(6));
                // negative values indicate a child, with 1 day (24000 ticks) being the usual value
                entityVillager.setGrowingAge(-24000);

                entityVillager.setPosition(spawnpos.getX() + 0.5d ,spawnpos.getY(),spawnpos.getZ() + 0.5d);

                // if you name the Baby item in an anvil, it carries over onto the villager entity
                if (!name.equals("Baby")) {
                    entityVillager.setCustomNameTag(name);
                }
                this.world.spawnEntity(entityVillager);

            } else if (this.itemStackHadler.getStackInSlot(0).getItem() == Items.EGG){

                String name = this.itemStackHadler.getStackInSlot(0).getDisplayName();

                EntityChicken entityChicken = new EntityChicken(this.world);
                entityChicken.setGrowingAge(-24000);

                entityChicken.setPosition(spawnpos.getX() + 0.5d ,spawnpos.getY(),spawnpos.getZ() + 0.5d);

                if (!name.equals("Egg")) {
                    entityChicken.setCustomNameTag(name);
                }
                this.world.spawnEntity(entityChicken);
            }
        }
    }

    @Override
    public void reduceInput() {
        this.itemStackHadler.getStackInSlot(0).shrink(1);
    }

    @Override
    public void update() {
        super.update();

        if (this.itemStackHadler.getStackInSlot(0).getItem() == Items.AIR){
            setProgress(0);
        }

        if (progress < 10 && this.inProgress()){
            if (this.itemStackHadler.getStackInSlot(0).getItem() == ModItems.BABY_ITEM){
                NBTTagCompound compound = this.itemStackHadler.getStackInSlot(0).getTagCompound();
                if (compound != null) {
                    int age = compound.getInteger("age");
                    setProgress(age * 20);
                }
                setFinishedProgress(ModConfig.BABY_AGE_UP_TIME * 20);

            }else if (this.itemStackHadler.getStackInSlot(0).getItem() == Items.EGG){
                setFinishedProgress(800);
            }

        }

        // Once every second the baby age steps... dot dot dot............ drumroll..... idk
        if (tick % 20 == 0 && this.inProgress()){
            if (this.itemStackHadler.getStackInSlot(0).getItem() == ModItems.BABY_ITEM){
                NBTTagCompound compound = this.itemStackHadler.getStackInSlot(0).getTagCompound();
                if (compound != null){
                    compound.setInteger("age", progress / 20);
                    this.itemStackHadler.getStackInSlot(0).setTagCompound(compound);
                }
            } else if (this.itemStackHadler.getStackInSlot(0).getItem() == Items.EGG){

            }
        }
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(ModBlocks.INCUBATOR.getLocalizedName());
    }
}
