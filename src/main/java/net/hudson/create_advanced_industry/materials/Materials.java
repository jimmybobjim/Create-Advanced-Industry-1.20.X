package net.hudson.create_advanced_industry.materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Materials {
    public Object materials;
    private List<RegistryObject<Item>> items = new ArrayList<>();

    public static final List<String> METALS = List.of(
            //"tin",
            "aluminium",
            "tungsten"
            //etc etc
    );

    public Materials() {

        for (String metal : METALS) {
            RegistryObject<Item>
                    sheet = MaterialGenerator.generateItem(metal + "_sheet"),
                    ingot = MaterialGenerator.generateItem(metal + "_ingot"),
                    rod = MaterialGenerator.generateItem(metal + "_rod"),
                    plate = MaterialGenerator.generateItem(metal + "_plate"),
                    wire = MaterialGenerator.generateItem(metal + "_wire");

            RegistryObject<Block>
                    block = MaterialGenerator.generateBLock(metal + "_block", BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noLootTable());


            Collections.addAll(items, sheet, ingot, rod, plate, wire);
        }


        RegistryObject<Item> material = MaterialGenerator.generateItem("test");
        items.add(material);
    }


    public List<RegistryObject<Item>> getItems() {
        return items;
    }
}
