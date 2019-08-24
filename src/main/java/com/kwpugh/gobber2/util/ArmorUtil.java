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
    
//    public static boolean isPlayerGotCreativeFlight(PlayerEntity player)
//    {
//    	ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
//		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
//		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
//	    ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);	
//	    
//    	
//    	if(head.getItem() == ItemList.gobber2_helmet_end && 
//    			chest.getItem() == ItemList.gobber2_chestplate_end &&
//    			legs.getItem() == ItemList.gobber2_leggings_end && 
//    			feet.getItem() == ItemList.gobber2_boots_end && 
//    			player.dimension.getId() == 1)
//      	{
//      		return true;  		
//      	}
//      		
//        return false;
//    }
} 