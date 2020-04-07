package com.kwpugh.gobber2.items.toolclasses;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * This is the base class for all types of Excavators
 * 
 */

public class ExcavatorBase extends ShovelItem
{
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.GRASS_BLOCK, 
			Blocks.GRASS_PATH, 
			Blocks.DIRT, 
			Blocks.COARSE_DIRT, 
			Blocks.RED_SAND, 
			Blocks.SAND, 
			Blocks.PODZOL, 
			Blocks.GRAVEL, 
			Blocks.SOUL_SAND, 
			Blocks.CLAY);
	
	public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.EARTH);

	public ExcavatorBase(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

    public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity)
    {
        stack.attemptDamageItem(1, random, null);

        if (entity instanceof PlayerEntity)
        {
        	ExcavatorUtil.attemptBreakNeighbors(world, pos, (PlayerEntity) entity, EFFECTIVE_ON, EFFECTIVE_MATERIALS);
        }
        return super.onBlockDestroyed(stack, world, state, pos, entity);
    }
 
	@Override
	public int getBurnTime(ItemStack itemStack)
	{
		return 400;
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_excavator.line1").applyTextStyle(TextFormatting.GREEN)));
	}
}
