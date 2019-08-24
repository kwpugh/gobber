package com.kwpugh.gobber2.items.rings;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingLumberjack extends Item
{

	public ItemCustomRingLumberjack(Properties properties)
	{
		super(properties);
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

        ItemStack equippedMain = player.getHeldItemMainhand();
    	
        if(equippedMain == stack)   //Only works in the main hand
        {
			if (!world.isRemote)
			{
				Block block;
				List<BlockPos> poslist = new ArrayList<BlockPos>();

				for (int x = -5; x <= 5; x++)
				{
					for (int y = 0; y <= 32; y++)
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

				if(!player.isSneaking())
				{
					if (!poslist.isEmpty())
					{
						for (int i = 0; i <= poslist.size() - 1; i++)
						{
							BlockPos targetpos = poslist.get(i);
							block = world.getBlockState(targetpos).getBlock();
							
							world.destroyBlock(targetpos, true);
						}				
					}
				}
				
				if(player.isSneaking())
				{
					if (!poslist.isEmpty())
					{
						for (int i = 0; i <= poslist.size() - 1; i++)
						{
							BlockPos targetpos = poslist.get(i);
							block = world.getBlockState(targetpos).getBlock();
							
							world.removeBlock(targetpos, true);
						}				
					}
				}	
			}
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Breaks Logs and Leaves around the player"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Area of effect: 11x32x11"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to use"));
	}   
}
