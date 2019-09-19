package com.dna.everythingisbad.plugins;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

public class BaseRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {
    protected IDrawableStatic background;
    protected IDrawableStatic energyMeter;
    protected IDrawableStatic icon;
    protected String localizedName;

    @Override
    public String getUid() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getModName() {
        return null;
    }

    @Override
    public IDrawable getBackground() {
        return null;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, T recipeWrapper, IIngredients ingredients) {

    }
}
