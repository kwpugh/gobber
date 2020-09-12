package com.kwpugh.gobber2.items.toolbaseclasses;

/*
 * Neurodr0me's - Hammer code borrowed from Practical Tools: https://www.curseforge.com/minecraft/mc-mods/practical-tools
 * 
 */

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HammerBase extends PickaxeItem
{	
	public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.ROCK, Material.IRON, Material.GLASS, Material.ICE, Material.PACKED_ICE, Material.ANVIL);

	public HammerBase(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
 
    public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity)
    {
    	stack.attemptDamageItem(1, random, null);

        if (entity instanceof PlayerEntity)
        {
        	HammerUtil.attemptBreakNeighbors(world, pos, (PlayerEntity) entity, EFFECTIVE_MATERIALS);
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
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_hammer.line1").mergeStyle(TextFormatting.GREEN)));
	}
}
