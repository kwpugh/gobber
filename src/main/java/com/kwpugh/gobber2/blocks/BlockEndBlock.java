package com.kwpugh.gobber2.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockEndBlock extends Block
{
	public BlockEndBlock(Properties properties)
	{
		super(properties);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag)
	{
		super.addInformation(stack, world, tooltip, flag);				
		tooltip.add(new StringTextComponent(TextFormatting.BLUE + "A storage option for Gobber End Ingots"));
		tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Drops the block when broken"));
	}
}
