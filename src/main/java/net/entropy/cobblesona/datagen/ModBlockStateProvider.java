package net.entropy.cobblesona.datagen;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.block.ModBlocks;
import net.entropy.cobblesona.block.custom.StampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
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

        getVariantBuilder(ModBlocks.STAMP_BLOCK.get()).forAllStates(state -> {
            if(state.getValue(StampBlock.CLICKABLE)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("stamp_block_unclicked",
                        ResourceLocation.fromNamespaceAndPath(CobbleSona.MOD_ID, "block/" + "stamp_block_unclicked")))};
            }
            else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("stamp_block_clicked",
                        ResourceLocation.fromNamespaceAndPath(CobbleSona.MOD_ID, "block/" + "stamp_block_clicked")))};

            }
        });
        simpleBlockItem(ModBlocks.STAMP_BLOCK.get(), models().cubeAll("stamp_block_unclicked",
                ResourceLocation.fromNamespaceAndPath(CobbleSona.MOD_ID, "block/" + "stamp_block_unclicked")));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
