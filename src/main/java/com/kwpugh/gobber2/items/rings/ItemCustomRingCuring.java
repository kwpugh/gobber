package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingCuring extends Item
{

	public ItemCustomRingCuring(Properties properties)
	{
		super(properties);
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		PlayerEntity player = (PlayerEntity)entity;
		
		if(entity instanceof PlayerEntity)
		{		
			if(player.getActivePotionEffect(Effects.BLINDNESS) != null)
			{
				player.removePotionEffect(Effects.BLINDNESS);
			}
	
			if(player.getActivePotionEffect(Effects.SLOWNESS) != null)
			{
				player.removePotionEffect(Effects.SLOWNESS);
			}
			
			if(player.getActivePotionEffect(Effects.MINING_FATIGUE) != null)
			{
				player.removePotionEffect(Effects.MINING_FATIGUE);
			}
			
			if(player.getActivePotionEffect(Effects.INSTANT_DAMAGE) != null)
			{
				player.removePotionEffect(Effects.INSTANT_DAMAGE);
			}
			
			if(player.getActivePotionEffect(Effects.NAUSEA) != null)
			{
				player.removePotionEffect(Effects.NAUSEA);
			}
			
			if(player.getActivePotionEffect(Effects.HUNGER) != null)
			{
				player.removePotionEffect(Effects.HUNGER);
			}
			
			if(player.getActivePotionEffect(Effects.POISON) != null)
			{
				player.removePotionEffect(Effects.POISON);
			}
			
			if(player.getActivePotionEffect(Effects.WITHER) != null)
			{
				player.removePotionEffect(Effects.WITHER);
			}
			
			if(player.getActivePotionEffect(Effects.LEVITATION) != null)
			{
				player.removePotionEffect(Effects.LEVITATION);
			}
			
			if(player.getActivePotionEffect(Effects.UNLUCK) != null)
			{
				player.removePotionEffect(Effects.UNLUCK);
			}
			
			if(player.getActivePotionEffect(Effects.WEAKNESS) != null)
			{
				player.removePotionEffect(Effects.WEAKNESS);
			}	
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_curing.line1").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_curing.line2").mergeStyle(TextFormatting.YELLOW)));
	}   
}
