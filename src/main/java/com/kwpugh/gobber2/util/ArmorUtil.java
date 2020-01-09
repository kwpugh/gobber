package com.kwpugh.gobber2.util;

import javax.annotation.Nonnull;

import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EnderChestInventory;
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
    	
	    PlayerInventory inv1 = player.inventory;
	    EnderChestInventory end_inv1 = player.getInventoryEnderChest();
	    
		for (int slot = 0; slot < end_inv1.getSizeInventory(); slot++)
		{
			ItemStack stack = end_inv1.getStackInSlot(slot);
			if (stack.getItem() == ItemList.gobber2_ring_phoenix)
			{	
				return true;
			}
		}
	    
		for (int slot = 0; slot < inv1.getSizeInventory(); slot++)
		{
			ItemStack stack = inv1.getStackInSlot(slot);
			if (stack.getItem() == ItemList.gobber2_ring_phoenix)
			{	
				return true;
			}
		}
		
		if (SupportMods.CURIOS.isLoaded())
	    {
			if (UtilCurios.findItem(ItemList.gobber2_ring_phoenix, player) != ItemStack.EMPTY)
			{
				return true;
		    }
	    }
      		
        return false;
    } 
} 