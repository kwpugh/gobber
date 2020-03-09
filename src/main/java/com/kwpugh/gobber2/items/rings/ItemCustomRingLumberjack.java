package com.kwpugh.gobber2.items.rings;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.GeneralModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemCustomRingLumberjack extends Item
{
	public ItemCustomRingLumberjack(Properties properties)
	{
		super(properties);
	}

	int ringLumberjackCooldown = GeneralModConfig.RING_LUMBERJACK_COOLDOWN.get();
	public static final int BREAK_DELAY = 1;
	boolean delayedBreakMode = GeneralModConfig.DELAY_BREAK_MODE.get();
	boolean shiftKeyPressed = false;
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

        ItemStack equippedMain = player.getHeldItemMainhand();
    	
        if(equippedMain == stack)   //Only works in the main hand
        {
        	shiftKeyPressed = player.isShiftKeyDown();
        	
        	player.getCooldownTracker().setCooldown(this, ringLumberjackCooldown);
        	
			if (!world.isRemote)
			{
				//Scan blocks in area looking for ones to break and add to list
				Block block;
				List<BlockPos> poslist = new ArrayList<BlockPos>();

				for (int x = -5; x <= 5; x++)
				{
					for (int y = -2; y <= 40; y++)
					{
						for (int z = -5; z <= 5; z++)
						{
							BlockPos pos = player.getPosition().add(x, y, z);
							block = world.getBlockState(pos).getBlock();
							
							if (block instanceof LeavesBlock || 
									block instanceof LogBlock ||
									block instanceof MushroomBlock ||
									block.isIn(BlockTags.LEAVES) ||
									block.isIn(BlockTags.LOGS) ||
									block == Blocks.BROWN_MUSHROOM_BLOCK ||
									block == Blocks.MUSHROOM_STEM ||
									block == Blocks.RED_MUSHROOM_BLOCK ||
									block instanceof VineBlock)
							{
								poslist.add(player.getPosition().add(x, y, z));
							}
						}
					}	
				}

				if(delayedBreakMode)
				{
					//If delayed break is true in the config, use this code branch
					if (!poslist.isEmpty())
					{
						MinecraftForge.EVENT_BUS.register(new Object()
			            {
			                int delay = BREAK_DELAY;
			                int i = 0;

			                @SubscribeEvent
			                public void onTick(TickEvent.WorldTickEvent event)
			                {
			                    if (delay-- > 0) return;
			                    delay = BREAK_DELAY;
			                    if (i < poslist.size())
			                    {
			                        BlockPos breakPos = poslist.get(i);
			                    	if(shiftKeyPressed)    //NOTE: shift key needs to be held down through the delayed block breaking to get drops
									{
										world.destroyBlock(breakPos, false);
									}
									else
									{
										world.destroyBlock(breakPos, true);	
									}
			                    	
			                        i++;
			                    }
			                    else
			                    {
			                        MinecraftForge.EVENT_BUS.unregister(this);
			                    }
			                }
			            });			
					}
				}
				else    // otherwise cycle through it breaking blocks either with or without drops (tradional mod all at once)
				{
					for (int i = 0; i <= poslist.size() - 1; i++)
					{
						BlockPos targetpos = poslist.get(i);
						block = world.getBlockState(targetpos).getBlock();
						
						if(player.isShiftKeyDown())   
						{
							world.destroyBlock(targetpos, false);
						}
						else
						{
							world.destroyBlock(targetpos, true);	
						}
					}			
				}
				
				
				
				
				
//				if(!player.isShiftKeyDown())
//				{
//					if (!poslist.isEmpty())
//					{
//						for (int i = 0; i <= poslist.size() - 1; i++)
//						{
//							BlockPos targetpos = poslist.get(i);
//							block = world.getBlockState(targetpos).getBlock();
//							
//							world.destroyBlock(targetpos, true);
//						}				
//					}
//				}
//				
//				if(player.isShiftKeyDown())
//				{
//					if (!poslist.isEmpty())
//					{
//						for (int i = 0; i <= poslist.size() - 1; i++)
//						{
//							BlockPos targetpos = poslist.get(i);
//							block = world.getBlockState(targetpos).getBlock();
//							
//							world.removeBlock(targetpos, true);
//						}				
//					}
//				}	
			}
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_lumberjack.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_lumberjack.line2").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_lumberjack.line3").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_lumberjack.line4").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring.cooldown",ringLumberjackCooldown).applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	} 
}
