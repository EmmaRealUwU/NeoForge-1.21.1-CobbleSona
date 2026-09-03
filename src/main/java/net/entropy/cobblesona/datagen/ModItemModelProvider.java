package net.entropy.cobblesona.datagen;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.block.ModBlocks;
import net.entropy.cobblesona.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CobbleSona.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.COGNITIVEFABRIC.get());
        basicItem(ModItems.KNIGHTMASK.get());
        basicItem(ModItems.MASQUERADEMASK.get());
        basicItem(ModItems.TENGUMASK.get());
        basicItem(ModItems.METANAVIGATOR.get());
        basicItem(ModItems.PANCAKES.get());
        basicItem(ModItems.WHITEVELVET.get());
        basicItem(ModBlocks.SLOTH_DOOR.asItem());
    }
}
