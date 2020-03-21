package com.kwpugh.gobber2.items.rings;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.GeneralModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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

public class ItemCustomRingMiner extends Item
{
	public ItemCustomRingMiner(Properties properties)
	{
		super(properties);
	}

	public static final int BREAK_DELAY = 1;
	
	int ringMinerCooldown = GeneralModConfig.RING_MINER_COOLDOWN.get();
	boolean reverseRingMiner = GeneralModConfig.REVERSE_RING_MINER.get();
	boolean delayedBreakMode = GeneralModConfig.DELAY_BREAK_MODE.get();
	
	boolean shiftKeyPressed = false;
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

        ItemStack equippedMain = player.getHeldItemMainhand();
        
        if(equippedMain == stack)   //Only works in the main hand
        {
        	shiftKeyPressed = player.isShiftKeyDown();
        	
        	player.getCooldownTracker().setCooldown(this, ringMinerCooldown);
        	
        	if(!world.isRemote)
			{
				Block block;
				List<BlockPos> poslist = new ArrayList<BlockPos>();

				for (int x = -5; x <= 5; x++)
				{
					for (int y = 0; y <= 4; y++)
					{
						for (int z = -5; z <= 5; z++)
						{
							BlockPos pos = player.getPosition().add(x, y, z);
							block = world.getBlockState(pos).getBlock();							
							String blockForgeTags = block.getTags().toString();
						    
							if (block == Blocks.STONE ||
									blockForgeTags.contains("forge:stone") ||
									blockForgeTags.contains("forge:sandstone") ||
									blockForgeTags.contains("forge:sand") ||
									blockForgeTags.contains("forge:dirt") ||
									blockForgeTags.contains("forge:gravel") ||
									block instanceof GravelBlock ||
									block instanceof SandBlock ||
									block == Blocks.DIRT || 
									block == Blocks.GRASS_PATH || 
									block == Blocks.SAND  || 
									block == Blocks.RED_SAND  || 
									block == Blocks.SANDSTONE || 
									block == Blocks.RED_SANDSTONE || 
									block == Blocks.GRAVEL || 
									block == Blocks.GRASS_BLOCK ||
									block == Blocks.COARSE_DIRT ||
									block == Blocks.PODZOL ||
									block == Blocks.MYCELIUM ||
									block == Blocks.GRANITE || 
									block == Blocks.ANDESITE || 
									block == Blocks.DIORITE  || 
									block == Blocks.DIORITE || 
									block == Blocks.SOUL_SAND || 
									block == Blocks.MOSSY_COBBLESTONE || 
									block == Blocks.MOSSY_COBBLESTONE_SLAB || 
									block == Blocks.MOSSY_COBBLESTONE_STAIRS ||
									block == Blocks.MOSSY_STONE_BRICKS || 
									block == Blocks.MOSSY_STONE_BRICK_STAIRS || 
									block == Blocks.MOSSY_STONE_BRICK_SLAB || 
									block == Blocks.STONE_BRICKS || 
									block == Blocks.STONE_BRICK_STAIRS || 
									block == Blocks.STONE_BRICK_SLAB || 
									block == Blocks.CRACKED_STONE_BRICKS || 
									block == Blocks.INFESTED_CRACKED_STONE_BRICKS || 
									block == Blocks.INFESTED_CHISELED_STONE_BRICKS|| 
									block == Blocks.INFESTED_COBBLESTONE || 
									block == Blocks.INFESTED_MOSSY_STONE_BRICKS || 
									block == Blocks.END_STONE || 
									block == Blocks.NETHERRACK || 
									block == Blocks.NETHER_BRICKS || 
									block == Blocks.NETHER_BRICK_FENCE || 
									block == Blocks.NETHER_BRICK_STAIRS)
							{
								poslist.add(player.getPosition().add(x, y, z));
							}
						}
					}	
				}
				

				if(delayedBreakMode)
				{
					//Test code for block break delay
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
										world.destroyBlock(breakPos, !reverseRingMiner);
									}
									else
									{
										world.destroyBlock(breakPos, reverseRingMiner);	
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
				else
				{
					//Traditional method of block break, all at once
					if (!poslist.isEmpty())
					{
						for (int i = 0; i <= poslist.size() - 1; i++)
						{
							BlockPos targetpos = poslist.get(i);
							block = world.getBlockState(targetpos).getBlock();
							
							if(player.isShiftKeyDown())   
							{
								world.destroyBlock(targetpos, !reverseRingMiner);
							}
							else
							{
								world.destroyBlock(targetpos, reverseRingMiner);	
							}
						}				
					}				
				}
			}
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_miner.line1").applyTextStyle(TextFormatting.GREEN)));
		
		if(reverseRingMiner)
		{
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_miner.line4").applyTextStyle(TextFormatting.YELLOW)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_miner.line5").applyTextStyle(TextFormatting.YELLOW)));
		}
		else
		{
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_miner.line2").applyTextStyle(TextFormatting.YELLOW)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_miner.line3").applyTextStyle(TextFormatting.YELLOW)));
		}
		

		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_miner.line6").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring.cooldown", ringMinerCooldown).applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		
		if(delayedBreakMode)
		{
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_miner.mode.line2").applyTextStyle(TextFormatting.LIGHT_PURPLE)));	
		}
		else
		{
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_miner.mode.line1").applyTextStyle(TextFormatting.LIGHT_PURPLE)));	
		}		
	} 
}