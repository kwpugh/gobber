package com.kwpugh.gobber2.util;


import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Gobber2_Group extends ItemGroup
{
	public Gobber2_Group() 
	{
		super("gobber2");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(ItemInit.GOBBER2_SWORD_NETHER.get());
	}	
}
