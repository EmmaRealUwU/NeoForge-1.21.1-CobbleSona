package net.entropy.cobblesona.item.custom;

import net.entropy.cobblesona.block.ModBlocks;
import net.entropy.cobblesona.block.custom.StampBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class StampCardItem extends Item {

    public StampCardItem(Properties properties) {
        super(properties);

    }
    public StampCardItem(Properties properties, int stampCount) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Block clickedBlock = context.getLevel().getBlockState(context.getClickedPos()).getBlock();
        if (clickedBlock == ModBlocks.STAMP_BLOCK.get()) {
            Boolean clickable = clickedBlock.defaultBlockState().getValue(StampBlock.CLICKABLE);
            Integer stampCount = 6; //change this to grab data component
            if(!clickable) {
                context.getPlayer().sendSystemMessage(Component.translatable("text.entropyscobblesona.stamp_not_clickable"));
                return InteractionResult.FAIL;
            }
            if(stampCount >= 6) {
                context.getPlayer().sendSystemMessage(Component.translatable("text.entropyscobblesona.stamp_card_full"));
                return InteractionResult.FAIL;
            }
            else {
                //add 1 to stamp count here!
                return InteractionResult.SUCCESS;
            }

        }
        else {
            return InteractionResult.FAIL;
        }
    }
}
