package net.hudson.create_advanced_industry.materials;

import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.hudson.create_advanced_industry.block.ModBlocks;
import net.hudson.create_advanced_industry.datagen.ModItemModelProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static net.hudson.create_advanced_industry.item.ModItems.ITEMS;

public class MaterialGenerator {

    public static RegistryObject<Item> generateItem(String name) {
        System.out.println("item was generated");
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    public static RegistryObject<Item> generateItem(String name, Item.Properties itemProperty) {
        return ITEMS.register(name, () -> new Item(itemProperty));
    }

    public static RegistryObject<Block> generateBLock(String name, BlockBehaviour.Properties blockProperty) {
        return ModBlocks.registerBlock(name, () -> new Block(blockProperty));
    }

    public void generateFluid() {
        //figure out how this works
    }

    private Map<String, Map<String, Map<String, Object>>> generateMaterials(List<String> materials,
                                                                                   List<String> items,
                                                                                   List<String> blocks,
                                                                                   List<BlockBehaviour.Properties> blockProperties,
                                                                                   List<String> fluids) {
        Map<String, Map<String, Map<String, Object>>> toReturn = new HashMap<>();
        for (int i = 0; i < materials.size(); i++) {
            String material = materials.get(i);
            BlockBehaviour.Properties blockProperty = blockProperties.get(i);

            Map<String, Map<String, Object>> map = new HashMap<>();
            Map<String, Object> itemMap = new HashMap<>();
            Map<String, Object> blockMap = new HashMap<>();
            Map<String, Object> fluidMap = new HashMap<>();

            Function<String, String> name = (n) -> material + "_" + n;

            for (String item : items) {
                itemMap.put(item, generateItem(name.apply(item)));
            }
            for (String block : blocks) {
                itemMap.put(block, generateBLock(name.apply(block), blockProperty));
            }
            for (String fluid : fluids) {

            }

            map.put("item", itemMap);
            map.put("block", blockMap);
            map.put("fluid", fluidMap);
            toReturn.put(material, map);
        }
        return toReturn;
    }
}
