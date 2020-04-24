package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.util.GobberConfigBuilder;

import net.minecraft.item.Food;

public class FoodList
{
	static int gooHunger = GobberConfigBuilder.GOO_HUNGER.get();
	static double gooSaturation = GobberConfigBuilder.GOO_SATURATION.get();
	static int gooeyAppleHunger = GobberConfigBuilder.GOOEY_APPLE_HUNGER.get();
	static double gooeyAppleSaturation = GobberConfigBuilder.GOOEY_APPLE_SATURATION.get();
	static int gooeyBreadHunger = GobberConfigBuilder.GOOEY_BREAD_HUNGER.get();
	static double gooeyBreadSaturation = GobberConfigBuilder.GOOEY_BREAD_SATURATION.get();
	static int gooeyBeefHunger = GobberConfigBuilder.GOOEY_BEEF_HUNGER.get();
	static double gooeyBeefSaturation = GobberConfigBuilder.GOOEY_BEEF_SATURATION.get();
	static int gooeyBeefstewHunger = GobberConfigBuilder.GOOEY_BEEFSTEW_HUNGER.get();
	static double gooeyBeefstewSaturation = GobberConfigBuilder.GOOEY_BEEFSTEW_SATURATION.get();
	
	static int netherGooHunger = GobberConfigBuilder.NETHER_GOO_HUNGER.get();
	static double netherGooSaturation = GobberConfigBuilder.NETHER_GOO_SATURATION.get();
	static int netherGooeyAppleHunger = GobberConfigBuilder.NETHER_GOOEY_APPLE_HUNGER.get();
	static double netherGooeyAppleSaturation = GobberConfigBuilder.NETHER_GOOEY_APPLE_SATURATION.get();
	static int netherGooeyBreadHunger = GobberConfigBuilder.NETHER_GOOEY_BREAD_HUNGER.get();
	static double netherGooeyBreadSaturation = GobberConfigBuilder.NETHER_GOOEY_BREAD_SATURATION.get();
	static int netherGooeyBeefHunger = GobberConfigBuilder.NETHER_GOOEY_BEEF_HUNGER.get();
	static double netherGooeyBeefSaturation = GobberConfigBuilder.NETHER_GOOEY_BEEF_SATURATION.get();
	static int netherGooeyBeefstewHunger = GobberConfigBuilder.NETHER_GOOEY_BEEFSTEW_HUNGER.get();
	static double netherGooeyBeefstewSaturation = GobberConfigBuilder.NETHER_GOOEY_BEEFSTEW_SATURATION.get();
	
	public static Food gooFood = (new Food.Builder()).hunger(gooHunger).saturation((float) gooSaturation).setAlwaysEdible().build();
	public static Food gooeyApple = (new Food.Builder()).hunger(gooeyAppleHunger).saturation((float) gooeyAppleSaturation).setAlwaysEdible().build();
	public static Food gooeyBread = (new Food.Builder()).hunger(gooeyBreadHunger).saturation((float) gooeyBreadSaturation).setAlwaysEdible().build();
	public static Food gooeyBeef = (new Food.Builder()).hunger(gooeyBeefHunger).saturation((float) gooeyBeefSaturation).meat().setAlwaysEdible().build();
	public static Food gooeyBeefstew = (new Food.Builder()).hunger(gooeyBeefstewHunger).saturation((float) gooeyBeefstewSaturation).setAlwaysEdible().build();
	
	public static Food gooFoodNether = (new Food.Builder()).hunger(netherGooHunger).saturation((float) netherGooSaturation).setAlwaysEdible().build();
	public static Food gooeyAppleNether = (new Food.Builder()).hunger(netherGooeyAppleHunger).saturation((float) netherGooeyAppleSaturation).setAlwaysEdible().build();
	public static Food gooeyBreadNether = (new Food.Builder()).hunger(netherGooeyBreadHunger).saturation((float) netherGooeyBreadSaturation).setAlwaysEdible().build();
	public static Food gooeyBeefNether = (new Food.Builder()).hunger(netherGooeyBeefHunger).saturation((float) netherGooeyBeefSaturation).meat().setAlwaysEdible().build();
	public static Food gooeyBeefstewNether = (new Food.Builder()).hunger(netherGooeyBeefstewHunger).saturation((float) netherGooeyBeefstewSaturation).setAlwaysEdible().build();
}
