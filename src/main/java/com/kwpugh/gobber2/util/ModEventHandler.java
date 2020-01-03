package com.kwpugh.gobber2.util;

import java.util.Random;

import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEventHandler
{
	@SubscribeEvent
	 public void harvestNetherWart(HarvestDropsEvent event)
	 {
		Block block = event.getState().getBlock();
		BlockPos pos = event.getPos();
		IWorldReader world = event.getWorld();
		
				
		 if (block == Blocks.NETHER_WART)
		 {
			 Random rand = new Random();

			 int itemvalue = rand.nextInt(30) + 1;
		
				 switch(itemvalue)
				 {
				 	case 5:
				 		event.getDrops().add(new ItemStack(ItemList.gobber2_globette_nether));
				 		return;	 	
				 	default:
				 		return;
		 		}
		}				 		 

	}
	
	@SubscribeEvent
	 public void harvestChorusTree(HarvestDropsEvent event)
	 {
		Block block = event.getState().getBlock();
		 if (block == Blocks.CHORUS_PLANT)
		 {
			 Random rand = new Random();

			 int itemvalue = rand.nextInt(40) + 1;
		
			 switch(itemvalue)
			 {
			 	case 5:
			 		event.getDrops().add(new ItemStack(ItemList.gobber2_globette_end));
			 		return;	 	
			 	default:
			 		return;
			}
		 }
	 }
}
