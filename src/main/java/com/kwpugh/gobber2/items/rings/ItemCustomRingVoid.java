package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingVoid extends Item
{
	public ItemCustomRingVoid(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand)
	{
		ItemStack stack = entity.getHeldItem(hand);
		
		int currentDim = entity.dimension.getId();
		
		if(!world.isRemote)
		{
			if(!(currentDim == 1))
			{
				entity.changeDimension(DimensionType.THE_END);  //Switch to The End

		    	return ActionResult.resultSuccess(stack);
			}
			else if(((currentDim == 1)) && (entity.isShiftKeyDown()))
			{
				entity.changeDimension(DimensionType.OVERWORLD);  //Switch to The End
		    	
				return ActionResult.resultSuccess(stack);
			}
			else if(((currentDim == 1)) && (!entity.isShiftKeyDown()))
			{
				entity.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line5").applyTextStyle(TextFormatting.GREEN)));
			}
			else
			{
				entity.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line6").applyTextStyle(TextFormatting.GREEN)));
			}	
		}
	    
	    return ActionResult.resultFail(stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line2").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line3").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line4").applyTextStyle(TextFormatting.RED)));

	}  
}
