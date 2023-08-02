package net.hudson.create_advanced_industry.datagen;

import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, CreateAdvancedIndustry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //tags here
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
