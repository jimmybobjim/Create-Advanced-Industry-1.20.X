package net.hudson.create_advanced_industry.datagen.CreateMod;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.hudson.create_advanced_industry.CreateAdvancedIndustry.MATERIALS;
import static net.hudson.create_advanced_industry.materials.Materials.METALS;

public class CreateModRecipes {
    public static DataProvider register(PackOutput packOutput) {
        CreateModRecipeGenerator
                compacting = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.COMPACTING),
                crushing = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.CRUSHING),
                cutting = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.CUTTING),
                deploying = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.DEPLOYING),
                emptying = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.EMPTYING),
                filling = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.FILLING),
                haunting = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.HAUNTING),
                itemApplication = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.ITEM_APPLICATION),
                milling = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.MILLING),
                mixing = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.MIXING),
                polishing = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.SANDPAPER_POLISHING),
                pressing = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.PRESSING),
                sequencedAssembly = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.SEQUENCED_ASSEMBLY),
                washing = new CreateModRecipeGenerator(packOutput, AllRecipeTypes.SPLASHING);


        for (String metal : METALS) {
            pressing.add(metal + "_plate_from_" + metal + "_ingot", b -> b
                    .require(MATERIALS.getItem(metal + "_ingot").get())
                    .output(MATERIALS.getItem(metal + "_plate").get()));

            pressing.add(metal + "_sheet_from_" + metal + "_plate", b -> b
                    .require(MATERIALS.getItem(metal + "_plate").get())
                    .output(MATERIALS.getItem(metal + "_sheet").get()));

            pressing.add(metal + "_foil_from_" + metal + "_sheet", b -> b
                    .require(MATERIALS.getItem(metal + "_sheet").get())
                    .output(MATERIALS.getItem(metal + "_foil").get()));
        }



        List<ProcessingRecipeGen> createRecipes = new ArrayList<>();

        createRecipes.add(compacting);
        createRecipes.add(crushing);
        createRecipes.add(cutting);
        createRecipes.add(deploying);
        createRecipes.add(emptying);
        createRecipes.add(filling);
        createRecipes.add(haunting);
        createRecipes.add(itemApplication);
        createRecipes.add(milling);
        createRecipes.add(mixing);
        createRecipes.add(polishing);
        createRecipes.add(pressing);
        createRecipes.add(sequencedAssembly);
        createRecipes.add(washing);



        return new DataProvider() {
            @Override
            public CompletableFuture<?> run(CachedOutput pOutput) {
                return CompletableFuture.allOf(createRecipes.stream()
                        .map(gen -> gen.run(pOutput))
                        .toArray(CompletableFuture[]::new));
            }

            @Override
            public String getName() {
                return "test";
            }
        };
    }
}
