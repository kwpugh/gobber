package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingRepair extends Item
{

	public ItemCustomRingRepair(Properties properties)
	{
		super(properties);
	}

   @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Slowly repairs damaged item in player inventory"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Work while ring is in player inventory"));
	} 
}
