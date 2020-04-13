package com.kwpugh.gobber2.items.foods;

import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class GoooeyAppleNether extends Item
{

	public GoooeyAppleNether(Properties properties)
	{
		super(properties);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving)
	{
		PlayerEntity player = (PlayerEntity) (entityLiving);
		
		ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);

		PlayerSpecialAbilities.giveNewMaxHealth(worldIn, player, stack, 60.0D);

		return itemstack;
	}	   

	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.EAT;
	}
	
}
