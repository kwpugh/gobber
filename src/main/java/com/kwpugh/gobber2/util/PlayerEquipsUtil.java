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

public final class PlayerEquipsUtil
{
    @Nonnull
    @SuppressWarnings("ConstantConditions")
    public static <T> T _null() {
        return null;
    }

    //Full suit of End Armor gets perks
    public static boolean isPlayerGotVoidProtection(PlayerEntity player)
    { 
    	ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
    	ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
    	ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
    	ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);
    	
	    //Head piece
    	if((head.getItem() == ItemList.gobber2_helmet_end &&
      			chest.getItem() == ItemList.gobber2_chestplate_end &&
      			legs.getItem() == ItemList.gobber2_leggings_end &&
      			feet.getItem() == ItemList.gobber2_boots_end) ||
    			
    			chest.getItem() == ItemList.gobber2_chestplate_dragon)
      	{
      		return true;  		
      	}
      		
        return false;
    } 
    
    //All helmet tiers get water breathing
    public static boolean isPlayerGotWaterBreathing(PlayerEntity player)
    { 
    	ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		
	    //Head piece
    	if((head.getItem() == ItemList.gobber2_helmet ||
      			head.getItem() == ItemList.gobber2_helmet_nether ||
      			head.getItem() == ItemList.gobber2_helmet_end ||
      			head.getItem() == ItemList.gobber2_helmet_dragon)	)
      	{
      		return true;  		
      	}
      		
        return false;
    } 
    
    //All legging tiers get no fall damage
    public static boolean isPlayerGotFallProtection(PlayerEntity player)
    {
    	ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
    	ItemStack mainHand = player.getHeldItemMainhand();
    	
    	//Leggings
    	if(legs.getItem() == ItemList.gobber2_leggings ||
      			legs.getItem() == ItemList.gobber2_leggings_nether ||
      			legs.getItem() == ItemList.gobber2_leggings_end ||
      					legs.getItem() == ItemList.gobber2_leggings_dragon ||
      			
      			mainHand.getItem() == ItemList.gobber2_ring_ascent)
      	{
      		return true;  		
      	}
      		
        return false;
    } 
    
		
    //Nether and End chestplates get fire protection
    public static boolean isPlayerGotFireProtection(PlayerEntity player)
    {
    	ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
    	ItemStack mainHand = player.getHeldItemMainhand();
    	
    	//Chestplate
    	if(chest.getItem() == ItemList.gobber2_chestplate_nether ||
      			chest.getItem() == ItemList.gobber2_chestplate_end  ||
      			chest.getItem() == ItemList.gobber2_chestplate_dragon ||
      			
      			mainHand.getItem() == ItemList.gobber2_ring_blaze)
      	{
    		return true;  		
      	}
    	
	    PlayerInventory inv1 = player.inventory;
	    EnderChestInventory end_inv1 = player.getInventoryEnderChest();
	    
	    //Is the ring in player enderchest?
		for (int slot = 0; slot < end_inv1.getSizeInventory(); slot++)
		{
			ItemStack stack = end_inv1.getStackInSlot(slot);
			if (stack.getItem() == ItemList.gobber2_ring_phoenix)
			{	
				return true;
			}
		}
	    
		//Is the ring in the player inventory?
		for (int slot = 0; slot < inv1.getSizeInventory(); slot++)
		{
			ItemStack stack = inv1.getStackInSlot(slot);
			if (stack.getItem() == ItemList.gobber2_ring_phoenix)
			{	
				return true;
			}
		}
		
		//Is the ring in a Curios slot?
		if (SupportMods.CURIOS.isLoaded())
	    {
			if (CuriosUtil.findItem(ItemList.gobber2_ring_phoenix, player) != ItemStack.EMPTY)
			{
				return true;
		    }
	    }
      		
        return false;
    }
    
    //Holding the Ring of Stealth
    public static boolean isPlayerGotStealth(PlayerEntity player)
    {
    	ItemStack mainHand = player.getHeldItemMainhand();
    	
    	if(mainHand.getItem() == ItemList.gobber2_ring_stealth)
      	{
      		return true;  		
      	}
      		
        return false;
    } 
} 