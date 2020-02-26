package com.kwpugh.gobber2.util;


import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.lists.ItemList;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEIDescriptions implements IModPlugin
{
	static ResourceLocation plugin = new ResourceLocation(Gobber2.modid, "plugin");

	@Override
	public ResourceLocation getPluginUid()
	{
		return plugin;
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry)
	{
		registry.addIngredientInfo(new ItemStack(ItemList.block_healer), VanillaTypes.ITEM, 
				"item.gobber2.block_healer.jei.line1",
				" ",
				"item.gobber2.block_healer.jei.line2",
				" ",
				"item.gobber2.block_healer.jei.line3");
		
		registry.addIngredientInfo(new ItemStack(ItemList.block_protector), VanillaTypes.ITEM, 
				"item.gobber2.block_protector.jei.line1",
				" ",
				"item.gobber2.block_protector.jei.line2",
				" ",
				"item.gobber2.block_protector.jei.line3",
				" ",
				"item.gobber2.block_protector.jei.line4",
				" ",
				"item.gobber2.block_protector.jei.line5");
		
		registry.addIngredientInfo(new ItemStack(ItemList.block_defender), VanillaTypes.ITEM, 
				"item.gobber2.block_defender.jei.line1",
				" ",
				"item.gobber2.block_defender.jei.line2",
				" ",
				"item.gobber2.block_defender.jei.line3",
				" ",
				"item.gobber2.block_defender.jei.line4",
				" ",
				"item.gobber2.block_defender.jei.line5");
		
		registry.addIngredientInfo(new ItemStack(ItemList.block_looter), VanillaTypes.ITEM, 
				"item.gobber2.block_looter.jei.line1",
				" ",
				"item.gobber2.block_looter.jei.line2",				
				"item.gobber2.block_looter.jei.line3",		
				"item.gobber2.block_looter.jei.line4",
				"item.gobber2.block_looter.jei.line5",
				"item.gobber2.block_looter.jei.line6",
				"item.gobber2.block_looter.jei.line7",
				"item.gobber2.block_looter.jei.line8",
				"item.gobber2.block_looter.jei.line9",
				"item.gobber2.block_looter.jei.line10",
				"item.gobber2.block_looter.jei.line11",
				"item.gobber2.block_looter.jei.line12",
				"item.gobber2.block_looter.jei.line13",
				"item.gobber2.block_looter.jei.line14",
				" ",
				"item.gobber2.block_looter.jei.line15",
				" ",
				"item.gobber2.block_looter.jei.line16",
				" ",
				"item.gobber2.block_looter.jei.line17");
		
		registry.addIngredientInfo(new ItemStack(ItemList.block_maturator), VanillaTypes.ITEM, 
				"item.gobber2.block_maturator.jei.line1",
				" ",
				"item.gobber2.block_maturator.jei.line2",
				"item.gobber2.block_maturator.jei.line3",
				" ",
				"item.gobber2.block_maturator.jei.line4");
	}
}