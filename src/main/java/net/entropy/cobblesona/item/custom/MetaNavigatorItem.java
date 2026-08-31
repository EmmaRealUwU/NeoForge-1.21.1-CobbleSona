package net.entropy.cobblesona.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MetaNavigatorItem extends Item {

    public MetaNavigatorItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(!level.isClientSide) {
            if(level.dimension() == Level.OVERWORLD) {
                player.changeDimension(new DimensionTransition(
                        Objects.requireNonNull(Objects.requireNonNull(level.getServer()).getLevel(Level.NETHER)),
                        player.getPosition(0),
                        new Vec3(0, 0, 0),
                        player.getYRot(),
                        player.getXRot(),
                        false,
                        DimensionTransition.DO_NOTHING
                ));
            } else if (level.dimension() == Level.NETHER) {
                player.changeDimension(new DimensionTransition(
                        Objects.requireNonNull(Objects.requireNonNull(level.getServer()).getLevel(Level.OVERWORLD)),
                        player.getPosition(0),
                        new Vec3(0, 0, 0),
                        player.getYRot(),
                        player.getXRot(),
                        false,
                        DimensionTransition.DO_NOTHING
                ));
            } else {
                player.sendSystemMessage(Component.literal(
                        "There's a time and place for everything! But you can't use that here."
                ));
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.success(itemstack);
    }
}
