package net.hudson.create_advanced_industry.fluid;

import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.hudson.create_advanced_industry.block.ModBlocks;
import net.hudson.create_advanced_industry.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CreateAdvancedIndustry.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_TIN_MOLTEN = FLUIDS.register("tin_molten_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.TIN_MOLTEN_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_TIN_MOLTEN = FLUIDS.register("flowing_tin_molten_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.TIN_MOLTEN_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties TIN_MOLTEN_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
        ModFluidTypes.TIN_MOLTEN_FLUID_TYPE, SOURCE_TIN_MOLTEN, FLOWING_TIN_MOLTEN)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.TIN_MOLTEN_BLOCK)
            .bucket(ModItems.TIN_MOLTEN_BUCKET);

    //Map<String, Object> testFluid = ModFluidGenerator.createFluid("");

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
