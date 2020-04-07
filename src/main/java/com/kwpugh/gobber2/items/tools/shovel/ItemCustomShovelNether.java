package com.kwpugh.gobber2.items.tools.shovel;

import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;

public class ItemCustomShovelNether extends ShovelItem
{
	public ItemCustomShovelNether (IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder)
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
		return repair.getItem() == ItemInit.GOBBER2_INGOT_NETHER.get();
	}
}