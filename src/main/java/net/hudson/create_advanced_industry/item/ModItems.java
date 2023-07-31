package net.hudson.create_advanced_industry.item;

import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CreateAdvancedIndustry.MOD_ID);

    public static final RegistryObject<Item> RAW_CASSITERITE = ITEMS.register("raw_cassiterite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_RAW_CASSITERITE = ITEMS.register("crushed_raw_cassiterite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_WASHED_RAW_CASSITERITE = ITEMS.register("crushed_washed_raw_cassiterite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN = ITEMS.register("tin",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
