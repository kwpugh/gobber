package com.kwpugh.gobber2.items.medallions;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomMedallionStepping extends Item
{
	public ItemCustomMedallionStepping(Properties properties)
	{
		super(properties);
	}

	float currentStepHeight;
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;
			player.stepHeight = currentStepHeight;
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
		if(!(player.isCrouching()))
		{
			player.sendStatusMessage((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line1", player.stepHeight).mergeStyle(TextFormatting.GREEN)), true);
		}
		
        if(player.isCrouching())
        {   
        	if(player.stepHeight < 1.0F)
		    {
		    	player.stepHeight = 1.0F;
		    	player.sendStatusMessage((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line2", player.stepHeight).mergeStyle(TextFormatting.GREEN)), true);
		    }
		    else if(player.stepHeight == 1.0F)
			{
		    	player.stepHeight = 2.1F;
		    	player.sendStatusMessage((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line2", player.stepHeight).mergeStyle(TextFormatting.GREEN)), true);
			}
			else if(player.stepHeight == 2.1F)
			{
				player.stepHeight = 3.1F;
				player.sendStatusMessage((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line2", player.stepHeight).mergeStyle(TextFormatting.GREEN)), true);
			}
			else if(player.stepHeight == 3.1F)
			{
				player.stepHeight = 0.6F;
				player.sendStatusMessage((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line3").mergeStyle(TextFormatting.GREEN)), true);
				player.sendStatusMessage((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line4").mergeStyle(TextFormatting.GREEN)), true);
			}		    
		    
        	currentStepHeight = player.stepHeight;

            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }
        return super.onItemRightClick(world, player, hand);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line5").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line6").mergeStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_medallion_stepping.line7").mergeStyle(TextFormatting.LIGHT_PURPLE)));
	} 
}
