package com.kwpugh.gobber2.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.GlassBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockGobberGlassEnd extends GlassBlock
{
	public BlockGobberGlassEnd(Properties properties)
	{
		super(properties);
	} 
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_glass.line1").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_glass.line2").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_glass.line3").mergeStyle(TextFormatting.LIGHT_PURPLE)));
	} 
}
