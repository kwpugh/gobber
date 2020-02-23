package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.lists.BlockList;
import com.kwpugh.gobber2.util.GeneralModConfig;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;


public class OreGenerator
{
    public static void setupOregen()
    {
        for(Biome biome : ForgeRegistries.BIOMES)
        {               
        	if(GeneralModConfig.GOBBER2_ORE_GENERATION.get())
        		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.gobber2_ore.getDefaultState(), GeneralModConfig.GOBBER2_ORE_SIZE.get())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(GeneralModConfig.GOBBER2_ORE_CHANCE.get(), GeneralModConfig.GOBBER2_ORE_MIN_HEIGHT.get(), GeneralModConfig.GOBBER2_ORE_MIN_HEIGHT.get(), GeneralModConfig.GOBBER2_ORE_MAX_HEIGHT.get()))));
	                                  
        	if(GeneralModConfig.GOBBER2_LUCKY_BLOCK_GENERATION.get())
        		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.gobber2_lucky_block.getDefaultState(), GeneralModConfig.GOBBER2_LUCKY_BLOCK_SIZE.get())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(GeneralModConfig.GOBBER2_LUCKY_BLOCK_CHANCE.get(), GeneralModConfig.GOBBER2_LUCKY_BLOCK_MIN_HEIGHT.get(), GeneralModConfig.GOBBER2_LUCKY_BLOCK_MIN_HEIGHT.get(), GeneralModConfig.GOBBER2_LUCKY_BLOCK_MAX_HEIGHT.get())))); 
        }
    }
     
    public static void setupNetherOregen()
    {
    	if(GeneralModConfig.GOBBER2_ORE_NETHER_GENERATION.get())
    		Biomes.NETHER.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockList.gobber2_ore_nether.getDefaultState(), GeneralModConfig.GOBBER2_ORE_NETHER_SIZE.get())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(GeneralModConfig.GOBBER2_ORE_NETHER_CHANCE.get(), GeneralModConfig.GOBBER2_ORE_NETHER_MIN_HEIGHT.get(), GeneralModConfig.GOBBER2_ORE_NETHER_MIN_HEIGHT.get(), GeneralModConfig.GOBBER2_ORE_NETHER_MAX_HEIGHT.get()))));  

    
	    for(Biome biome : ForgeRegistries.BIOMES)
	    {
	    	if (GeneralModConfig.GOBBER2_ORE_NETHER_GENERATION.get())
	            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockList.gobber2_ore_nether.getDefaultState(), GeneralModConfig.GOBBER2_ORE_NETHER_SIZE.get())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(GeneralModConfig.GOBBER2_ORE_NETHER_CHANCE.get(), GeneralModConfig.GOBBER2_ORE_NETHER_MIN_HEIGHT.get(), GeneralModConfig.GOBBER2_ORE_NETHER_MIN_HEIGHT.get(), GeneralModConfig.GOBBER2_ORE_NETHER_MAX_HEIGHT.get()))));  
	    }
    }
    

    
  
    
    public static void setupEndOregen()
    {
    	if(GeneralModConfig.GOBBER2_ORE_END_GENERATION.get())
    		Biomes.END_BARRENS.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockList.gobber2_ore_end.getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(80, 0, 0, 255))));
	  
        if(GeneralModConfig.GOBBER2_ORE_END_GENERATION.get())
        	Biomes.END_HIGHLANDS.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockList.gobber2_ore_end.getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(80, 0, 0, 255))));

        if(GeneralModConfig.GOBBER2_ORE_END_GENERATION.get())
        	Biomes.END_MIDLANDS.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockList.gobber2_ore_end.getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(80, 0, 0, 255))));
        
        if(GeneralModConfig.GOBBER2_ORE_END_GENERATION.get())
        	Biomes.SMALL_END_ISLANDS.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockList.gobber2_ore_end.getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(80, 0, 0, 255))));
        
        if(GeneralModConfig.GOBBER2_ORE_END_GENERATION.get())
        	Biomes.THE_END.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockList.gobber2_ore_end.getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(80, 0, 0, 255))));       	
    }
}