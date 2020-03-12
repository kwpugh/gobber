package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class ItemCustomStaffClearing extends Item
{
	public ItemCustomStaffClearing(Properties properties)
	{
		super(properties);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

		if (!world.isRemote)
		{		 	
    		BlockPos playerPos = new BlockPos(player.getPositionVec());
    		
    		for (BlockPos targetPos : BlockPos.getAllInBoxMutable(playerPos.add(-11, -2, -11), playerPos.add(11, 2, 11)))
    		{
				Block block = world.getBlockState(targetPos).getBlock();
				
				if (block == Blocks.GRASS || 
						block == Blocks.DEAD_BUSH || 
						block == Blocks.TALL_GRASS || 
						block == Blocks.FERN || 
						block == Blocks.LARGE_FERN || 
						block instanceof MushroomBlock || 
						block instanceof FlowerBlock)
				{
					world.destroyBlock(targetPos, true);
				}
				
				if (block instanceof LeavesBlock && player.isShiftKeyDown())
				{
					world.destroyBlock(targetPos, true);
				}
    		}    		
		}
		
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_clearing.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_clearing.line2").applyTextStyle(TextFormatting.GREEN)));
	}
}
