package com.dna.everythingisbad.block;

import com.dna.everythingisbad.creativetab.CreativeTabBetterIO;
import com.dna.everythingisbad.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block{
    protected String name;

    public BlockBase(Material material) {
        //material determines sound, map color, tool?, flammability, etc
        super(material);
        setHardness(2);
        setResistance(30);
        this.setCreativeTab(CreativeTabBetterIO.BETTERIO_TAB);
    }

    public BlockBase(){
        this(Material.ROCK);//we'll use rock as default
    }

    //unlocalized name here, localized name comes from lang file
    @Override
    public String getUnlocalizedName(){
        //easy storage format: blockName
        //convert to proper format: tile.[modID]:[blockName].name
        return String.format("tile.%s:%s", Reference.MOD_ID.toLowerCase(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName( String unlocalizedName ){
        return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
    }



    public String getName(){
        return name;
    }
}
