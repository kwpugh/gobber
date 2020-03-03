package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomStaffNature extends Item
{
	public ItemCustomStaffNature(Properties properties)
	{
		super(properties);
	}

	Block block;
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context)
	{
		 World world = context.getWorld();
		 PlayerEntity player = context.getPlayer();
		 BlockPos pos = context.getPos();
		 BlockState state = world.getBlockState(pos);
		 Block block = state.getBlock();
		 ItemStack stack = context.getItem();

	     if(block == Blocks.ACACIA_SAPLING)
	     {
	    	 world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	    	 world.setBlockState(pos, Blocks.BIRCH_SAPLING.getDefaultState(), 3);
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	    	 
	    	 return ActionResultType.SUCCESS;
	     }
	     else if(block == Blocks.BIRCH_SAPLING)
	     {
	    	 world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	    	 world.setBlockState(pos, Blocks.DARK_OAK_SAPLING.getDefaultState(), 3);
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	    	 
	    	 return ActionResultType.SUCCESS;
	     }
	     else if(block == Blocks.DARK_OAK_SAPLING)
	     {
	    	 world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	    	 world.setBlockState(pos, Blocks.JUNGLE_SAPLING.getDefaultState(), 3);
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	    	 
	    	 return ActionResultType.SUCCESS;
	     }
	     else if(block == Blocks.JUNGLE_SAPLING)
	     {
	    	 world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	    	 world.setBlockState(pos, Blocks.OAK_SAPLING.getDefaultState(), 3);
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	    	 
	    	 return ActionResultType.SUCCESS;
	     }
	     else if(block == Blocks.OAK_SAPLING)
	     {
	    	 world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	    	 world.setBlockState(pos, Blocks.SPRUCE_SAPLING.getDefaultState(), 3);
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	    	 
	    	 return ActionResultType.SUCCESS;
	     }
	     else if(block == Blocks.SPRUCE_SAPLING)
	     {
	    	 world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	    	 world.setBlockState(pos, Blocks.ACACIA_SAPLING.getDefaultState(), 3);
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	    	 return ActionResultType.SUCCESS;
	     }
	     else if(block == Blocks.SUGAR_CANE)
	     {
	    	 world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	    	 world.setBlockState(pos, Blocks.BAMBOO_SAPLING.getDefaultState(), 3);
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	    	 
	    	 return ActionResultType.SUCCESS;
	     }
	     else if(block == Blocks.BAMBOO_SAPLING)
	     {
	    	 world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	    	 world.setBlockState(pos, Blocks.SUGAR_CANE.getDefaultState(), 3);
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	    	 
	    	 return ActionResultType.SUCCESS;
	     }
	    	     
		 return ActionResultType.PASS;
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
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_nature.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_nature.line2").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_nature.line3").applyTextStyle(TextFormatting.YELLOW)));
	}
}
