package net.entropy.cobblesona.block.custom;

import net.entropy.cobblesona.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class StampBlock extends Block {
    public static final BooleanProperty CLICKABLE = BooleanProperty.create("clickable");

    public StampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKABLE, true));
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state,
                                                       Level level, @NotNull BlockPos pos, @NotNull Player player,
                                                       @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            boolean currentState = state.getValue(CLICKABLE);
            boolean canReceiveStamp = false;
            //check if itemstack.getitem.getitem in hand slot is a stamp card.
            if(stack.getItem().getClass() == ModItems.STAMP_CARD.get().getClass()) {
                level.setBlockAndUpdate(pos, state.setValue(CLICKABLE, false));
            }
            else {
                player.sendSystemMessage(Component.translatable("text.entropyscobblemon.no_stamp_card"));
            }
            //set canReceiveStamp to whether it's a stamp card and if the stamp count is < 6

        }

        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLICKABLE);
    }
}
