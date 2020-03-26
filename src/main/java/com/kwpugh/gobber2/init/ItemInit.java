package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.items.armor.ItemCustomArmorDragon;
import com.kwpugh.gobber2.items.armor.ItemCustomArmorEnd;
import com.kwpugh.gobber2.items.armor.ItemCustomArmorGobber;
import com.kwpugh.gobber2.items.armor.ItemCustomArmorNether;
import com.kwpugh.gobber2.items.foods.GooeyBeefstew;
import com.kwpugh.gobber2.items.foods.GooeyBeefstewNether;
import com.kwpugh.gobber2.items.fuels.ItemCustomFuel;
import com.kwpugh.gobber2.items.fuels.ItemCustomFuelEnd;
import com.kwpugh.gobber2.items.fuels.ItemCustomFuelNether;
import com.kwpugh.gobber2.items.medallions.ItemCustomMedallionBreathing;
import com.kwpugh.gobber2.items.medallions.ItemCustomMedallionConduit;
import com.kwpugh.gobber2.items.medallions.ItemCustomMedallionDolphin;
import com.kwpugh.gobber2.items.medallions.ItemCustomMedallionGlowing;
import com.kwpugh.gobber2.items.medallions.ItemCustomMedallionHero;
import com.kwpugh.gobber2.items.medallions.ItemCustomMedallionStepping;
import com.kwpugh.gobber2.items.rings.ItemCustomRing;
import com.kwpugh.gobber2.items.rings.ItemCustomRingAbove;
import com.kwpugh.gobber2.items.rings.ItemCustomRingAcceleration;
import com.kwpugh.gobber2.items.rings.ItemCustomRingAirwalking;
import com.kwpugh.gobber2.items.rings.ItemCustomRingAscent;
import com.kwpugh.gobber2.items.rings.ItemCustomRingAttraction;
import com.kwpugh.gobber2.items.rings.ItemCustomRingBlaze;
import com.kwpugh.gobber2.items.rings.ItemCustomRingBlink;
import com.kwpugh.gobber2.items.rings.ItemCustomRingCuring;
import com.kwpugh.gobber2.items.rings.ItemCustomRingDismissal;
import com.kwpugh.gobber2.items.rings.ItemCustomRingEnderchest;
import com.kwpugh.gobber2.items.rings.ItemCustomRingExplorer;
import com.kwpugh.gobber2.items.rings.ItemCustomRingFarmer;
import com.kwpugh.gobber2.items.rings.ItemCustomRingHaste;
import com.kwpugh.gobber2.items.rings.ItemCustomRingHusbandry;
import com.kwpugh.gobber2.items.rings.ItemCustomRingLeaping;
import com.kwpugh.gobber2.items.rings.ItemCustomRingLumberjack;
import com.kwpugh.gobber2.items.rings.ItemCustomRingMiner;
import com.kwpugh.gobber2.items.rings.ItemCustomRingPhoenix;
import com.kwpugh.gobber2.items.rings.ItemCustomRingPyro;
import com.kwpugh.gobber2.items.rings.ItemCustomRingRepair;
import com.kwpugh.gobber2.items.rings.ItemCustomRingReturn;
import com.kwpugh.gobber2.items.rings.ItemCustomRingStealth;
import com.kwpugh.gobber2.items.rings.ItemCustomRingSunshine;
import com.kwpugh.gobber2.items.rings.ItemCustomRingSwiftness;
import com.kwpugh.gobber2.items.rings.ItemCustomRingTeleport;
import com.kwpugh.gobber2.items.rings.ItemCustomRingTraveler;
import com.kwpugh.gobber2.items.rings.ItemCustomRingVision;
import com.kwpugh.gobber2.items.rings.ItemCustomRingVoid;
import com.kwpugh.gobber2.items.seeds.ItemCustomSeed;
import com.kwpugh.gobber2.items.seeds.ItemCustomSeedEnd;
import com.kwpugh.gobber2.items.seeds.ItemCustomSeedNether;
import com.kwpugh.gobber2.items.staffs.ItemCustomStaffClearing;
import com.kwpugh.gobber2.items.staffs.ItemCustomStaffEnsnarement;
import com.kwpugh.gobber2.items.staffs.ItemCustomStaffFarmer;
import com.kwpugh.gobber2.items.staffs.ItemCustomStaffHarvest;
import com.kwpugh.gobber2.items.staffs.ItemCustomStaffNature;
import com.kwpugh.gobber2.items.staffs.ItemCustomStaffSniper;
import com.kwpugh.gobber2.items.staffs.ItemCustomStaffStars;
import com.kwpugh.gobber2.items.tools.ItemCustomAxe;
import com.kwpugh.gobber2.items.tools.ItemCustomAxeEnd;
import com.kwpugh.gobber2.items.tools.ItemCustomAxeNether;
import com.kwpugh.gobber2.items.tools.ItemCustomBow;
import com.kwpugh.gobber2.items.tools.ItemCustomBowEnd;
import com.kwpugh.gobber2.items.tools.ItemCustomBowNether;
import com.kwpugh.gobber2.items.tools.ItemCustomExcavator;
import com.kwpugh.gobber2.items.tools.ItemCustomExcavatorEnd;
import com.kwpugh.gobber2.items.tools.ItemCustomExcavatorNether;
import com.kwpugh.gobber2.items.tools.ItemCustomHammer;
import com.kwpugh.gobber2.items.tools.ItemCustomHammerEnd;
import com.kwpugh.gobber2.items.tools.ItemCustomHammerNether;
import com.kwpugh.gobber2.items.tools.ItemCustomPaxel;
import com.kwpugh.gobber2.items.tools.ItemCustomPaxelEnd;
import com.kwpugh.gobber2.items.tools.ItemCustomPaxelNether;
import com.kwpugh.gobber2.items.tools.ItemCustomPaxelStars;
import com.kwpugh.gobber2.items.tools.ItemCustomPickaxe;
import com.kwpugh.gobber2.items.tools.ItemCustomPickaxeEnd;
import com.kwpugh.gobber2.items.tools.ItemCustomPickaxeNether;
import com.kwpugh.gobber2.items.tools.ItemCustomShovel;
import com.kwpugh.gobber2.items.tools.ItemCustomShovelEnd;
import com.kwpugh.gobber2.items.tools.ItemCustomShovelNether;
import com.kwpugh.gobber2.items.tools.ItemCustomSword;
import com.kwpugh.gobber2.items.tools.ItemCustomSwordEnd;
import com.kwpugh.gobber2.items.tools.ItemCustomSwordNether;
import com.kwpugh.gobber2.items.tools.ItemCustomSwordSniper;
import com.kwpugh.gobber2.items.tools.ItemCustomSwordTraveler;
import com.kwpugh.gobber2.items.tools.ItemCustomTreeAxe;
import com.kwpugh.gobber2.lists.ArmorMaterialList;
import com.kwpugh.gobber2.lists.BlockList;
import com.kwpugh.gobber2.lists.FoodList;
import com.kwpugh.gobber2.lists.ItemList;
import com.kwpugh.gobber2.lists.ToolMaterialList;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ItemInit
{
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			//General items
			event.getRegistry().registerAll
			(
				//Novelty Blocks
				ItemList.gobber2_lucky_block = new BlockItem(BlockList.gobber2_lucky_block, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_lucky_block.getRegistryName()),
			
				
				
				//Ores & Materials
				ItemList.gobber2_ore = new BlockItem(BlockList.gobber2_ore, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_ore.getRegistryName()),
				ItemList.gobber2_ore_nether = new BlockItem(BlockList.gobber2_ore_nether, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_ore_nether.getRegistryName()),
				ItemList.gobber2_ore_end = new BlockItem(BlockList.gobber2_ore_end, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_ore_end.getRegistryName()),
				
				ItemList.gobber2_globette = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_globette")),
				ItemList.gobber2_globette_nether = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_globette_nether")),
				ItemList.gobber2_globette_end = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_globette_end")),
				
				ItemList.gobber2_seed = new ItemCustomSeed("gobber2_seed", BlockList.gobber2_plant, (new Item.Properties().group(Gobber2.gobber2))),
				ItemList.gobber2_seed_nether = new ItemCustomSeedNether("gobber2_seed_nether", BlockList.gobber2_plant_nether, (new Item.Properties().group(Gobber2.gobber2))),
				ItemList.gobber2_seed_end = new ItemCustomSeedEnd("gobber2_seed_end", BlockList.gobber2_plant_end, (new Item.Properties().group(Gobber2.gobber2))),
						
				ItemList.gobber2_glob = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_glob")),
				ItemList.gobber2_glob_nether = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_glob_nether")),
				ItemList.gobber2_glob_end = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_glob_end")),
				
				ItemList.gobber2_ingot = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_ingot")),
				ItemList.gobber2_ingot_nether = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_ingot_nether")),
				ItemList.gobber2_ingot_end = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_ingot_end")),
				

				ItemList.gobber2_block = new BlockItem(BlockList.gobber2_block, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_block.getRegistryName()),
				ItemList.gobber2_block_nether = new BlockItem(BlockList.gobber2_block_nether, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_block_nether.getRegistryName()),
				ItemList.gobber2_block_end = new BlockItem(BlockList.gobber2_block_end, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_block_end.getRegistryName()),
				
				ItemList.gobber2_glass = new BlockItem(BlockList.gobber2_glass, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_glass.getRegistryName()),
				ItemList.gobber2_glass_nether = new BlockItem(BlockList.gobber2_glass_nether, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_glass_nether.getRegistryName()),
				ItemList.gobber2_glass_end = new BlockItem(BlockList.gobber2_glass_end, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.gobber2_glass_end.getRegistryName()),
				
				ItemList.gobber2_rod = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_rod")),		
				ItemList.gobber2_rod_nether = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_rod_nether")),
				ItemList.gobber2_rod_end = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_rod_end")),
				
				ItemList.gobber2_foo = new ItemCustomFuel(new Item.Properties().group(Gobber2.gobber2), "gobber2_foo", 64000),
				ItemList.gobber2_foo_nether = new ItemCustomFuelNether(new Item.Properties().group(Gobber2.gobber2), "gobber2_foo_nether", 96000),
				ItemList.gobber2_foo_end = new ItemCustomFuelEnd(new Item.Properties().group(Gobber2.gobber2), "gobber2_foo_end", 128000),
				
				
				
				
				//Foods
				ItemList.gobber2_goo = new Item(new Item.Properties().food(FoodList.gooFood).group(Gobber2.gobber2)).setRegistryName(location("gobber2_goo")),
				ItemList.gobber2_gooey_apple = new Item(new Item.Properties().food(FoodList.gooeyApple).group(Gobber2.gobber2)).setRegistryName(location("gobber2_gooey_apple")),
				ItemList.gobber2_gooey_bread = new Item(new Item.Properties().food(FoodList.gooeyBread).group(Gobber2.gobber2)).setRegistryName(location("gobber2_gooey_bread")),
				ItemList.gobber2_gooey_beef = new Item(new Item.Properties().food(FoodList.gooeyBeef).group(Gobber2.gobber2)).setRegistryName(location("gobber2_gooey_beef")),
				ItemList.gobber2_gooey_beefstew = new GooeyBeefstew(new Item.Properties().maxStackSize(1).food(FoodList.gooeyBeefstew).group(Gobber2.gobber2)).setRegistryName(location("gobber2_gooey_beefstew")),
				
				ItemList.gobber2_goo_nether = new Item(new Item.Properties().food(FoodList.gooFoodNether).group(Gobber2.gobber2)).setRegistryName(location("gobber2_goo_nether")),
				ItemList.gobber2_gooey_apple_nether = new Item(new Item.Properties().food(FoodList.gooeyAppleNether).group(Gobber2.gobber2)).setRegistryName(location("gobber2_gooey_apple_nether")),
				ItemList.gobber2_gooey_bread_nether = new Item(new Item.Properties().food(FoodList.gooeyBreadNether).group(Gobber2.gobber2)).setRegistryName(location("gobber2_gooey_bread_nether")),
				ItemList.gobber2_gooey_beef_nether = new Item(new Item.Properties().food(FoodList.gooeyBeefNether).group(Gobber2.gobber2)).setRegistryName(location("gobber2_gooey_beef_nether")),
				ItemList.gobber2_gooey_beefstew_nether = new GooeyBeefstewNether(new Item.Properties().maxStackSize(1).food(FoodList.gooeyBeefstewNether).group(Gobber2.gobber2)).setRegistryName(location("gobber2_gooey_beefstew_nether")),
					
				
				
				
				//Armor 
				ItemList.gobber2_armor_repair = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_armor_repair")),
						
				ItemList.gobber2_helmet = new ItemCustomArmorGobber(ArmorMaterialList.gobber2, EquipmentSlotType.HEAD, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_helmet")),
				ItemList.gobber2_chestplate = new ItemCustomArmorGobber(ArmorMaterialList.gobber2, EquipmentSlotType.CHEST, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_chestplate")),
				ItemList.gobber2_leggings = new ItemCustomArmorGobber(ArmorMaterialList.gobber2, EquipmentSlotType.LEGS, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_leggings")),
				ItemList.gobber2_boots = new ItemCustomArmorGobber(ArmorMaterialList.gobber2, EquipmentSlotType.FEET, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_boots")),
				
				ItemList.gobber2_helmet_nether = new ItemCustomArmorNether(ArmorMaterialList.gobber2_nether, EquipmentSlotType.HEAD, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_helmet_nether")),
				ItemList.gobber2_chestplate_nether = new ItemCustomArmorNether(ArmorMaterialList.gobber2_nether, EquipmentSlotType.CHEST, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_chestplate_nether")),
				ItemList.gobber2_leggings_nether = new ItemCustomArmorNether(ArmorMaterialList.gobber2_nether, EquipmentSlotType.LEGS, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_leggings_nether")),
				ItemList.gobber2_boots_nether = new ItemCustomArmorNether(ArmorMaterialList.gobber2_nether, EquipmentSlotType.FEET, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_boots_nether")),
				
				ItemList.gobber2_links_end = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_links_end")),
				ItemList.dragon_star = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("dragon_star")),
				ItemList.dragon_elytra = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("dragon_elytra")),
						
				ItemList.gobber2_helmet_end = new ItemCustomArmorEnd(ArmorMaterialList.gobber2_end, EquipmentSlotType.HEAD, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_helmet_end")),
				ItemList.gobber2_chestplate_end = new ItemCustomArmorEnd(ArmorMaterialList.gobber2_end, EquipmentSlotType.CHEST, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_chestplate_end")),
				ItemList.gobber2_leggings_end = new ItemCustomArmorEnd(ArmorMaterialList.gobber2_end, EquipmentSlotType.LEGS, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_leggings_end")),
				ItemList.gobber2_boots_end = new ItemCustomArmorEnd(ArmorMaterialList.gobber2_end, EquipmentSlotType.FEET, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_boots_end")),				

				ItemList.gobber2_helmet_dragon = new ItemCustomArmorDragon(ArmorMaterialList.gobber2_dragon, EquipmentSlotType.HEAD, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_helmet_dragon")),
				ItemList.gobber2_chestplate_dragon = new ItemCustomArmorDragon(ArmorMaterialList.gobber2_dragon, EquipmentSlotType.CHEST, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_chestplate_dragon")),
				ItemList.gobber2_leggings_dragon = new ItemCustomArmorDragon(ArmorMaterialList.gobber2_dragon, EquipmentSlotType.LEGS, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_leggings_dragon")),
				ItemList.gobber2_boots_dragon = new ItemCustomArmorDragon(ArmorMaterialList.gobber2_dragon, EquipmentSlotType.FEET, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_boots_dragon")),
		
				
						
				//Tools & Weapons
				ItemList.gobber2_sword = new ItemCustomSword(ToolMaterialList.gobber2, 9, -2.0f, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_sword")),
				ItemList.gobber2_bow = new ItemCustomBow((new Item.Properties()).maxStackSize(1).group(Gobber2.gobber2).maxDamage(3800)).setRegistryName(location("gobber2_bow")),
				ItemList.gobber2_pickaxe = new ItemCustomPickaxe(ToolMaterialList.gobber2, 4, -2.4f, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_pickaxe")),				
				ItemList.gobber2_shovel = new ItemCustomShovel(ToolMaterialList.gobber2, 5.0f, -3.0f, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_shovel")),
				ItemList.gobber2_axe = new ItemCustomAxe(ToolMaterialList.gobber2, 8.0f, -2.9f, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_axe")),
				ItemList.gobber2_tree_axe = new ItemCustomTreeAxe(ToolMaterialList.gobber2, 8.0f, -2.9f, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_tree_axe")),
				ItemList.gobber2_hoe = new HoeItem(ToolMaterialList.gobber2, 2.1f, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_hoe")),
				ItemList.gobber2_hammer = new ItemCustomHammer(ToolMaterialList.gobber2, 7, -2.9f, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_hammer")),
				ItemList.gobber2_excavator = new ItemCustomExcavator(ToolMaterialList.gobber2, 5.0f, -3.0f, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_excavator")),
				ItemList.gobber2_paxel = new ItemCustomPaxel(8, -2.9f, ToolMaterialList.gobber2, null, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_paxel")),
						
				ItemList.gobber2_sword_nether = new ItemCustomSwordNether(ToolMaterialList.gobber2_nether, 11, -1.8f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_sword_nether")),			
				ItemList.gobber2_bow_nether = new ItemCustomBowNether((new Item.Properties()).maxStackSize(1).group(Gobber2.gobber2).maxDamage(5200)).setRegistryName(location("gobber2_bow_nether")),
				ItemList.gobber2_pickaxe_nether = new ItemCustomPickaxeNether(ToolMaterialList.gobber2_nether, 5, -2.3f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_pickaxe_nether")),								
				ItemList.gobber2_shovel_nether = new ItemCustomShovelNether(ToolMaterialList.gobber2_nether, 6.0f, -2.8f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_shovel_nether")),
				ItemList.gobber2_axe_nether = new ItemCustomAxeNether(ToolMaterialList.gobber2_nether, 10.0f, -2.8f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_axe_nether")),
				ItemList.gobber2_tree_axe_nether = new ItemCustomTreeAxe(ToolMaterialList.gobber2_nether, 10.0f, -2.8f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_tree_axe_nether")),
				ItemList.gobber2_hoe_nether = new HoeItem(ToolMaterialList.gobber2_nether, 2.2f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_hoe_nether")),	
				ItemList.gobber2_hammer_nether = new ItemCustomHammerNether(ToolMaterialList.gobber2_nether, 8, -2.8f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_hammer_nether")),
				ItemList.gobber2_excavator_nether = new ItemCustomExcavatorNether(ToolMaterialList.gobber2_nether, 6.0f, -2.8f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_excavator_nether")),
				ItemList.gobber2_paxel_nether = new ItemCustomPaxelNether(10, -2.8f, ToolMaterialList.gobber2_nether, null, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_paxel_nether")),					
						
				ItemList.gobber2_sword_end = new ItemCustomSwordEnd(ToolMaterialList.gobber2_end, 14, -1.6f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_sword_end")),				
				ItemList.gobber2_sword_sniper = new ItemCustomSwordSniper(ToolMaterialList.gobber2_end, 14, -1.6f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_sword_sniper")),
				ItemList.gobber2_sword_traveler = new ItemCustomSwordTraveler(ToolMaterialList.gobber2_end, 14, -1.6f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_sword_traveler")),
				ItemList.gobber2_bow_end = new ItemCustomBowEnd((new Item.Properties()).maxStackSize(1).group(Gobber2.gobber2).maxDamage(8000)).setRegistryName(location("gobber2_bow_end")),
				ItemList.gobber2_pickaxe_end = new ItemCustomPickaxeEnd(ToolMaterialList.gobber2_end, 6, -2.2f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_pickaxe_end")),
				ItemList.gobber2_shovel_end = new ItemCustomShovelEnd(ToolMaterialList.gobber2_end, 8.0f, -2.6f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_shovel_end")),
				ItemList.gobber2_axe_end = new ItemCustomAxeEnd(ToolMaterialList.gobber2_end, 14.0f, -2.7f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_axe_end")),
				ItemList.gobber2_tree_axe_end = new ItemCustomTreeAxe(ToolMaterialList.gobber2_end, 14.0f, -2.7f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_tree_axe_end")),
				ItemList.gobber2_hoe_end = new HoeItem(ToolMaterialList.gobber2_end, 2.3f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_hoe_end")),
				ItemList.gobber2_hammer_end = new ItemCustomHammerEnd(ToolMaterialList.gobber2_end, 10, -2.7f, new Item.Properties().maxStackSize(1).maxDamage(0).group(Gobber2.gobber2)).setRegistryName(location("gobber2_hammer_end")),		
				ItemList.gobber2_excavator_end = new ItemCustomExcavatorEnd(ToolMaterialList.gobber2_end, 8.0f, -2.6f, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_excavator_end")),
				ItemList.gobber2_paxel_end = new ItemCustomPaxelEnd(13, -2.7f, ToolMaterialList.gobber2_end, null, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_paxel_end")),
				ItemList.gobber2_paxel_stars = new ItemCustomPaxelStars(13, -2.7f, ToolMaterialList.gobber2_end, null, new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_paxel_stars")),
				
				
				
				//Rings
				ItemList.gobber2_ring = new ItemCustomRing(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring")),
				ItemList.gobber2_ring_attraction = new ItemCustomRingAttraction(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_attraction")),
				ItemList.gobber2_ring_return = new ItemCustomRingReturn(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_return")),
				ItemList.gobber2_ring_ascent = new ItemCustomRingAscent(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_ascent")),
				ItemList.gobber2_ring_above = new ItemCustomRingAbove(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_above")),
				ItemList.gobber2_ring_miner = new ItemCustomRingMiner(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_miner")),
				ItemList.gobber2_ring_lumberjack = new ItemCustomRingLumberjack(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_lumberjack")),
				ItemList.gobber2_ring_farmer = new ItemCustomRingFarmer(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_farmer")),
				ItemList.gobber2_ring_husbandry = new ItemCustomRingHusbandry(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_husbandry")),
				ItemList.gobber2_ring_swiftness = new ItemCustomRingSwiftness(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_swiftness")),
				ItemList.gobber2_ring_sunshine = new ItemCustomRingSunshine(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_sunshine")),

				ItemList.gobber2_ring_nether = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_nether")),
				ItemList.gobber2_ring_acceleration = new ItemCustomRingAcceleration(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_acceleration")),				
				ItemList.gobber2_ring_leaping = new ItemCustomRingLeaping(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_leaping")),		
				ItemList.gobber2_ring_curing = new ItemCustomRingCuring(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_curing")),
				ItemList.gobber2_ring_vision = new ItemCustomRingVision(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_vision")),
				ItemList.gobber2_ring_phoenix = new ItemCustomRingPhoenix(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_phoenix")),		
				ItemList.gobber2_ring_haste = new ItemCustomRingHaste(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_haste")),
				ItemList.gobber2_ring_blaze = new ItemCustomRingBlaze(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_blaze")),
				ItemList.gobber2_ring_repair = new ItemCustomRingRepair(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_repair")),								
				
				ItemList.gobber2_ring_end = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_end")),
				ItemList.gobber2_ring_dismissal = new ItemCustomRingDismissal(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_dismissal")),
				ItemList.gobber2_ring_pyro = new ItemCustomRingPyro(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_pyro")),
				ItemList.gobber2_ring_enderchest = new ItemCustomRingEnderchest(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_enderchest")),
				ItemList.gobber2_ring_traveler = new ItemCustomRingTraveler(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_traveler")),
				ItemList.gobber2_ring_void = new ItemCustomRingVoid(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_void")),		
				ItemList.gobber2_ring_airwalking = new ItemCustomRingAirwalking(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_airwalking")),
				ItemList.gobber2_ring_stealth = new ItemCustomRingStealth(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_stealth")),
				ItemList.gobber2_ring_teleport = new ItemCustomRingTeleport(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_teleport")),
				ItemList.gobber2_ring_blink = new ItemCustomRingBlink(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_blink")),
				ItemList.gobber2_ring_explorer = new ItemCustomRingExplorer(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_ring_explorer")),
						
				
				//Medallions
				ItemList.gobber2_medallion = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion")),
				ItemList.gobber2_medallion_nether = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion_nether")),
				ItemList.gobber2_medallion_end = new Item(new Item.Properties().group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion_end")),
				ItemList.gobber2_medallion_stepping = new ItemCustomMedallionStepping(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion_stepping")),
				ItemList.gobber2_medallion_breathing = new ItemCustomMedallionBreathing(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion_breathing")),
				ItemList.gobber2_medallion_hero = new ItemCustomMedallionHero(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion_hero")),
				ItemList.gobber2_medallion_glowing = new ItemCustomMedallionGlowing(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion_glowing")),
				ItemList.gobber2_medallion_conduit = new ItemCustomMedallionConduit(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion_conduit")),
				ItemList.gobber2_medallion_dolphin = new ItemCustomMedallionDolphin(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_medallion_dolphin")),
				
				//Staffs
				ItemList.gobber2_staff_clearing = new ItemCustomStaffClearing(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_staff_clearing")),
				ItemList.gobber2_staff_nature = new ItemCustomStaffNature(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_staff_nature")),
				ItemList.gobber2_staff_farmer = new ItemCustomStaffFarmer(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_staff_farmer")),
				ItemList.gobber2_staff_harvest = new ItemCustomStaffHarvest(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_staff_harvest")),
				ItemList.gobber2_staff_stars = new ItemCustomStaffStars(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_staff_stars")),
				ItemList.gobber2_staff_ensnarement = new ItemCustomStaffEnsnarement(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_staff_ensnarement")),
				ItemList.gobber2_staff_sniper = new ItemCustomStaffSniper(new Item.Properties().maxStackSize(1).group(Gobber2.gobber2)).setRegistryName(location("gobber2_staff_sniper")),
	
				//Blocks		
				ItemList.block_healer = new BlockItem(BlockList.block_healer, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.block_healer.getRegistryName()),				
				ItemList.block_protector = new BlockItem(BlockList.block_protector, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.block_protector.getRegistryName()),
				ItemList.block_defender = new BlockItem(BlockList.block_defender, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.block_defender.getRegistryName()),
				ItemList.block_maturator = new BlockItem(BlockList.block_maturator, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.block_maturator.getRegistryName()),
				ItemList.block_looter = new BlockItem(BlockList.block_looter, new Item.Properties().group(Gobber2.gobber2)).setRegistryName(BlockList.block_looter.getRegistryName())
				
			);		
			Gobber2.logger.info("Gobber Items registered.");
		}
		
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(Gobber2.modid, name);
		}
	}
}
