package net.hudson.create_advanced_industry.datagen;

import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.hudson.create_advanced_industry.item.ModItems;
import net.hudson.create_advanced_industry.materials.Materials;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreateAdvancedIndustry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.TIN);
        simpleItem(ModItems.TIN_MOLTEN_BUCKET);


    }

    public ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CreateAdvancedIndustry.MOD_ID,"item/" + item.getId().getPath()));
    }
}
