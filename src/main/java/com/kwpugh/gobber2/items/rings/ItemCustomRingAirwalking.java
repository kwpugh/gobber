package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.kwpugh.gobber2.lists.BlockList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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

public class ItemCustomRingAirwalking extends Item
{
	public ItemCustomRingAirwalking(Properties properties)
	{
		super(properties);
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		LivingEntity player = (PlayerEntity)entity;
		ItemStack equipped = player.getHeldItemMainhand();
		
		if (!world.isRemote)
		{	
			if(stack == equipped)
			{
				player.setNoGravity(true);
			}
			else
			{
				player.setNoGravity(false);
			}
		}
	}

	//Places an gobber glass block below where the player is looking, think Angel block
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,@Nonnull Hand hand)
	{			
		if (!world.isRemote)
		{
			double x = player.getPositionVec().x + 3 * player.getLookVec().x;
			double y = 1.5 + player.getPositionVec().y + 3 * player.getLookVec().y;
			double z = player.getPositionVec().z  + 3 * player.getLookVec().z;

			BlockPos pos = new BlockPos(x,y,z);
			Block glassBlock = BlockList.gobber2_glass_end;
			BlockState glassDefaultState = glassBlock.getDefaultState();	      
      
			if (world.isAirBlock(pos) || !world.getFluidState(pos).isEmpty())
			{
				world.setBlockState(pos, glassDefaultState);
			}
    }
		return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
	}
	 
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_airwalking.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_airwalking.line2").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_airwalking.line3").applyTextStyle(TextFormatting.YELLOW)));
	} 
}
