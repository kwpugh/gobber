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
			player.removeActivePotionEffect(Effects.BLINDNESS);
			player.removeActivePotionEffect(Effects.SLOWNESS);
			player.removeActivePotionEffect(Effects.MINING_FATIGUE);
			player.removeActivePotionEffect(Effects.INSTANT_DAMAGE);
			player.removeActivePotionEffect(Effects.NAUSEA);
			player.removeActivePotionEffect(Effects.HUNGER);
			player.removeActivePotionEffect(Effects.POISON);
			player.removeActivePotionEffect(Effects.WITHER);
			player.removeActivePotionEffect(Effects.LEVITATION);
			player.removeActivePotionEffect(Effects.UNLUCK);
			player.removeActivePotionEffect(Effects.WEAKNESS);		
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_curing.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_curing.line2").applyTextStyle(TextFormatting.YELLOW)));
	}   
}
