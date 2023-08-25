package net.hudson.create_advanced_industry.datagen.CreateMod;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.Create;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.PackOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class CreateModRecipeGenerator extends ProcessingRecipeGen {
    private final AllRecipeTypes type;
    private final List<GeneratedRecipe> recipes = new ArrayList<>();

    public GeneratedRecipe add(String name, UnaryOperator<ProcessingRecipeBuilder<ProcessingRecipe<?>>> builder) {
        GeneratedRecipe recipe = create(Create.asResource(name), builder);
        this.recipes.add(recipe);
        return recipe;
    }

    public List<GeneratedRecipe> getRecipes() {
        return this.recipes;
    }

    public CreateModRecipeGenerator(PackOutput generator, AllRecipeTypes type) {
        super(generator);
        this.type = type;

    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return this.type;
    }
}
