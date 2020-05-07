package com.kwpugh.gobber2.items.tools.excavator;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.kwpugh.gobber2.items.toolbaseclasses.ExcavatorUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
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

public class ItemCustomExcavatorEnd extends ShovelItem
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

	public ItemCustomExcavatorEnd(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override   //Used to override taking damage
	public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entityLiving)
	{
		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0F)
		{
			ExcavatorUtil.attemptBreakNeighbors(world, pos, (PlayerEntity) entityLiving, EFFECTIVE_ON, EFFECTIVE_MATERIALS);
    	  
			stack.damageItem(0, entityLiving, (p_220038_0_) -> {
            p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
         });
		}

		return true;
	}

	@Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		stack.setDamage(0);  //no damage
        
        return true;
    }
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn)
	{
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
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