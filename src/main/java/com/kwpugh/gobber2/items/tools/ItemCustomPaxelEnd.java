package com.kwpugh.gobber2.items.tools;

import java.util.Set;

import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class ItemCustomPaxelEnd extends ItemCustomPaxelBase
{
	public ItemCustomPaxelEnd(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn,
			Properties builder)
	{
		super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder);	
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemList.gobber2_ingot_end;
	}
}
