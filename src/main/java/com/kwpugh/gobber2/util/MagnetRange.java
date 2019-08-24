package com.kwpugh.gobber2.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;

public class MagnetRange
{	
    public static int getCurrentRange(ItemStack stack)
    {
        return stack.getTag().getInt("currentRange");
    }
    
    public static boolean getCurrentlySet(ItemStack stack)
    {
        return stack.getTag().getBoolean("currentlySet");
    }

    public static void setCurrentRange(PlayerEntity player, Hand hand, int range)
    {
    	setCurrentRange(player.getHeldItem(hand), range);
    }

    public static void setCurrentRange(ItemStack stack, int newRange)
    {
        if(!stack.hasTag())
        {
            stack.setTag(new CompoundNBT());
        }
        
        stack.getTag().putInt("currentRange", newRange);
        stack.getTag().putBoolean("currentlySet", true);
    }
}