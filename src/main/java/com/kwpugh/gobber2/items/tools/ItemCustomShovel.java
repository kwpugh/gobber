package com.kwpugh.gobber2.items.tools;

import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;

public class ItemCustomShovel extends ShovelItem
{
	public ItemCustomShovel (IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemList.gobber2_ingot;
	}
}