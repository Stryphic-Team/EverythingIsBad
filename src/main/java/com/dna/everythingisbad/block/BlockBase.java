package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;


public abstract class BlockBase extends Block implements IModBlockBase {
    protected String name;

    /**
     * Do not fucking use this
     * @param material
     */
    @Deprecated
    public BlockBase(Material material) {//DO NOT USE THIS EVER!!!
        //DO NOT USE THIS EVER!!! NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER NEVER
        super(material);//DO NOT USE THIS EVER!!!
        this.name = name;//DO NOT USE THIS EVER!!!
        setHardness(2);//DO NOT USE THIS EVER!!!
        setResistance(30);//DO NOT USE THIS EVER!!!
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);//DO NOT USE THIS EVER!!!
    }//DO NOT USE THIS EVER!!!
    //I would deprecate it twice but java won't allow me to
    /**
     * Should be the default super constructor for most blocks
     * @param name
     */
    public BlockBase(String name) {
        this(name,Material.ROCK);
    }

    /**
     * Can be used in the case that you would like to base a material through
     * @param name
     * @param material
     */
    public BlockBase(String name,Material material){
        super(material);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        addItemToRegistry();
        addBlockToRegistry();
    }

    @Override
    public void addItemToRegistry() {
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.name));
    }

    @Override
    public void addBlockToRegistry() {
        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerModel(Item.getItemFromBlock(this),0);
    }

}
