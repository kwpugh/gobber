package com.kwpugh.gobber2.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;

public class EnableUtil
{	

    public static boolean isEnabled(ItemStack stack)
    {
        return stack.hasTag() && stack.getTag().getBoolean("IsEnabled");
    }

    public static void changeEnabled(PlayerEntity player, Hand hand)
    {
        changeEnabled(player.getHeldItem(hand));
    }

    public static void changeEnabled(ItemStack stack)
    {
        if(!stack.hasTag())
        {
            stack.setTag(new CompoundNBT());
        }
        boolean isEnabled = isEnabled(stack);
        stack.getTag().putBoolean("IsEnabled", !isEnabled);
    }

	
}