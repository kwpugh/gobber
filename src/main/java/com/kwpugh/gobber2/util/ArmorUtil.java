package com.kwpugh.gobber2.util;

import javax.annotation.Nonnull;

import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

/*
 * Inspired and adapted from Sinhika's code in NetherRocks
 * 
 */

public final class ArmorUtil
{
    @Nonnull
    @SuppressWarnings("ConstantConditions")
    public static <T> T _null() {
        return null;
    }

    public static boolean isPlayerGotFallProtection(PlayerEntity player)
    {
    	ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
    	ItemStack mainHand = player.getHeldItemMainhand();
    	
    	if(legs.getItem() == ItemList.gobber2_leggings ||
      			legs.getItem() == ItemList.gobber2_leggings_nether ||
      			legs.getItem() == ItemList.gobber2_leggings_end ||
      			mainHand.getItem() == ItemList.gobber2_ring_ascent)
      	{
      		return true;  		
      	}
      		
        return false;
    } 
    
    public static boolean isPlayerGotFireProtection(PlayerEntity player)
    {
    	ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
    	ItemStack mainHand = player.getHeldItemMainhand();
    	
    	if(chest.getItem() == ItemList.gobber2_chestplate_nether ||
      			chest.getItem() == ItemList.gobber2_chestplate_end  ||
      			mainHand.getItem() == ItemList.gobber2_ring_blaze)
      	{
    		return true;  		
      	}
      		
        return false;
    } 
} 