package net.entropy.cobblesona.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties PANCAKES = new FoodProperties.Builder().nutrition(3).saturationModifier(1f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 5), (float) 6 / 9).build();
}
