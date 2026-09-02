package net.entropy.cobblesona.item;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.item.custom.MetaNavigatorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CobbleSona.MOD_ID);

    public static final DeferredItem<Item> METANAVIGATOR = ITEMS.register("meta_navigator",
            () -> new MetaNavigatorItem(new Item.Properties()));

    public static final DeferredItem<Item> MASQUERADEMASK = ITEMS.register("masquerade_mask",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> KNIGHTMASK = ITEMS.register("knight_mask",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TENGUMASK = ITEMS.register("tengu_mask",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> WHITEVELVET = ITEMS.register("white_velvet_fabric",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COGNITIVEFABRIC = ITEMS.register("cognitive_fabric",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PANCAKES = ITEMS.register("pancakes",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PANCAKES)) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("text.entropyscobblesona.pancakes.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
