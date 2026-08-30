package net.entropy.cobblesona.item;

import net.entropy.cobblesona.CobbleSona;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CobbleSona.MOD_ID);

    public static final DeferredItem<Item> METANAVIGATOR = ITEMS.register("metanavigator",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
