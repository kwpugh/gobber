package com.kwpugh.gobber2.world;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.world.feature.CustomOreFeature;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gobber2.modid, bus = Mod.EventBusSubscriber.Bus.MOD)


//public static final ConfiguredFeature<?, ?> field_243897_bq = func_243968_a("ore_coal", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, Features.States.field_244045_ai, 17)).func_242733_d(128).func_242728_a().func_242731_b(20));
//public static final ConfiguredFeature<?, ?> field_243898_br = func_243968_a("ore_iron", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, Features.States.field_244046_aj, 9)).func_242733_d(64).func_242728_a().func_242731_b(20));



public class OreGenerator
{
	public static ConfiguredFeature<?, ?> GOBBER2_LUCKY_BLOCK;
	public static ConfiguredFeature<?, ?> GOBBER2_ORE;
	public static ConfiguredFeature<?, ?> GOBBER2_ORE_NETHER;
	public static ConfiguredFeature<?, ?> GOBBER2_ORE_END;
	    
	@SuppressWarnings("deprecation")
	public static void addFeatures()
	{
		OreGenerator.GOBBER2_LUCKY_BLOCK = configuredFeature("gobber_lucky_block", Feature.ORE.withConfiguration(new OreFeatureConfig
				(OreFeatureConfig.FillerBlockType.field_241882_a, BlockInit.GOBBER2_LUCKY_BLOCK.get().getDefaultState(), GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_SIZE.get())).func_242733_d(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_MAX_HEIGHT.get()).func_242728_a().func_242731_b(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_CHANCE.get()));
		
		OreGenerator.GOBBER2_ORE = configuredFeature("ore_gobber", Feature.ORE.withConfiguration(new OreFeatureConfig
				(OreFeatureConfig.FillerBlockType.field_241882_a, BlockInit.GOBBER2_ORE.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_SIZE.get())).func_242733_d(GobberConfigBuilder.GOBBER2_ORE_MAX_HEIGHT.get()).func_242728_a().func_242731_b(GobberConfigBuilder.GOBBER2_ORE_CHANCE.get()));
		
		OreGenerator.GOBBER2_ORE_NETHER = configuredFeature("ore_gobber_nether", Feature.ORE.withConfiguration(new OreFeatureConfig
				(OreFeatureConfig.FillerBlockType.field_241883_b, BlockInit.GOBBER2_ORE_NETHER.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_NETHER_SIZE.get())).func_242733_d(GobberConfigBuilder.GOBBER2_ORE_NETHER_MAX_HEIGHT.get()).func_242728_a().func_242731_b(GobberConfigBuilder.GOBBER2_ORE_NETHER_CHANCE.get()));
		
		OreGenerator.GOBBER2_ORE_END = configuredFeature("ore_gobber_end", Feature.ORE.withConfiguration(new OreFeatureConfig
				(CustomOreFeature.FillerBlockType.end_stone, BlockInit.GOBBER2_ORE_END.get().getDefaultState(), 4)).func_242733_d(90).func_242728_a().func_242731_b(20));
		
        for (final Biome biome : WorldGenRegistries.field_243657_i) 
        {
            if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NONE) 
        	{
            	if(GobberConfigBuilder.GOBBER2_ORE_GENERATION.get())
            	{
            		BiomeUtil.addFeature(biome, GenerationStage.Decoration.UNDERGROUND_ORES, OreGenerator.GOBBER2_ORE); 
            	}
            	
            	if(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_GENERATION.get())
            	{
            		BiomeUtil.addFeature(biome, GenerationStage.Decoration.UNDERGROUND_ORES, OreGenerator.GOBBER2_LUCKY_BLOCK);
            	}
        	}
            
            if (biome.getCategory() == Biome.Category.NETHER) 
        	{
            	if(GobberConfigBuilder.GOBBER2_ORE_NETHER_GENERATION.get())
            	{
            		BiomeUtil.addFeature(biome, GenerationStage.Decoration.UNDERGROUND_ORES, OreGenerator.GOBBER2_ORE_NETHER);
            	}
        	}
            
            if (biome.getCategory() == Biome.Category.THEEND) 
        	{
            	if(GobberConfigBuilder.GOBBER2_ORE_END_GENERATION.get())
            	{
            		BiomeUtil.addFeature(biome, GenerationStage.Decoration.UNDERGROUND_ORES, OreGenerator.GOBBER2_ORE_END); 
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