package com.kwpugh.gobber2.world;


import com.google.common.collect.ImmutableList;
import java.util.stream.Collectors;
import java.util.function.Supplier;
import java.util.List;

import com.google.common.collect.Lists;
import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.world.feature.CustomOreFeature;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.Biome;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gobber2.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreGenerator
{
	public static ConfiguredFeature<?, ?> GOBBER2_ORE;
	public static ConfiguredFeature<?, ?> GOBBER2_ORE_NETHER;
	public static ConfiguredFeature<?, ?> GOBBER2_ORE_END;
	   
	@SuppressWarnings("deprecation")
	public static void addFeatures()
	{
		OreGenerator.GOBBER2_ORE = configuredFeature("ore_gobber", Feature.ORE.withConfiguration(new OreFeatureConfig
				(OreFeatureConfig.FillerBlockType.field_241882_a, BlockInit.GOBBER2_ORE.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_SIZE.get())).func_242733_d(GobberConfigBuilder.GOBBER2_ORE_MAX_HEIGHT.get()).func_242728_a().func_242731_b(GobberConfigBuilder.GOBBER2_ORE_CHANCE.get()));
		
		OreGenerator.GOBBER2_ORE_NETHER = configuredFeature("ore_gobber_nether", Feature.ORE.withConfiguration(new OreFeatureConfig
				(OreFeatureConfig.FillerBlockType.field_241883_b, BlockInit.GOBBER2_ORE_NETHER.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_NETHER_SIZE.get())).func_242733_d(GobberConfigBuilder.GOBBER2_ORE_NETHER_MAX_HEIGHT.get()).func_242728_a().func_242731_b(GobberConfigBuilder.GOBBER2_ORE_NETHER_CHANCE.get()));
		
		OreGenerator.GOBBER2_ORE_END = configuredFeature("ore_gobber_end", Feature.ORE.withConfiguration(new OreFeatureConfig
				(CustomOreFeature.FillerBlockType.end_stone, BlockInit.GOBBER2_ORE_END.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_SIZE.get())).func_242733_d(GobberConfigBuilder.GOBBER2_ORE_MAX_HEIGHT.get()).func_242728_a().func_242731_b(GobberConfigBuilder.GOBBER2_ORE_CHANCE.get()));
		
        for (final Biome biome : WorldGenRegistries.field_243657_i) 
        {
            if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NONE) 
        	{
            	if(GobberConfigBuilder.GOBBER2_ORE_GENERATION.get())
            	{
            		addFeatureToBiome(biome, GenerationStage.Decoration.UNDERGROUND_ORES, OreGenerator.GOBBER2_ORE); 
            	}
        	}
            
            if (biome.getCategory() == Biome.Category.NETHER) 
        	{
            	if(GobberConfigBuilder.GOBBER2_ORE_NETHER_GENERATION.get())
            	{
            		addFeatureToBiome(biome, GenerationStage.Decoration.UNDERGROUND_ORES, OreGenerator.GOBBER2_ORE_NETHER); 
            	}
        	}
            
            if (biome.getCategory() == Biome.Category.THEEND) 
        	{
            	if(GobberConfigBuilder.GOBBER2_ORE_END_GENERATION.get())
            	{
            		addFeatureToBiome(biome, GenerationStage.Decoration.UNDERGROUND_ORES, OreGenerator.GOBBER2_ORE_END); 
            	}
        	}
        }
    }
	   
	public static ConfiguredFeature<?, ?> configuredFeature(final String registryName, final ConfiguredFeature<?, ?> configuredFeature) 
	{
        Registry.register(WorldGenRegistries.field_243653_e, new ResourceLocation("gobber2", registryName), configuredFeature);
        return configuredFeature;
    }
    
	public static void addFeatureToBiome(Biome biome, GenerationStage.Decoration feature, ConfiguredFeature<?, ?> configuredFeature) 
	{
        ConvertImmutableFeatures(biome);
        List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = biome.func_242440_e().field_242484_f;
        while (biomeFeatures.size() <= feature.ordinal()) 
        {
            biomeFeatures.add(Lists.newArrayList());
        }
        biomeFeatures.get(feature.ordinal()).add(() -> configuredFeature);
    }
    
    private static void ConvertImmutableFeatures(final Biome biome)
    {
        if (biome.func_242440_e().field_242484_f instanceof ImmutableList)
        {
            biome.func_242440_e().field_242484_f = biome.func_242440_e().field_242484_f.stream().map(Lists::newArrayList).collect(Collectors.toList());
        }
    } 
}











//import com.kwpugh.gobber2.config.GobberConfigBuilder;
//import com.kwpugh.gobber2.init.BlockInit;
//
//import net.minecraft.block.Blocks;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.Biomes;
//import net.minecraft.world.gen.GenerationStage;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.OreFeatureConfig;
//import net.minecraft.world.gen.feature.ReplaceBlockConfig;
//import net.minecraft.world.gen.placement.CountRangeConfig;
//import net.minecraft.world.gen.placement.Placement;
//import net.minecraftforge.registries.ForgeRegistries;
//
//
//public class OreGenerator
//{
//    public static void setupOregen()
//    {
//        for(Biome biome : ForgeRegistries.BIOMES)
//        { 
//        	if(GobberConfigBuilder.GOBBER2_ORE_GENERATION.get())
//        		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
//        			new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.GOBBER2_ORE.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_SIZE.get())).withPlacement(Placement.COUNT_RANGE.configure(
//        			new CountRangeConfig(GobberConfigBuilder.GOBBER2_ORE_CHANCE.get(), GobberConfigBuilder.GOBBER2_ORE_MIN_HEIGHT.get(), GobberConfigBuilder.GOBBER2_ORE_MIN_HEIGHT.get(), GobberConfigBuilder.GOBBER2_ORE_MAX_HEIGHT.get()))));
//	                                  
//        	if(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_GENERATION.get())
//        		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
//        			new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.GOBBER2_LUCKY_BLOCK.get().getDefaultState(), GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_SIZE.get())).withPlacement(Placement.COUNT_RANGE.configure(
//        			new CountRangeConfig(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_CHANCE.get(), GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_MIN_HEIGHT.get(), GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_MIN_HEIGHT.get(), GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_MAX_HEIGHT.get())))); 
//        }
//    }
//     
//    public static void setupNetherOregen()
//    {
//    	if(GobberConfigBuilder.GOBBER2_ORE_NETHER_GENERATION.get())
//    		Biomes.NETHER.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
//    			new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockInit.GOBBER2_ORE_NETHER.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_NETHER_SIZE.get())).withPlacement(Placement.COUNT_RANGE.configure(
//    			new CountRangeConfig(GobberConfigBuilder.GOBBER2_ORE_NETHER_CHANCE.get(), GobberConfigBuilder.GOBBER2_ORE_NETHER_MIN_HEIGHT.get(), GobberConfigBuilder.GOBBER2_ORE_NETHER_MIN_HEIGHT.get(), GobberConfigBuilder.GOBBER2_ORE_NETHER_MAX_HEIGHT.get()))));  
//
//    
//	    for(Biome biome : ForgeRegistries.BIOMES)
//	    {
//	    	if (GobberConfigBuilder.GOBBER2_ORE_NETHER_GENERATION.get())
//	            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(
//	            	new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockInit.GOBBER2_ORE_NETHER.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_NETHER_SIZE.get())).withPlacement(Placement.COUNT_RANGE.configure(
//	            	new CountRangeConfig(GobberConfigBuilder.GOBBER2_ORE_NETHER_CHANCE.get(), GobberConfigBuilder.GOBBER2_ORE_NETHER_MIN_HEIGHT.get(), GobberConfigBuilder.GOBBER2_ORE_NETHER_MIN_HEIGHT.get(), GobberConfigBuilder.GOBBER2_ORE_NETHER_MAX_HEIGHT.get()))));  
//	    }
//    }
//   
//    public static void setupEndOregen()
//    { 	
//    	if(GobberConfigBuilder.GOBBER2_ORE_END_GENERATION.get())
//    	{	
//        	Biomes.THE_END.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(
//        			new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockInit.GOBBER2_ORE_END.get().getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(
//        			new CountRangeConfig(GobberConfigBuilder.GOBBER2_ORE_END_COUNT.get(), 0, 0, 70))));
//    	
//    		Biomes.END_BARRENS.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(
//    				new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockInit.GOBBER2_ORE_END.get().getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(
//    				new CountRangeConfig(GobberConfigBuilder.GOBBER2_ORE_END_COUNT.get(), 0, 0, 70))));
//    		
//    		Biomes.END_HIGHLANDS.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(
//    				new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockInit.GOBBER2_ORE_END.get().getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(
//    				new CountRangeConfig(GobberConfigBuilder.GOBBER2_ORE_END_COUNT.get(), 0, 0, 70))));
//    		
//    		Biomes.END_MIDLANDS.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(
//    				new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockInit.GOBBER2_ORE_END.get().getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(
//    				new CountRangeConfig(GobberConfigBuilder.GOBBER2_ORE_END_COUNT.get(), 0, 0, 70))));	
//    		
//    		Biomes.SMALL_END_ISLANDS.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(
//    				new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockInit.GOBBER2_ORE_END.get().getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(
//    				new CountRangeConfig(GobberConfigBuilder.GOBBER2_ORE_END_COUNT.get(), 0, 0, 70))));
//    	}
//    }
//}