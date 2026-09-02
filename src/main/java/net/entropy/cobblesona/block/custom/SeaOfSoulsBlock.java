package net.entropy.cobblesona.block.custom;

import net.entropy.cobblesona.item.ModItems;
import net.entropy.cobblesona.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class SeaOfSoulsBlock extends Block {
    public SeaOfSoulsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                                        @NotNull Player player, @NotNull BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON, SoundSource.BLOCKS);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
        if(entity instanceof ItemEntity itemEntity && level.dimension() == Level.NETHER) {
            if(isValidItem(itemEntity.getItem())) {
                itemEntity.setItem(new ItemStack(ModItems.MASQUERADEMASK.get(), itemEntity.getItem().getCount()));
            }
            if(itemEntity.getItem().getItem() == Items.IRON_INGOT) {
                itemEntity.setItem(new ItemStack(ModItems.KNIGHTMASK.get(), itemEntity.getItem().getCount()));
            }
            if(itemEntity.getItem().getItem() == ModItems.PANCAKES.get()) {
                itemEntity.setItem(new ItemStack(ModItems.TENGUMASK.get(), itemEntity.getItem().getCount()));
            }
        }

        super.stepOn(level, pos, state, entity);
    }

    private boolean isValidItem(ItemStack item) {
        return item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
    }

//    @Override
//    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
//        Map<Item, Item> MASK_MAP =
//            Map.of(
//                    Items.IRON_INGOT, ModItems.KNIGHTMASK.get(),
//                    ModItems.WHITEVELVET.get(), ModItems.MASQUERADEMASK.get(),
//                    ModItems.PANCAKES.get(), ModItems.TENGUMASK.get()
//            );
//        if(entity instanceof ItemEntity itemEntity && level.dimension() == Level.NETHER) {
//            ItemLike item = itemEntity.getItem().getItem();
//            int count = itemEntity.getItem().getCount();
//            itemEntity.setItem(new ItemStack(MASK_MAP.get(item), count));
//        }
//        super.stepOn(level, pos, state, entity);
//    }
}
