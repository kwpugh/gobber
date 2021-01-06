package com.kwpugh.gobber2.items.medallions;

import java.util.List;

/*
 * This code is credited to bl4ckscor3
 * 
 * Taken from the mod XP Tome and adapted
 * 
 */

import java.util.Random;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.ExpUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomMedallionExp extends Item
{
	public static final int MAX_STORAGE = 200000; 
	private final Random random = new Random();

	public ItemCustomMedallionExp()
	{
		super(new Item.Properties().maxDamage(MAX_STORAGE).group(Gobber2.gobber2));
	}

	

	
	
	
	
	// Test code
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		// Store the players xp
		if(player.isSneaking() && getXPStored(stack) != MAX_STORAGE)
		{
			int playerXP = ExpUtils.getPlayerXP(player);

			if(playerXP == 0)
				return new ActionResult<>(ActionResultType.PASS, stack);

			int actuallyStored = addXP(stack, playerXP); //try to store all of the player's levels

			ExpUtils.addPlayerXP(player, -actuallyStored);

			if(!world.isRemote)
				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, (random.nextFloat() - random.nextFloat()) * 0.35F + 0.9F);

			return new ActionResult<>(ActionResultType.SUCCESS, stack);
		}
		// Give stored xp to player
		else if(!player.isSneaking() && getXPStored(stack) != 0)
		{
			if(getXPStored(stack) >1401)
			{
				ExpUtils.addPlayerXP(player, 1401);
				setStoredXP(stack, getXPStored(stack)-1401);
			}
			else
			{
				ExpUtils.addPlayerXP(player, getXPStored(stack));
				setStoredXP(stack, 0);
			}
			

			if(!world.isRemote)
			{
				float pitchMultiplier = player.experienceLevel > 30 ? 1.0F : player.experienceLevel / 30.0F;

				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, pitchMultiplier * 0.75F, 1.0F);
			}

			return new ActionResult<>(ActionResultType.SUCCESS, stack);
		}

		return new ActionResult<>(ActionResultType.PASS, stack);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// Original code	
//	@Override
//	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
//	{
//		ItemStack stack = player.getHeldItem(hand);
//
//		if(player.isSneaking() && getXPStored(stack) != MAX_STORAGE)
//		{
//			int playerXP = ExpUtils.getPlayerXP(player);
//
//			if(playerXP == 0)
//				return new ActionResult<>(ActionResultType.PASS, stack);
//
//			int actuallyStored = addXP(stack, playerXP); //try to store all of the player's levels
//
//			ExpUtils.addPlayerXP(player, -actuallyStored);
//
//			if(!world.isRemote)
//				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, (random.nextFloat() - random.nextFloat()) * 0.35F + 0.9F);
//
//			return new ActionResult<>(ActionResultType.SUCCESS, stack);
//		}
//		else if(!player.isSneaking() && getXPStored(stack) != 0)
//		{
//			ExpUtils.addPlayerXP(player, getXPStored(stack));
//			setStoredXP(stack, 0);
//
//			if(!world.isRemote)
//			{
//				float pitchMultiplier = player.experienceLevel > 30 ? 1.0F : player.experienceLevel / 30.0F;
//
//				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, pitchMultiplier * 0.75F, 1.0F);
//			}
//
//			return new ActionResult<>(ActionResultType.SUCCESS, stack);
//		}
//
//		return new ActionResult<>(ActionResultType.PASS, stack);
//	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn)
	{
		stack.setDamage(MAX_STORAGE);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return getXPStored(stack) > 0;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return false;
	}

	@Override
	public boolean isRepairable(ItemStack stack)
	{
		return false;
	}

	/**
	 * Adds the given amount of XP to the given stack. If that action would exceed the storage capacity, as much XP as possible will be stored.
	 * @param stack The stack to add XP to
	 * @param amount The amount of XP to add
	 * @return The amount XP that was added
	 */
	public int addXP(ItemStack stack, int amount)
	{
		int stored = getXPStored(stack);

		if(stored + amount > MAX_STORAGE)
		{
			setStoredXP(stack, MAX_STORAGE);
			return MAX_STORAGE - stored;
		}
		else
		{
			setStoredXP(stack, stored + amount);
			return amount;
		}
	}

	/**
	 * Sets the amount of XP that is stored in the given stack
	 * @param stack The stack to set the amount of stored XP of
	 * @param amount The amount of XP to set the storage to
	 */
	public void setStoredXP(ItemStack stack, int amount)
	{
		stack.setDamage(MAX_STORAGE - amount);
	}

	/**
	 * Gets the amount of XP that the given stack has stored
	 * @param stack The stack to get the amount of stored XP from
	 * @return The amount of stored XP in the stack
	 */
	public int getXPStored(ItemStack stack)
	{
		return MAX_STORAGE - stack.getDamage(); //if the damage is 0, the book is full on xp
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_medallion_exp.line1").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_medallion_exp.line3").mergeStyle(TextFormatting.BLUE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_medallion_exp.line2", getXPStored(stack)).mergeStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line3").mergeStyle(TextFormatting.YELLOW)));
	} 
}