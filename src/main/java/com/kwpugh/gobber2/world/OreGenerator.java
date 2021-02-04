package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.world.feature.CustomOreFeature;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OreGenerator
{	
	public static ConfiguredFeature<?,?> GOBBER2_LUCKY_BLOCK;	
	public static ConfiguredFeature<?,?> GOBBER2_ORE;
	public static ConfiguredFeature<?,?> GOBBER2_ORE_NETHER;
	public static ConfiguredFeature<?,?> GOBBER2_ORE_END;


	public static void ores() 
	{ 		
		GOBBER2_LUCKY_BLOCK = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.GOBBER2_LUCKY_BLOCK.get().getDefaultState(), GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_SIZE.get())).range(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_MAX_HEIGHT.get()).square().func_242731_b(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_CHANCE.get());		
		GOBBER2_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.GOBBER2_ORE.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_SIZE.get())).range(GobberConfigBuilder.GOBBER2_ORE_MAX_HEIGHT.get()).square().func_242731_b(GobberConfigBuilder.GOBBER2_ORE_CHANCE.get());
		GOBBER2_ORE_NETHER = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER, BlockInit.GOBBER2_ORE_NETHER.get().getDefaultState(), GobberConfigBuilder.GOBBER2_ORE_NETHER_SIZE.get())).range(GobberConfigBuilder.GOBBER2_ORE_NETHER_MAX_HEIGHT.get()).square().func_242731_b(GobberConfigBuilder.GOBBER2_ORE_NETHER_CHANCE.get());
		GOBBER2_ORE_END = Feature.ORE.withConfiguration(new OreFeatureConfig(CustomOreFeature.FillerBlockType.end_stone, BlockInit.GOBBER2_ORE_END.get().getDefaultState(), 4)).range(90).square().func_242731_b(20);
		
		Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_lucky_block"), GOBBER2_LUCKY_BLOCK);		
		Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore"), GOBBER2_ORE);
		Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_nether"), GOBBER2_ORE_NETHER);
		Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_end"), GOBBER2_ORE_END);
	}
	
	@SubscribeEvent
	public static void biomeModification(final BiomeLoadingEvent event) 
	{ 
		ores();		
		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> GOBBER2_LUCKY_BLOCK);
		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> GOBBER2_ORE);
		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> GOBBER2_ORE_NETHER);
		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> GOBBER2_ORE_END);
	}
}