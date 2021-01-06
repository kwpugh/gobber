package com.kwpugh.gobber2.util;

/*
 * This code is credited to bl4ckscor3
 * 
 * Taken from the mod XP Tome and adapted
 * 
 */

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;

public class ExpUtils
{
	public static int getPlayerXP(PlayerEntity player) 
	{
		return (int)(ExpUtils.getExperienceForLevel(player.experienceLevel) + (player.experience * player.xpBarCap()));
	}

	public static void addPlayerXP(PlayerEntity player, int amount) 
	{
		int experience = getPlayerXP(player) + amount;
		player.experienceTotal = experience;
		player.experienceLevel = ExpUtils.getLevelForExperience(experience);
		int expForLevel = ExpUtils.getExperienceForLevel(player.experienceLevel);
		player.experience = (experience - expForLevel) / (float)player.xpBarCap();
	}

	public static int xpBarCap(int level) 
    {
		if (level >= 30) 
		{
			return 112 + (level - 30) * 9;
		} 
		else 
		{
			return level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2;
		}
    }
	   
	private static int sum(int n, int a0, int d) 
	{
		return n * (2 * a0 + (n - 1) * d) / 2;
	}

	public static int getExperienceForLevel(int level) 
	{
		if (level == 0) return 0;
		if (level <= 15) return sum(level, 7, 2);
		if (level <= 30) return 315 + sum(level - 15, 37, 5);
		return 1395 + sum(level - 30, 112, 9);
	}

	public static int getXpToNextLevel(int level) 
	{
		int levelXP = ExpUtils.getLevelForExperience(level);
		int nextXP = ExpUtils.getExperienceForLevel(level + 1);
		return nextXP - levelXP;
	}

	public static int getLevelForExperience(int targetXp) 
	{
		int level = 0;
		while (true) {
			final int xpToNextLevel = xpBarCap(level);
			if (targetXp < xpToNextLevel) return level;
			level++;
			targetXp -= xpToNextLevel;
		}
	}

	public static void addAllBooks(Enchantment enchantment, List<ItemStack> items) 
	{
		for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); i++)
			items.add(EnchantedBookItem.getEnchantedItemStack(new EnchantmentData(enchantment, i)));
	}
}
