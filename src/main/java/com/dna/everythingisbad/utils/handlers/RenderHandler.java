package com.dna.everythingisbad.utils.handlers;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler {
    public static void registerCustomMeshesAndStates()
    {
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.DEVILS_PEE_BLOCK), new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(Reference.MOD_ID+":"+"devils_pee", "fluid");
            }
        });

        ModelLoader.setCustomStateMapper(ModBlocks.DEVILS_PEE_BLOCK, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(Reference.MOD_ID+":"+"devils_pee", "fluid");
            }
        });
    }
}
