package net.hudson.create_advanced_industry.fluid;

import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class ModFluidTypes {
    public static RegistryObject<FluidType> registerFluidType(String name, UnaryOperator<FluidType.Properties> operator, ResourceLocation Still_RL, ResourceLocation Flowing_RL, ResourceLocation Overlay_RL) {

        return FLUID_TYPES.register(name, () -> new
                FluidType(operator.apply(FluidType.Properties.create())) {

                    private final ResourceLocation stillTexture = Still_RL;
                    private final ResourceLocation flowingTexture = Flowing_RL;
                    private final ResourceLocation overlayTexture = Overlay_RL;


                    @Override
                    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                        consumer.accept(new IClientFluidTypeExtensions() {
                            @Override
                            public ResourceLocation getStillTexture() {
                                return stillTexture;
                            }

                            @Override
                            public ResourceLocation getFlowingTexture() {
                                return flowingTexture;
                            }

                            @Override
                            public ResourceLocation getOverlayTexture() {
                                return overlayTexture;
                            }
                        });
                    }
                });
    }

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CreateAdvancedIndustry.MOD_ID);

    public static final RegistryObject<FluidType> TIN_MOLTEN_FLUID_TYPE = registerFluidType("tin_molten",
            new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, 0xA1E038D0,
                    new Vector3f(244f / 255f, 244f / 255f, 244f / 255f),
                    FluidType.Properties.create()));


    //private static final RegistryObject<FluidType> TEST = registerFluidType("test", )


    public static RegistryObject<FluidType> registerFluidType(String name, FluidType fluidType) {
        return FLUID_TYPES.register(name, () -> fluidType);
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
