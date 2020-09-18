package com.kwpugh.gobber2.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.TileInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockProtector extends Block
{
	int radius = GobberConfigBuilder.PROTECTOR_RADIUS.get();

	public BlockProtector(Properties properties)
	{
		super(properties);
	}

	public boolean hasTileEntity(final BlockState state) 
	{
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) 
	{
		// Always use TileEntityType#create to allow registry overrides to work.
		return TileInit.BLOCK_PROTECTOR.get().create();
	}

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.block_protector.line2").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.block_protector.line3").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.block_protector.line4", radius).mergeStyle(TextFormatting.LIGHT_PURPLE)));
	}
}

