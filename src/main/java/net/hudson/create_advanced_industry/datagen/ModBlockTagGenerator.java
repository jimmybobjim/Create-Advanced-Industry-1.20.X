package net.hudson.create_advanced_industry.datagen;

import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.hudson.create_advanced_industry.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CreateAdvancedIndustry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.TIN_BLOCK.get(),
                ModBlocks.CASSITERITE_ORE.get()
        );

        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                ModBlocks.CASSITERITE_ORE.get()
        );
    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
