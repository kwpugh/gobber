package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingCuring extends Item
{

	public ItemCustomRingCuring(Properties properties)
	{
		super(properties);
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof PlayerEntity)
		{	
			((PlayerEntity) entity).removeActivePotionEffect(Effects.BLINDNESS);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.SLOWNESS);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.MINING_FATIGUE);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.INSTANT_DAMAGE);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.NAUSEA);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.HUNGER);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.POISON);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.WITHER);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.LEVITATION);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.UNLUCK);
			((PlayerEntity) entity).removeActivePotionEffect(Effects.WEAKNESS);		
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Provides curing of most negative potion effects"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Works while in player inventory"));
	}  
}
