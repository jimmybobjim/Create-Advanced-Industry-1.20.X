package net.hudson.create_advanced_industry.datagen;

import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.hudson.create_advanced_industry.datagen.CreateMod.CreateModRecipeGenerator;
import net.hudson.create_advanced_industry.datagen.CreateMod.CreateRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CreateAdvancedIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModFluidTagsProvider(packOutput, lookupProvider, existingFileHelper));


        List<ProcessingRecipeGen> createRecipes = new ArrayList<>();
        createRecipes.add(new CreateModRecipeGenerator(packOutput));

        generator.addProvider(true, new DataProvider() {
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
        });
    }
}
