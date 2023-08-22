package net.hudson.create_advanced_industry.datagen.CreateMod;

import net.minecraft.data.PackOutput;

public class CreateRecipeTypes {
    public static void generate(PackOutput packOutput) {
        CreateModRecipeGenerator c = new CreateModRecipeGenerator(packOutput);
        c.generateRecipe("test");
    }
}
