package net.entropy.cobblesona.datagen;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CobbleSona.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.COGNITION_BLOCK);
        blockWithItem(ModBlocks.SOS_BLOCK);
        blockWithItem(ModBlocks.MEMENTOS_BLOCK);
        blockWithItem(ModBlocks.EMBEDDED_COGNITION);
        blockWithItem(ModBlocks.BURIED_COGNITION);

        doorBlockWithRenderType(ModBlocks.SLOTH_DOOR.get(), modLoc("block/sloth_door_bottom"), modLoc("block/sloth_door_top"), "cutout");
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
