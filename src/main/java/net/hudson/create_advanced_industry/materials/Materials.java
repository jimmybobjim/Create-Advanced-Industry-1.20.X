package net.hudson.create_advanced_industry.materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;

import java.util.*;

public class Materials {
    private final Map<String, RegistryObject<Item>> items = new HashMap<>();
    private final Map<String, RegistryObject<Block>> blocks = new HashMap<>();

    public static final List<String> METALS = List.of(
            //"tin",
            "aluminium",
            "tungsten"
            //etc etc
    );



    public void generate() {

        for (String metal : METALS) {
            Map<String, RegistryObject<Item>> metals = new HashMap<>();
            RegistryObject<Item>
                    sheet = MaterialGenerator.generateItem(metal + "_sheet"),
                    ingot = MaterialGenerator.generateItem(metal + "_ingot"),
                    rod = MaterialGenerator.generateItem(metal + "_rod"),
                    plate = MaterialGenerator.generateItem(metal + "_plate"),
                    wire = MaterialGenerator.generateItem(metal + "_wire"),
                    foil = MaterialGenerator.generateItem(metal + "_foil");

            RegistryObject<Block>
                    block = MaterialGenerator.generateBLock(metal + "_block", BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noLootTable());

            this.items.put(metal + "_sheet", sheet);
            this.items.put(metal + "_ingot", ingot);
            this.items.put(metal + "_rod", rod);
            this.items.put(metal + "_plate", plate);
            this.items.put(metal + "_wire", wire);
            this.items.put(metal + "_foil", foil);

            this.blocks.put(metal + "_block", block);
        }


        RegistryObject<Item> material = MaterialGenerator.generateItem("test");
    }

    public Map<String, RegistryObject<Item>> getItems() {
        return this.items;
    }

    public RegistryObject<Item> getItem(String key) {
        return this.items.get(key);
    }

    public Map<String, RegistryObject<Block>> getBlocks() {
        return this.blocks;
    }

    public RegistryObject<Block> getBlock(String key) {
        return  this.blocks.get(key);
    }
}
