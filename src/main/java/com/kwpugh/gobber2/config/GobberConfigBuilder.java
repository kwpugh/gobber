package com.kwpugh.gobber2.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GobberConfigBuilder
{
    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_SIZE;
    //public static ForgeConfigSpec.IntValue GOBBER2_ORE_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_MAX_HEIGHT;

    public static ForgeConfigSpec.BooleanValue GOBBER2_LUCKY_BLOCK_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_SIZE;
    //public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_MAX_HEIGHT;

    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_NETHER_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_SIZE;
    //public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_MAX_HEIGHT;

    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_END_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_END_COUNT;

    public static ForgeConfigSpec.IntValue RING_BLINK_COOLDOWN;
    public static ForgeConfigSpec.IntValue RING_MINER_COOLDOWN;
    public static ForgeConfigSpec.IntValue RING_LUMBERJACK_COOLDOWN;
    public static ForgeConfigSpec.IntValue RING_ABOVE_COOLDOWN;
    public static ForgeConfigSpec.IntValue RING_EXPLORER_COOLDOWN;
    public static ForgeConfigSpec.IntValue SNIPER_SWORD_COOLDOWN;
    public static ForgeConfigSpec.IntValue SNIPER_STAFF_COOLDOWN;

    public static ForgeConfigSpec.IntValue RING_EXPLORER_MIN_RANGE;
    public static ForgeConfigSpec.IntValue RING_EXPLORER_MAX_RANGE;

    public static ForgeConfigSpec.BooleanValue REVERSE_RING_MINER;
    public static ForgeConfigSpec.BooleanValue DELAY_BREAK_MODE;
    public static ForgeConfigSpec.DoubleValue RING_ACCELERATION_VELOCITY;
    public static ForgeConfigSpec.IntValue RING_REPAIR_DELAY;
    public static ForgeConfigSpec.IntValue HASTE_RING_BREAK_SPEED;

    public static ForgeConfigSpec.IntValue RING_FARMER_TICK_DELAY;
    public static ForgeConfigSpec.IntValue RING_FARMER_RADIUS;
    public static ForgeConfigSpec.IntValue STAFF_FARMER_TICK_DELAY;
    public static ForgeConfigSpec.IntValue STAFF_FARMER_RADIUS;

    public static ForgeConfigSpec.BooleanValue STAFF_HARVEST_REPLANT;
    public static ForgeConfigSpec.BooleanValue STAFF_FARMER_REPLANT;
    
    public static ForgeConfigSpec.IntValue MEDALLION_EXP_ORBS;
    public static ForgeConfigSpec.IntValue MEDALLION_EXP_LOOT;

    public static ForgeConfigSpec.IntValue GOO_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOO_SATURATION;
    public static ForgeConfigSpec.IntValue GOOEY_APPLE_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOOEY_APPLE_SATURATION;
    public static ForgeConfigSpec.IntValue GOOEY_BREAD_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOOEY_BREAD_SATURATION;
    public static ForgeConfigSpec.IntValue GOOEY_BEEF_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOOEY_BEEF_SATURATION;
    public static ForgeConfigSpec.IntValue GOOEY_BEEFSTEW_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOOEY_BEEFSTEW_SATURATION;

    public static ForgeConfigSpec.IntValue NETHER_GOO_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOO_SATURATION;
    public static ForgeConfigSpec.IntValue NETHER_GOOEY_APPLE_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOOEY_APPLE_SATURATION;
    public static ForgeConfigSpec.IntValue NETHER_GOOEY_BREAD_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOOEY_BREAD_SATURATION;
    public static ForgeConfigSpec.IntValue NETHER_GOOEY_BEEF_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOOEY_BEEF_SATURATION;
    public static ForgeConfigSpec.IntValue NETHER_GOOEY_BEEFSTEW_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOOEY_BEEFSTEW_SATURATION;

    public static ForgeConfigSpec.IntValue HEALER_RADIUS;
    public static ForgeConfigSpec.IntValue PROTECTOR_RADIUS;
    public static ForgeConfigSpec.IntValue DEFENDER_RADIUS;
    public static ForgeConfigSpec.IntValue LOOTER_RADIUS;
    public static ForgeConfigSpec.IntValue MATURATOR_RADIUS;
    public static ForgeConfigSpec.IntValue MATURATOR_VERTICAL_RANGE;
    public static ForgeConfigSpec.IntValue MATURATOR_MIN_TICK;
    public static ForgeConfigSpec.IntValue MATURATOR_MAX_TICK;
    public static ForgeConfigSpec.BooleanValue ENABLE_MATURATOR_ANIMAL_EFFECT;

    public static ForgeConfigSpec.BooleanValue ENABLE_DRAGON_ARMOR_VOID_PROTECTION;
    public static ForgeConfigSpec.BooleanValue ENABLE_DRAGON_KILL_EVERY_KILL;

    public static void init(ForgeConfigSpec.Builder SERVER_BUILDER)
    {
        SERVER_BUILDER.comment("Gobber Food Values").push("gobber_foods");

        GOO_HUNGER = SERVER_BUILDER.comment("Goo hunger value [0-25, default: 7]").defineInRange("gooHunger", 7, 0, 25);
        GOO_SATURATION = SERVER_BUILDER.comment("Goo saturation value [0-5, default: 0.7]").defineInRange("gooSaturation", 0.7, 0, 5.0);
        GOOEY_APPLE_HUNGER = SERVER_BUILDER.comment("Gooey apple hunger value [0-25, default: 8]").defineInRange("gooeyAppleHunger", 8, 0, 25);
        GOOEY_APPLE_SATURATION = SERVER_BUILDER.comment("Gooey apple saturation value [0-5, default: 0.8]").defineInRange("gooeyAppleSaturation", 0.8, 0, 5.0);
        GOOEY_BREAD_HUNGER = SERVER_BUILDER.comment("Gooey bread hunger value [0-25, default: 8]").defineInRange("gooeyBreadHunger", 8, 0, 25);
        GOOEY_BREAD_SATURATION = SERVER_BUILDER.comment("Gooey bread saturation value [0-5, default: 0.8]").defineInRange("gooeyBreadSaturation", 0.8, 0, 5.0);
        GOOEY_BEEF_HUNGER = SERVER_BUILDER.comment("Gooey beef hunger value [0-25, default: 9]").defineInRange("gooeyBeefHunger", 9, 0, 25);
        GOOEY_BEEF_SATURATION = SERVER_BUILDER.comment("Gooey beef saturation value [0-5, default: 0.9]").defineInRange("gooeyBeefSaturation", 0.9, 0, 5.0);
        GOOEY_BEEFSTEW_HUNGER = SERVER_BUILDER.comment("Gooey beefstew hunger value [0-25, default: 10]").defineInRange("gooeyBeefstewHunger", 10, 0, 25);
        GOOEY_BEEFSTEW_SATURATION = SERVER_BUILDER.comment("Gooey beefstew saturation value [0-5, default: 1.0]").defineInRange("gooeyBeefstewSaturation", 1.0, 0, 5.0);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Nether Gobber Food Values").push("nether_gobber_foods");

        NETHER_GOO_HUNGER = SERVER_BUILDER.comment("Nether Goo hunger value [0-25, default: 7]").defineInRange("netherGooHunger", 9, 0, 25);
        NETHER_GOO_SATURATION = SERVER_BUILDER.comment("Nether Goo saturation value [0-5, default: 0.7]").defineInRange("netherGooSaturation", 0.9, 0, 5.0);
        NETHER_GOOEY_APPLE_HUNGER = SERVER_BUILDER.comment("Nether Gooey apple hunger value [0-25, default: 8]").defineInRange("netherGooeyAppleHunger", 10, 0, 25);
        NETHER_GOOEY_APPLE_SATURATION = SERVER_BUILDER.comment("Nether Gooey apple saturation value [0-5, default: 0.8]").defineInRange("netherGooeyAppleSaturation", 1.0, 0, 5.0);
        NETHER_GOOEY_BREAD_HUNGER = SERVER_BUILDER.comment("Nether Gooey bread hunger value [0-25, default: 8]").defineInRange("netherGooeyBreadHunger", 10, 0, 25);
        NETHER_GOOEY_BREAD_SATURATION = SERVER_BUILDER.comment("Nether Gooey bread saturation value [0-5, default: 0.8]").defineInRange("netherGooeyBreadSaturation", 1.0, 0, 5.0);
        NETHER_GOOEY_BEEF_HUNGER = SERVER_BUILDER.comment("Nether Gooey beef hunger value [0-25, default: 9]").defineInRange("netherGooeyBeefHunger", 12, 0, 25);
        NETHER_GOOEY_BEEF_SATURATION = SERVER_BUILDER.comment("Nether Gooey beef saturation value [0-5, default: 0.9]").defineInRange("netherGooeyBeefSaturation", 1.2, 0, 5.0);
        NETHER_GOOEY_BEEFSTEW_HUNGER = SERVER_BUILDER.comment("Nether Gooey beefstew hunger value [0-25, default: 10]").defineInRange("netherGooeyBeefstewHunger", 14, 0, 25);
        NETHER_GOOEY_BEEFSTEW_SATURATION = SERVER_BUILDER.comment("Nether Gooey beefstew saturation value [0-5, default: 1.0]").defineInRange("netherGooeyBeefstewSaturation", 1.5, 0, 5.0);

        SERVER_BUILDER.pop();


    	SERVER_BUILDER.comment("Gobber Ore Generation").push("gobber2_ore");

        GOBBER2_ORE_GENERATION = SERVER_BUILDER.comment("Generate Gobber Ore in the world [true / false]").define("gobberOreGeneration", true);
        GOBBER2_ORE_SIZE = SERVER_BUILDER.comment("Size of Gobber Ore pockets [0-100, default: 3]").defineInRange("gobberOreSize", 3, 0, 100);
        GOBBER2_ORE_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Ore pocket being generated [0-100, default: 10]").defineInRange("gobberOreChance", 10, 0, 100);
        //GOBBER2_ORE_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Ore pocket generation, [0-255, default: 20]").defineInRange("gobberOreMinHeight", 20, 0, 255);
        GOBBER2_ORE_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Ore pocket generation [0-255, default: 30]").defineInRange("gobberOreMaxHeight", 30, 0, 255);

        SERVER_BUILDER.pop();

        
        SERVER_BUILDER.comment("Gobber Nether Ore Generation").push("gobber2_ore_nether");

        GOBBER2_ORE_NETHER_GENERATION = SERVER_BUILDER.comment("Generate Gobber Nether Ore in the world [true / false]").define("gobberOreNetherGeneration", true);
        GOBBER2_ORE_NETHER_SIZE = SERVER_BUILDER.comment("Size of Gobber Nether Ore pockets [0-100, default: 1]").defineInRange("gobberOreNetherSize", 3, 0, 100);
        GOBBER2_ORE_NETHER_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Nether Ore pocket being generated [0-100, default: 10]").defineInRange("gobberOreNetherChance", 80, 0, 100);
        //GOBBER2_ORE_NETHER_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Nether Ore pocket generation, [0-255, default: 5]").defineInRange("gobberOreNetherMinHeight", 20, 0, 255);
        GOBBER2_ORE_NETHER_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Nether Ore pocket generation [0-255, default: 50]").defineInRange("gobberOreNetherMaxHeight", 255, 0, 255);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Gobber End Ore Generation").push("gobber2_ore_end");

        GOBBER2_ORE_END_GENERATION = SERVER_BUILDER.comment("Generate Gobber End Ore in the world [true / false]").define("gobberOreEndGeneration", true);
        GOBBER2_ORE_END_COUNT = SERVER_BUILDER.comment("Count of Gobber End Ore possibly being generated [0-800, default: 90]").defineInRange("gobberOreEndCount", 90, 0, 800);

        SERVER_BUILDER.pop();
        

        SERVER_BUILDER.comment("Gobber Lucky Block Generation").push("gobber2_lucky_block");

        GOBBER2_LUCKY_BLOCK_GENERATION = SERVER_BUILDER.comment("Generate Gobber Lucky Block in the world [true / false]").define("gobberLuckyBlockGeneration", true);
        GOBBER2_LUCKY_BLOCK_SIZE = SERVER_BUILDER.comment("Size of Gobber Lucky Block pockets [0-100, default: 3]").defineInRange("gobberLuckyBlockSize", 3, 0, 100);
        GOBBER2_LUCKY_BLOCK_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Lucky Block pocket being generated [0-100, default: 20]").defineInRange("gobberLuckyBlockChance", 20, 0, 100);
        //GOBBER2_LUCKY_BLOCK_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Lucky Block pocket generation, [0-255, default: 1]").defineInRange("gobberLuckyBlockMinHeight", 1, 0, 255);
        GOBBER2_LUCKY_BLOCK_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Lucky Block pocket generation [0-255, default: 255]").defineInRange("gobberLuckyBlockMaxHeight", 255, 0, 255);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Item Cooldown Settings").push("item_cooldown_settings");

        RING_BLINK_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of Blink cooldown [0-120, default: 60]").defineInRange("ringBlinkCooldown", 60, 0, 120);
        RING_LUMBERJACK_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of the Lumberjack cooldown [0-240, default: 80]").defineInRange("ringLumberjackCooldown", 80, 0, 120);
        RING_MINER_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of the Miner cooldown [0-240, default: 80]").defineInRange("ringMinerCooldown", 80, 0, 120);
        RING_ABOVE_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of Above cooldown [0-240, default: 80]").defineInRange("ringAboveCooldown", 80, 0, 120);
        RING_EXPLORER_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of the Explorer cooldown [0-1200, default: 480]").defineInRange("ringExplorerCooldown", 480, 0, 1200);
        SNIPER_SWORD_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Sword of the Sniper cooldown [0-120, default: 60]").defineInRange("swordSniperCooldown", 60, 0, 120);
        SNIPER_STAFF_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Staff of the Sniper cooldown [0-120, default: 60]").defineInRange("staffSniperCooldown", 60, 0, 120);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Ring of the Exploer Settings").push("ring_explorer_settings");

        RING_EXPLORER_MIN_RANGE = SERVER_BUILDER.comment("Ring of Explorer - Min distance from world spawn to begin searching for a spot [default: 500]").defineInRange("ringExplorerMin", 500, 0, 100000);
        RING_EXPLORER_MAX_RANGE = SERVER_BUILDER.comment("Ring of Explorer - Max distance from world spawn to begin searching for a spot [default: 6000]").defineInRange("ringExplorerMax", 6000, 0, 250000);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Misc Ring/staff Settings").push("misc_ring_staff_settings");

        REVERSE_RING_MINER = SERVER_BUILDER.comment("Reverse the drop/no drops feature on Ring of Miner [true / false]").define("reverseRingMiner", false);
        DELAY_BREAK_MODE = SERVER_BUILDER.comment("Uses a delayed break mode to reduce stutter and potential lag on Ring of Miner and Ring of Lumberjack [true / false]").define("delayedBreakMode", true);
        RING_ACCELERATION_VELOCITY = SERVER_BUILDER.comment("Ring of Acceleration - amount of velocity applied [default: .18]").defineInRange("ringAccelerationVelocity", .18, 0.0, .30);
        RING_REPAIR_DELAY = SERVER_BUILDER.comment("Ring of Repair - Delay time between repair ticks [default: 120]").defineInRange("ringRepairDelay", 120, 20, 600);
        HASTE_RING_BREAK_SPEED = SERVER_BUILDER.comment("Ring of Haste - Block break speed [default: 30]").defineInRange("ringHasteBreakSpeed", 30, 10, 100);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Ring/Staff of Farmer Settings").push("farmer_settings");

        RING_FARMER_TICK_DELAY = SERVER_BUILDER.comment("Ring of the Farmer base tick delay [default: 20]").defineInRange("ringFarmerTickDelay", 20, 10, 120);
        RING_FARMER_RADIUS = SERVER_BUILDER.comment("Ring of the Farmer radius from player [default: 10]").defineInRange("ringFarmerRadius", 10, 2, 20);
        STAFF_FARMER_TICK_DELAY = SERVER_BUILDER.comment("Staff of the Farmer base tick delay [default: 20]").defineInRange("staffFarmerTickDelay", 20, 10, 120);
        STAFF_FARMER_RADIUS = SERVER_BUILDER.comment("Staff of the Farmer radius from player [default: 10]").defineInRange("staffFarmerRadius", 10, 2, 20);

        SERVER_BUILDER.pop();

        
        SERVER_BUILDER.comment("Auto Replanting Settings").push("replanting_settings");
      
        STAFF_HARVEST_REPLANT = SERVER_BUILDER.comment("Should the Staff of Harvest auto-replant? [true / false]").define("StaffHarvestReplant", true);
        STAFF_FARMER_REPLANT = SERVER_BUILDER.comment("Should the Staff of the Farmer auto-replant? [true / false]").define("StaffFarmerReplant", true);

        SERVER_BUILDER.pop();
        

        SERVER_BUILDER.comment("Medallion of Experience Settings").push("medallion_exp_settings");

        MEDALLION_EXP_ORBS = SERVER_BUILDER.comment("Medallion of Experience multiplier for extra XP orbs [default: 5]").defineInRange("medallionExpOrbs", 5, 0, 20);
        MEDALLION_EXP_LOOT = SERVER_BUILDER.comment("Medallion of Experience multiplier for extra mob loot [default: 10]").defineInRange("medallionExpLoot", 20, 5, 100);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Settings for area effect blocks").push("area_block_effect_settings");

        HEALER_RADIUS = SERVER_BUILDER.comment("Block range for Healer block effects [default: 12]").defineInRange("healerRange", 12, 0, 16);
        PROTECTOR_RADIUS = SERVER_BUILDER.comment("Block range for Protector block effects [default: 24]").defineInRange("protectorRange", 24, 0, 32);
        DEFENDER_RADIUS = SERVER_BUILDER.comment("Block range for Defender block effects [default: 32]").defineInRange("defenderRange", 32, 0, 64);
        LOOTER_RADIUS = SERVER_BUILDER.comment("Block rangefor Looter block effects [default: 24]").defineInRange("looterRange", 24, 0, 32);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Settings for Maturator").push("maturator_settings");

        MATURATOR_RADIUS = SERVER_BUILDER.comment("Block range for Maturator block effects [default: 16]").defineInRange("maturatorRange", 10, 0, 20);
        MATURATOR_VERTICAL_RANGE = SERVER_BUILDER.comment("Vertical block range for Maturator block effects [default: 10]").defineInRange("maturatorVerticalRange", 5, 0, 10);
        MATURATOR_MIN_TICK = SERVER_BUILDER.comment("Min interval of world ticks for the Maturator [default: 120]").defineInRange("maturatorMinTick", 120, 0, 240);
        MATURATOR_MAX_TICK = SERVER_BUILDER.comment("Max interval of world ticks for the Maturator [default: 240]").defineInRange("maturatorMaxTick", 240, 0, 480);
        ENABLE_MATURATOR_ANIMAL_EFFECT = SERVER_BUILDER.comment("Enable Maturator effect on baby animals [true / false]").define("enableMaturatorAnimalEffect", false);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Enable Dragon Armor Void Protect - immune to /kill command").push("dragon_armor_void");

        ENABLE_DRAGON_ARMOR_VOID_PROTECTION = SERVER_BUILDER.comment("Enable Dragon Armor Void Protection [true / false]").define("enableDragonArmorVoidProtection", false);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Dragon Egg With Every Kill").push("dragon_egg_kill");

        ENABLE_DRAGON_KILL_EVERY_KILL = SERVER_BUILDER.comment("Enable Dragon Egg drops on every kill [true / false]").define("enableDragonEggEveryKill", false);

        SERVER_BUILDER.pop();
    }
}
