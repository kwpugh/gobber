package com.kwpugh.gobber2.items.tools.hoe;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomHoe extends HoeItem
{
	public ItemCustomHoe(IItemTier tier, float attackSpeedIn, Properties builder)
	{
		super(tier, attackSpeedIn, builder);
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		//Nothing at the moment
	}	

	@Override   //Taken from HoeItem to override taking damage on use
	public ActionResultType onItemUse(ItemUseContext context)
	{
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
		
		if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
		
		if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up()))
		{
			BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
		
			if (blockstate != null) {
				PlayerEntity playerentity = context.getPlayer();
				world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			
				if (!world.isRemote)
				{
					world.setBlockState(blockpos, blockstate, 11);
				
					if (playerentity != null) {
						context.getItem().damageItem(0, playerentity, (p_220043_1_) -> {
							p_220043_1_.sendBreakAnimation(context.getHand());
						});
					}
				}

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}
    
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_end.unbreakable").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	}
}
