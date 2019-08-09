package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.IHasModel;
import com.dna.everythingisbad.utils.Utils;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockPoop extends BlockFallingBase implements IHasModel {
    public BlockPoop(String name, Material material) {
        //material determines sound, map color, tool?, flammability, etc
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Utils.createUnlocalizedName(name));
        setSoundType(SoundType.SLIME);
        setHardness(0.5f);
        setResistance(1);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");

    }
}
