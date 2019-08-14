package com.dna.everythingisbad.client;

import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public class ModItemMeshDefinition implements ItemMeshDefinition {
    private String name;
    public ModItemMeshDefinition(String name){
        this.name = name;
    }
    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        return new ModelResourceLocation(CommonUtils.createUnlocalizedName(name), "fluid");
    }
}
