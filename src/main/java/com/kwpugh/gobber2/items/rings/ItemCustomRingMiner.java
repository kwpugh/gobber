package com.kwpugh.gobber2.items.rings;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.SandBlock;
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

public class ItemCustomRingMiner extends Item
{

	public ItemCustomRingMiner(Properties properties)
	{
		super(properties);
	}
	
	 public static boolean isRock(Block blockIn) {
	      return net.minecraftforge.common.Tags.Blocks.STONE.contains(blockIn);
	   }
	 
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

        ItemStack equippedMain = player.getHeldItemMainhand();
    	
        //boolean forgeStoneTag;
        
        if(equippedMain == stack)   //Only works in the main hand
        {
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
							
							if (block == Blocks.STONE ||
									Block.isRock(block) == true ||      //This is key to selecting blocks that have the Forge "stone" tag
									block.isIn(BlockTags.DIRT_LIKE) ||
									block instanceof GravelBlock ||
									block instanceof SandBlock ||
									block == Blocks.DIRT || 
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
		list.add(new StringTextComponent(TextFormatting.BLUE + "Breaks most vanilla blocks around the player"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Area of effect: 11x5x11"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to break blocks"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Sneak right-click to remove blocks"));
	} 
}
