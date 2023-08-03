package net.hudson.create_advanced_industry.item;

import net.hudson.create_advanced_industry.CreateAdvancedIndustry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateAdvancedIndustry.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_ORES = CREATIVE_MODE_TABS.register("mod_ores",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TIN.get()))
                    .title(Component.translatable("creativetab.mod_ores")).displayItems((displayParameters, output) -> {
                        output.accept(ModItems.TIN.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
