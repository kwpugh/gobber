package com.kwpugh.gobber2.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosAPI;

public class CuriosUtil
{
    //Thanks williewillus and Kamefrede
    public static ItemStack findItem(Item item, LivingEntity living)
    {
        return CuriosAPI.getCurioEquipped(item, living)
                .map(ImmutableTriple::getRight)
                .orElse(ItemStack.EMPTY);
    }
}