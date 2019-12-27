package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.TickEvent;

@EventBusSubscriber(modid = Gobber2.modid, bus = EventBusSubscriber.Bus.FORGE )
public class RepairTickHandler
{
	public RepairTickHandler(Item item)
	{

	}
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		PlayerInventory inv2 = event.player.inventory;

		for (int slot = 0; slot < inv2.getSizeInventory(); slot++)
		{
			ItemStack stack = inv2.getStackInSlot(slot);
			if (stack.getItem() == ItemList.gobber2_ring_repair)
			{	
				if (player.ticksExisted % 90 == 0)
        		{
					repair(player, inv2);
       		 	} 
			}
		}
		
//		if (SupportMods.CURIOS.isLoaded())
//	    {
//			if (UtilCurios.findItem(ItemList.gobber2_ring_repair, player) != ItemStack.EMPTY)
//			{
//				if (player.ticksExisted % 90 == 0)
//	    		{
//					repair(player, inv2);
//	   		 	}  
//		    }
//	    }  
	}
	
	private static void repair(PlayerEntity player, PlayerInventory inv)
	{		
		for(int slot = 0; slot < inv.getSizeInventory(); slot++)
		{
			ItemStack target = inv.getStackInSlot(slot);
			if (!target.isEmpty())
			{
				if (!(target == player.getHeldItemMainhand()))
				{
					if (target.isDamaged() && target.getItem().isRepairable(target))
					{
						target.setDamage(target.getDamage() - 1);
						return; 
					}
				}
			}
		}
	}
}

