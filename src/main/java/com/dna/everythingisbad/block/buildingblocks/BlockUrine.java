package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockBase;
import com.dna.everythingisbad.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class BlockUrine extends BlockBase {
    public BlockUrine(String name){
        super(name);
        setSoundType(SoundType.GLASS);
        setHardness(1.2f);
        setResistance(10f);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(3);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.URINE_CRYSTAL_ITEM;
    }
}
