package com.kwpugh.gobber2.items.staffs;

import java.util.ArrayList;
import java.util.List;

import com.kwpugh.gobber2.lists.BlockList;
import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.MelonBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

/*
 * Re-planting currently only works on crops that
 * are extended from CropsBlocks
 * 
 * TO DO: figure out how to do this with melons,
 * pumpkins, cactus, bamboo, cane, etc.  Key issue
 * is "how to determine their maxAge"
 * 
 */

public class ItemCustomStaffHarvest extends Item
{

	public ItemCustomStaffHarvest(Properties properties)
	{
		super(properties);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		Block block;
		boolean maxAge;
		
		if (!world.isRemote)
		{
			List<BlockPos> poslist = new ArrayList<BlockPos>();

			for (int x = -12; x <= 12; x++)
			{
				for (int y = -1; y <= 1; y++)
				{
					for (int z = -12; z <= 12; z++)
					{
						BlockPos pos = player.getPosition().add(x, y, z);
						block = world.getBlockState(pos).getBlock();
						
						if(block instanceof CropsBlock ||
								block instanceof CactusBlock ||
								block instanceof SugarCaneBlock ||
								block instanceof BambooBlock ||
								block instanceof MelonBlock ||
								block instanceof PumpkinBlock)
						{
							poslist.add(player.getPosition().add(x, y, z));
						}
					}
				}
			}

			if (!poslist.isEmpty())
			{
				for (int i = 0; i <= poslist.size() - 1; i++)
				{
					BlockPos targetPos = poslist.get(i);
					BlockState state = world.getBlockState(targetPos);
					block = world.getBlockState(targetPos).getBlock();	
					BlockState defaultState = block.getDefaultState();
					
					//These plants are simply broken with drops
					if(block instanceof CactusBlock ||
							block instanceof SugarCaneBlock ||
							block instanceof BambooBlock ||
							block instanceof MelonBlock ||
							block instanceof PumpkinBlock)
					{
						world.destroyBlock(targetPos, true);
					}
					
					//Crops are harvested, if at max age, and re-planted
					if(block instanceof CropsBlock)
					{
						maxAge = state.get(((CropsBlock) block).getAgeProperty()) >= ((CropsBlock) block).getMaxAge();
						
						if(maxAge)
						{
							world.destroyBlock(targetPos, true);
							world.setBlockState(targetPos, defaultState);	
						}
					}
					
					//Gobber plants are checked separately because they do not have natural drops (intentionally) and Globettes need to be spawned manually
					if(block == BlockList.gobber2_plant || 
							block == BlockList.gobber2_plant_nether || 
							block == BlockList.gobber2_plant_end)
					{
						maxAge = state.get(((CropsBlock) block).getAgeProperty()) >= ((CropsBlock) block).getMaxAge();
						
						if(maxAge)
						{
							if(block == BlockList.gobber2_plant)
							{
								world.destroyBlock(targetPos, true);
								world.setBlockState(targetPos, defaultState);
								world.addEntity(new ItemEntity(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), new ItemStack(ItemList.gobber2_globette, 1)));
							}
							
							if(block == BlockList.gobber2_plant_nether)
							{
								world.destroyBlock(targetPos, true);
								world.setBlockState(targetPos, defaultState);
								world.addEntity(new ItemEntity(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), new ItemStack(ItemList.gobber2_globette_nether, 1)));
							}
							
							if(block == BlockList.gobber2_plant_end)
							{
								world.destroyBlock(targetPos, true);
								world.setBlockState(targetPos, defaultState);
								world.addEntity(new ItemEntity(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), new ItemStack(ItemList.gobber2_globette_end, 1)));
							}	
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
		list.add(new StringTextComponent(TextFormatting.BLUE + "Harvest mature crops around the player"));
		list.add(new StringTextComponent(TextFormatting.GREEN +"Area of effect: 12x2x12"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to use"));
	} 
}
