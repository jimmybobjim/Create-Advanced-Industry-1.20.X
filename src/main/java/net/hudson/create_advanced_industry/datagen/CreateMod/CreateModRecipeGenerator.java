package net.hudson.create_advanced_industry.datagen.CreateMod;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.hudson.create_advanced_industry.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class CreateModRecipeGenerator extends ProcessingRecipeGen {
    public void generateRecipe(String name) {
        GeneratedRecipe recipe = create(Create.asResource(name), b -> b.require(Ingredient.of(Items.GOLD_INGOT, Items.DIAMOND)).output(Items.GOLD_BLOCK));
    }

    //GeneratedRecipe a = create("test", b -> b.require(Ingredient.of(Items.GOLD_INGOT, Items.DIAMOND)));
    GeneratedRecipe test = create(Create.asResource("test"), b -> b.require(Ingredient.of(Items.GOLD_INGOT, Items.DIAMOND)).output(Items.GOLD_BLOCK));

    public CreateModRecipeGenerator(PackOutput generator) {
        super(generator);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.PRESSING;
    }
}

