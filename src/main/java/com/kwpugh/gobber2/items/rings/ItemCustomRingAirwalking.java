package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

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
				//((EntityPlayer) player).addExperienceLevel(-1);
			}
			else
			{
				player.setNoGravity(false);
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Provides the ability to walk on air"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Works while in player mainhand"));
	}
}
