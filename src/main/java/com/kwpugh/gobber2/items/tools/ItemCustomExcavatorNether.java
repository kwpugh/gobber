package com.kwpugh.gobber2.items.tools;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.items.toolclasses.ExcavatorBase;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCustomExcavatorNether extends ExcavatorBase
{
	public ItemCustomExcavatorNether (IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
 
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.GOBBER2_INGOT_NETHER.get();
	}
}

