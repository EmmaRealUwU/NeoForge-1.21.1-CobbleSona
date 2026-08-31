package net.entropy.cobblesona.item.custom;

import net.entropy.cobblesona.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class MetaNavigatorItem extends Item {
    private static final Map<Block, Block> META_MAP =
            Map.of(
                    Blocks.DIRT, ModBlocks.BURIED_COGNITION.get(),
                    Blocks.STONE, ModBlocks.EMBEDDED_COGNITION.get()
            );
    private static final Map<Block, Block> UNMETA_MAP =
            Map.of(
                    ModBlocks.BURIED_COGNITION.get(), Blocks.DIRT,
                    ModBlocks.EMBEDDED_COGNITION.get(), Blocks.STONE
            );

    public MetaNavigatorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        if(META_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), META_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS);
            }
        }
        else if(UNMETA_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), UNMETA_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.BEACON_DEACTIVATE, SoundSource.BLOCKS);
            }
        }

        return super.useOn(context);
    }
}
