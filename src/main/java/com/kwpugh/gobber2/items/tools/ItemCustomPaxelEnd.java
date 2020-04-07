package com.kwpugh.gobber2.items.tools;

import java.util.Set;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.items.toolclasses.PaxelBase;

import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class ItemCustomPaxelEnd extends PaxelBase
{
	public ItemCustomPaxelEnd(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn,
			Properties builder)
	{
		super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder);	
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.GOBBER2_INGOT_END.get();
	}
}
