package net.hudson.create_advanced_industry;

import com.mojang.logging.LogUtils;
import net.hudson.create_advanced_industry.block.ModBlocks;
import net.hudson.create_advanced_industry.datagen.ModItemModelProvider;
import net.hudson.create_advanced_industry.fluid.ModFluidTypes;
import net.hudson.create_advanced_industry.fluid.ModFluids;
import net.hudson.create_advanced_industry.item.ModCreativeModeTabs;
import net.hudson.create_advanced_industry.item.ModItemProperties;
import net.hudson.create_advanced_industry.item.ModItems;
import net.hudson.create_advanced_industry.materials.Materials;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateAdvancedIndustry.MOD_ID)
public class CreateAdvancedIndustry {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "create_advanced_industry";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Materials MATERIALS = new Materials();

    public CreateAdvancedIndustry() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MATERIALS.generate();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RAW_CASSITERITE);
            event.accept(ModItems.CRUSHED_RAW_CASSITERITE);
            event.accept(ModItems.CRUSHED_WASHED_RAW_CASSITERITE);
            event.accept(ModItems.TIN);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.CASSITERITE_ORE);
            event.accept(ModBlocks.TIN_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ModItemProperties.addCustomItemProperties();


                ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_TIN_MOLTEN.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_TIN_MOLTEN.get(), RenderType.translucent());
            });



            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
