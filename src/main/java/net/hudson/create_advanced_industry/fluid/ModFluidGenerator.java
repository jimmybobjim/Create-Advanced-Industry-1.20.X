package net.hudson.create_advanced_industry.fluid;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

import static net.hudson.create_advanced_industry.block.ModBlocks.BLOCKS;
import static net.hudson.create_advanced_industry.fluid.ModFluidTypes.*;
import static net.hudson.create_advanced_industry.fluid.ModFluids.FLUIDS;
import static net.hudson.create_advanced_industry.item.ModItems.ITEMS;

public class ModFluidGenerator {
    public static Map<String, Object> createFluid(String name, int tint, float[] vector, int slopeDistance, int levelDecreasePerBlock, boolean bucket) {
        Map<String, Object> toReturn = new HashMap<>();

        RegistryObject<FlowingFluid> SOURCE = FLUIDS.register(name,
                () -> new ForgeFlowingFluid.Source(ModFluids.PROPERTIES));

        RegistryObject<FlowingFluid> FLOWING = FLUIDS.register(name + "_flowing",
                () -> new ForgeFlowingFluid.Flowing(ModFluids.PROPERTIES));

        RegistryObject<FluidType> TYPE = ModFluidTypes.registerFluidType(name,
                new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, tint,
                        new Vector3f(vector[0] / 255f, vector[1] / 255f, vector[2] / 255f),
                        FluidType.Properties.create()));

        RegistryObject<LiquidBlock> BLOCK = BLOCKS.register(name + "_block",
                () -> new LiquidBlock(SOURCE, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

        ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(TYPE, SOURCE, FLOWING)
                .slopeFindDistance(slopeDistance)
                .levelDecreasePerBlock(levelDecreasePerBlock)
                .block(BLOCK);

        if (bucket) {
            RegistryObject<Item> BUCKET = ITEMS.register(name + "_bucket",
                    () -> new BucketItem(ModFluids.SOURCE_TIN_MOLTEN, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
            PROPERTIES.bucket(BUCKET);
            toReturn.put("bucket", BUCKET);
        }


        toReturn.put("source", SOURCE);
        toReturn.put("flowing", FLOWING);
        toReturn.put("type", TYPE);
        toReturn.put("block", BLOCK);
        toReturn.put("properties", PROPERTIES);

        return toReturn;
    }
}
