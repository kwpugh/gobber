package com.kwpugh.gobber2.world;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class BiomeUtil
{
	public static boolean doesBiomeMatch(Biome biomeIn, RegistryKey<Biome> wantedBiomeIn)
	{
		return biomeIn.toString().matches(wantedBiomeIn.func_240901_a_().toString());
	}

	public static <C extends IFeatureConfig, PC extends IPlacementConfig> void addFeature(Biome biome, Decoration stage, ConfiguredFeature<?, ?> feature)
	{
		if (getGenSettings(biome).field_242484_f instanceof ImmutableList || (getGenSettings(biome).field_242484_f.size() > stage.ordinal() && getGenSettings(biome).field_242484_f.get(stage.ordinal()) instanceof ImmutableList))
			getGenSettings(biome).field_242484_f = makeListMutable(getGenSettings(biome).field_242484_f, BiomeUtil::makeListMutable);

		while (getGenSettings(biome).field_242484_f.size() <= stage.ordinal())
			getGenSettings(biome).field_242484_f.add(Lists.newArrayList());

		getGenSettings(biome).field_242484_f.get(stage.ordinal()).add(() -> feature);
	}

	public static BiomeGenerationSettings getGenSettings(Biome biome)
	{
		return biome.func_242440_e();
	}

	public static <T> List<T> makeListMutable(List<T> list)
	{
		return list.stream().collect(Collectors.toList());
	}

	public static <T> List<T> makeListMutable(List<T> list, Function<T, T> listFunction)
	{
		return list.stream().map(listFunction).collect(Collectors.toList());
	}
}
