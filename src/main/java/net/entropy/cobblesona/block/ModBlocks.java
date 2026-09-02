package net.entropy.cobblesona.block;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.block.custom.SeaOfSoulsBlock;
import net.entropy.cobblesona.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CobbleSona.MOD_ID);

    public static final DeferredBlock<Block> COGNITION_BLOCK = registerBlock("cognition_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .strength(5f)
                    .explosionResistance(6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> MEMENTOS_BLOCK = registerBlock("mementos_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(50f)
                    .explosionResistance(1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.HEAVY_CORE)
            ));

    public static final DeferredBlock<Block> EMBEDDED_COGNITION = registerBlock("embedded_cognition",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(3f)
                    .explosionResistance(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> BURIED_COGNITION = registerBlock("buried_cognition",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.5f)
                    .explosionResistance(0.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ROOTED_DIRT)
            ));

    public static final DeferredBlock<Block> SOS_BLOCK = registerBlock("sos_block",
            () -> new SeaOfSoulsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .strength(-1f)
                    .explosionResistance(-1f)
                    .lightLevel(state -> 7)
                    .sound(SoundType.WET_GRASS)
                    .noLootTable()
            ));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
