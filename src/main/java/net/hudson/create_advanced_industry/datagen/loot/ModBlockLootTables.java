package net.hudson.create_advanced_industry.datagen.loot;

import net.hudson.create_advanced_industry.block.ModBlocks;
import net.hudson.create_advanced_industry.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.TIN_BLOCK.get());

        this.add(ModBlocks.CASSITERITE_ORE.get(), block -> createOreDrop(ModBlocks.CASSITERITE_ORE.get(), ModItems.TIN.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
