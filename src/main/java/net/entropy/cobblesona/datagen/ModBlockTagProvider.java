package net.entropy.cobblesona.datagen;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CobbleSona.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.COGNITION_BLOCK.get())
                .add(ModBlocks.EMBEDDED_COGNITION.get())
                .add(ModBlocks.MEMENTOS_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.BURIED_COGNITION.get());

        tag(BlockTags.DOORS)
                .add(ModBlocks.SLOTH_DOOR.get());

        tag(BlockTags.REPLACEABLE)
                .add(ModBlocks.CAUTION_TAPE.get());

        tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.CAUTION_TAPE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BURIED_COGNITION.get())
                .add(ModBlocks.EMBEDDED_COGNITION.get())
                .add(ModBlocks.COGNITION_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.MEMENTOS_BLOCK.get())
                .add(ModBlocks.SLOTH_DOOR.get());

    }
}
