package com.kwpugh.gobber2.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralModConfig
{
    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_SIZE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_MAX_HEIGHT;

    public static ForgeConfigSpec.BooleanValue GOBBER2_LUCKY_BLOCK_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_SIZE;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_MAX_HEIGHT;

    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_NETHER_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_SIZE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_MAX_HEIGHT;
    
    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_END_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_END_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_END_SIZE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_END_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_END_MAX_HEIGHT;
    
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
    
    public static ForgeConfigSpec.IntValue MATURATOR_MIN_TICK;
    public static ForgeConfigSpec.IntValue MATURATOR_MAX_TICK;
    public static ForgeConfigSpec.BooleanValue ENABLE_MATURATOR_ANIMAL_EFFECT;
    
    public static ForgeConfigSpec.BooleanValue ENABLE_DRAGON_KILL_EVERY_KILL;
    
    
    public static void init(ForgeConfigSpec.Builder SERVER_BUILDER)
    {
        SERVER_BUILDER.comment("Gobber Ore Generation").push("gobber2_ore");

        GOBBER2_ORE_GENERATION = SERVER_BUILDER.comment("Generate Gobber Ore in the world [true / false]").define("gobberOreGeneration", true);
        GOBBER2_ORE_SIZE = SERVER_BUILDER.comment("Size of Gobber Ore pockets [0-100, default: 3]").defineInRange("gobberOreSize", 3, 0, 100);
        GOBBER2_ORE_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Ore pocket being generated [0-100, default: 10]").defineInRange("gobberOreChance", 10, 0, 100);
        GOBBER2_ORE_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Ore pocket generation, [0-255, default: 20]").defineInRange("gobberOReMinHeight", 20, 0, 255);
        GOBBER2_ORE_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Ore pocket generation [0-255, default: 30]").defineInRange("gobberOreMaxHeight", 30, 0, 255);

        SERVER_BUILDER.pop();
        
        
        SERVER_BUILDER.comment("Gobber Lucky Block Generation").push("gobber2_lucky_block");

        GOBBER2_LUCKY_BLOCK_GENERATION = SERVER_BUILDER.comment("Generate Gobber Lucky Block in the world [true / false]").define("gobberLuckyBlockGeneration", true);
        GOBBER2_LUCKY_BLOCK_SIZE = SERVER_BUILDER.comment("Size of Gobber Lucky Block pockets [0-100, default: 3]").defineInRange("gobberLuckyBlockSize", 3, 0, 100);
        GOBBER2_LUCKY_BLOCK_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Lucky Block pocket being generated [0-100, default: 20]").defineInRange("gobberLuckyBlockChance", 20, 0, 100);
        GOBBER2_LUCKY_BLOCK_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Lucky Block pocket generation, [0-255, default: 1]").defineInRange("gobberLuckyBlockMinHeight", 1, 0, 255);
        GOBBER2_LUCKY_BLOCK_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Lucky Block pocket generation [0-255, default: 255]").defineInRange("gobberLuckyBlockMaxHeight", 255, 0, 255);

        SERVER_BUILDER.pop();
        
        
        SERVER_BUILDER.comment("Gobber Nether Ore Generation").push("gobber2_ore_nether");

        GOBBER2_ORE_NETHER_GENERATION = SERVER_BUILDER.comment("Generate Gobber Nether Ore in the world [true / false]").define("gobberOreNetherGeneration", true);
        GOBBER2_ORE_NETHER_SIZE = SERVER_BUILDER.comment("Size of Gobber Nether Ore pockets [0-100, default: 1]").defineInRange("gobberOreNetherSize", 3, 0, 100);
        GOBBER2_ORE_NETHER_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Nether Ore pocket being generated [0-100, default: 10]").defineInRange("gobberOreNetherChance", 80, 0, 100);
        GOBBER2_ORE_NETHER_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Nether Ore pocket generation, [0-255, default: 5]").defineInRange("gobberOreNetherMinHeight", 20, 0, 255);
        GOBBER2_ORE_NETHER_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Nether Ore pocket generation [0-255, default: 50]").defineInRange("gobberOreNetherMaxHeight", 255, 0, 255);

        SERVER_BUILDER.pop();
        
        
        SERVER_BUILDER.comment("Gobber End Ore Generation").push("gobber2_ore_end");

        GOBBER2_ORE_END_GENERATION = SERVER_BUILDER.comment("Generate Gobber End Ore in the world [true / false]").define("gobberOreEndGeneration", true);
        GOBBER2_ORE_END_SIZE = SERVER_BUILDER.comment("Size of Gobber End Ore pockets [0-100, default: 5]").defineInRange("gobberOreEndSize", 5, 0, 100);
        GOBBER2_ORE_END_CHANCE = SERVER_BUILDER.comment("Chances of Gobber End Ore pocket being generated [0-100, default: 10]").defineInRange("gobberOreEndChance", 80, 0, 100);
        GOBBER2_ORE_END_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber End Ore pocket generation, [0-255, default: 5]").defineInRange("gobberOreEndMinHeight", 0, 0, 255);
        GOBBER2_ORE_END_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber End Ore pocket generation [0-255, default: 50]").defineInRange("gobberOreEndMaxHeight", 255, 0, 255);

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
        
        SERVER_BUILDER.comment("Misc Ring Settings").push("misc_ring_settings");
        
        RING_EXPLORER_MIN_RANGE = SERVER_BUILDER.comment("Ring of Explorer - Min distance from world spawn to begin searching for a spot [default: 500]").defineInRange("ringExplorerMin", 500, 0, 100000);
        RING_EXPLORER_MAX_RANGE = SERVER_BUILDER.comment("Ring of Explorer - Max distance from world spawn to begin searching for a spot [default: 6000]").defineInRange("ringExplorerMax", 6000, 0, 250000);
        REVERSE_RING_MINER = SERVER_BUILDER.comment("Reverse the drop/no drops feature on Ring of Miner [true / false]").define("reverseRingMiner", false);
        DELAY_BREAK_MODE = SERVER_BUILDER.comment("Uses a delayed break mode to reduce stutter and potential lag on Ring of Miner and Ring of Lumberjack [true / false]").define("delayedBreakMode", true);
        RING_ACCELERATION_VELOCITY = SERVER_BUILDER.comment("Ring of Acceleration - amount of velocity applied [default: .18]").defineInRange("ringAccelerationVelocity", .18, 0.0, .30);
        RING_REPAIR_DELAY = SERVER_BUILDER.comment("Ring of Repair - Delay time between repair ticks [default: 120]").defineInRange("ringRepairDelay", 120, 20, 600);
        
        SERVER_BUILDER.pop();
        
        SERVER_BUILDER.comment("Settings for the Maturator").push("maturator_settings");
        
        MATURATOR_MIN_TICK = SERVER_BUILDER.comment("Min interval of world ticks for the Maturator [default: 120]").defineInRange("maturatorMinTick", 120, 0, 240);
        MATURATOR_MAX_TICK = SERVER_BUILDER.comment("Max interval of world ticks for the Maturator [default: 240]").defineInRange("maturatorMaxTick", 240, 0, 480);
        ENABLE_MATURATOR_ANIMAL_EFFECT = SERVER_BUILDER.comment("Enable Maturator effect on baby animals [true / false]").define("enableMaturatorAnimalEffect", false);

        SERVER_BUILDER.pop();
        
        SERVER_BUILDER.comment("Dragon Egg With Every Kill").push("dragon_egg_kill");
        
        ENABLE_DRAGON_KILL_EVERY_KILL = SERVER_BUILDER.comment("Enable Dragon Egg drops on every kill [true / false]").define("enableDragonEggEveryKill", false);

        SERVER_BUILDER.pop();
    }
}