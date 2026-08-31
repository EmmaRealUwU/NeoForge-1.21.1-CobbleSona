package net.entropy.cobblesona.item;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativemodeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CobbleSona.MOD_ID);

    public static final Supplier<CreativeModeTab> COBBLESONA_ITEMS_TAB = CREATIVE_MODE_TAB.register("cobblesona_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MASQUERADEMASK.get()))
                    .title(Component.translatable("creativetab.entropyscobblesona.cobblesona_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.MASQUERADEMASK);
                        output.accept(ModItems.KNIGHTMASK);
                        output.accept(ModItems.COGNITIVEFABRIC);
                        output.accept(ModItems.WHITEVELVET);
                        output.accept(ModItems.METANAVIGATOR);
                    }).build());

    public static final Supplier<CreativeModeTab> COBBLESONA_BLOCKS_TAB = CREATIVE_MODE_TAB.register("cobblesona_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.MEMENTOS_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CobbleSona.MOD_ID, "cobblesona_items_tab"))
                    .title(Component.translatable("creativetab.entropyscobblesona.cobblesona_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BURIED_COGNITION);
                        output.accept(ModBlocks.EMBEDDED_COGNITION);
                        output.accept(ModBlocks.COGNITION_BLOCK);
                        output.accept(ModBlocks.MEMENTOS_BLOCK);
                        output.accept(ModBlocks.SOS_BLOCK);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
