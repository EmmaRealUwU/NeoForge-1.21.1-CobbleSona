package net.entropy.cobblesona.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.AttributeSet;
import javax.swing.text.Style;
import java.util.List;
import java.util.Objects;

public class MetaNavigatorItem extends Item {

    public MetaNavigatorItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(!Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("text.entropyscobblesona.meta_navigator.hold_shift"));
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
        else {
            KeyMapping keyUse = Minecraft.getInstance().options.keyUse;
            Component boundKey = keyUse.getTranslatedKeyMessage().plainCopy().withStyle(ChatFormatting.AQUA);
            tooltipComponents.add(Component.translatable("text.entropyscobblesona.meta_navigator.tooltip", boundKey));
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
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
                player.sendSystemMessage(Component.translatable(
                        "text.entropyscobblesona.meta_navigator.nav_use_error"
                ));
            }
            //once there's an actual dimension and such, this may be where you can check for items that aren't allowed to leave / enter the metaverse ehe
            //don't bother until you get there and still feel a need to do so
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.success(itemstack);
    }
}
