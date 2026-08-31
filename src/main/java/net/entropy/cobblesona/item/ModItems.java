package net.entropy.cobblesona.item;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.item.custom.MetaNavigatorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CobbleSona.MOD_ID);

    public static final DeferredItem<Item> METANAVIGATOR = ITEMS.register("meta_navigator",
            () -> new MetaNavigatorItem(new Item.Properties()));

    public static final DeferredItem<Item> MASQUERADEMASK = ITEMS.register("masquerade_mask",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> KNIGHTMASK = ITEMS.register("knight_mask",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> WHITEVELVET = ITEMS.register("white_velvet_fabric",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COGNITIVEFABRIC = ITEMS.register("cognitive_fabric",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
