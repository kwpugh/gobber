package com.kwpugh.gobber2.world;

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
    }
}