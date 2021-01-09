package com.kwpugh.gobber2.util;

import javax.annotation.Nonnull;

import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

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
    	
	    //Full suit
    	if(head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON.get() &&
      			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON.get() &&
      			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON.get() &&
      			feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON.get())
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
    	if((head.getItem() == ItemInit.GOBBER2_HELMET.get() ||
      			head.getItem() == ItemInit.GOBBER2_HELMET_NETHER.get() ||
      			head.getItem() == ItemInit.GOBBER2_HELMET_END.get() ||
      			head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON.get())	)
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
    	if(legs.getItem() == ItemInit.GOBBER2_LEGGINGS.get() ||
      			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER.get() ||
      			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_END.get() ||
      					legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON.get() ||
      			
      			mainHand.getItem() == ItemInit.GOBBER2_RING_ASCENT.get())
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
    	if(chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER.get() ||
      			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_END.get()  ||
      			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON.get() ||
      			
      			mainHand.getItem() == ItemInit.GOBBER2_RING_BLAZE.get())
      	{
    		return true;  		
      	}
    	
	    PlayerInventory inv1 = player.inventory;
	    
		//Is the ring in the player inventory?
		for (int slot = 0; slot < inv1.getSizeInventory(); slot++)
		{
			ItemStack stack = inv1.getStackInSlot(slot);
			if (stack.getItem() == ItemInit.GOBBER2_RING_PHOENIX.get())
			{	
				return true;
			}
		}
		
		//Is the ring in a Curios slot?
		if (CuriosModCheck.CURIOS.isLoaded())
	    {
			if (CuriosUtil.findItem(ItemInit.GOBBER2_RING_PHOENIX.get(), player) != ItemStack.EMPTY)
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
    	ItemStack offHand = player.getHeldItemOffhand();
    	
    	if(mainHand.getItem() == ItemInit.GOBBER2_RING_STEALTH.get() && offHand.isEmpty())
      	{
      		return true;  		
      	}
      		
        return false;
    } 
    
    public static boolean isPlayerGotHasteRing(PlayerEntity player)
    { 	    
		PlayerInventory inv2 = player.inventory;
		
		//Is the ring in the player inventory?
		for (int slot = 0; slot < inv2.getSizeInventory(); slot++)
		{
			ItemStack stack = inv2.getStackInSlot(slot);
			if (stack.getItem() == ItemInit.GOBBER2_RING_HASTE.get())
			{	
				return true;
			}
		}
		
		//Checks Curios slots
		if (CuriosModCheck.CURIOS.isLoaded())
	    {
			if (CuriosUtil.findItem(ItemInit.GOBBER2_RING_HASTE.get(), player) != ItemStack.EMPTY)
			{
				return true;
		    }
	    } 
		
        return false;
    } 
    
    public static boolean isPlayerGotExpToken(PlayerEntity player)
    { 	    
		PlayerInventory inv2 = player.inventory;
		
		//Is the ring in the player inventory?
		for (int slot = 0; slot < inv2.getSizeInventory(); slot++)
		{
			ItemStack stack = inv2.getStackInSlot(slot);
			if (stack.getItem() == ItemInit.GOBBER2_MEDALLION_EXP.get())
			{	
				return true;
			}
		}
		
		//Checks Curios slots
		if (CuriosModCheck.CURIOS.isLoaded())
	    {
			if (CuriosUtil.findItem(ItemInit.GOBBER2_MEDALLION_EXP.get(), player) != ItemStack.EMPTY)
			{
				return true;
		    }
	    } 
		
        return false;
    } 
} 