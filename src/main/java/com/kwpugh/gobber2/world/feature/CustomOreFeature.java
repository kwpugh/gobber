package com.kwpugh.gobber2.world.feature;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class CustomOreFeature extends OreFeature
{

	public CustomOreFeature(Function<Dynamic<?>, ? extends OreFeatureConfig> p_i51472_1_) {
		super(p_i51472_1_);
	}

	@Override
   public boolean func_207803_a(IWorld worldIn, Random random, OreFeatureConfig config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
      int i = 0;
      BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
      
      //Old code from 1.14.4
      //BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
      
      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
      double[] adouble = new double[config.size * 4];

      for(int j = 0; j < config.size; ++j) {
         float f = (float)j / (float)config.size;
         double d0 = MathHelper.lerp((double)f, p_207803_4_, p_207803_6_);
         double d2 = MathHelper.lerp((double)f, p_207803_12_, p_207803_14_);
         double d4 = MathHelper.lerp((double)f, p_207803_8_, p_207803_10_);
         double d6 = random.nextDouble() * (double)config.size / 16.0D;
         double d7 = ((double)(MathHelper.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
         adouble[j * 4 + 0] = d0;
         adouble[j * 4 + 1] = d2;
         adouble[j * 4 + 2] = d4;
         adouble[j * 4 + 3] = d7;
      }

      for(int l2 = 0; l2 < config.size - 1; ++l2) {
         if (!(adouble[l2 * 4 + 3] <= 0.0D)) {
            for(int j3 = l2 + 1; j3 < config.size; ++j3) {
               if (!(adouble[j3 * 4 + 3] <= 0.0D)) {
                  double d12 = adouble[l2 * 4 + 0] - adouble[j3 * 4 + 0];
                  double d13 = adouble[l2 * 4 + 1] - adouble[j3 * 4 + 1];
                  double d14 = adouble[l2 * 4 + 2] - adouble[j3 * 4 + 2];
                  double d15 = adouble[l2 * 4 + 3] - adouble[j3 * 4 + 3];
                  if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
                     if (d15 > 0.0D) {
                        adouble[j3 * 4 + 3] = -1.0D;
                     } else {
                        adouble[l2 * 4 + 3] = -1.0D;
                     }
                  }
               }
            }
         }
      }

      for(int i3 = 0; i3 < config.size; ++i3) {
         double d11 = adouble[i3 * 4 + 3];
         if (!(d11 < 0.0D)) {
            double d1 = adouble[i3 * 4 + 0];
            double d3 = adouble[i3 * 4 + 1];
            double d5 = adouble[i3 * 4 + 2];
            int k = Math.max(MathHelper.floor(d1 - d11), p_207803_16_);
            int k3 = Math.max(MathHelper.floor(d3 - d11), p_207803_17_);
            int l = Math.max(MathHelper.floor(d5 - d11), p_207803_18_);
            int i1 = Math.max(MathHelper.floor(d1 + d11), k);
            int j1 = Math.max(MathHelper.floor(d3 + d11), k3);
            int k1 = Math.max(MathHelper.floor(d5 + d11), l);

            for(int l1 = k; l1 <= i1; ++l1) {
               double d8 = ((double)l1 + 0.5D - d1) / d11;
               if (d8 * d8 < 1.0D) {
                  for(int i2 = k3; i2 <= j1; ++i2) {
                     double d9 = ((double)i2 + 0.5D - d3) / d11;
                     if (d8 * d8 + d9 * d9 < 1.0D) {
                        for(int j2 = l; j2 <= k1; ++j2) {
                           double d10 = ((double)j2 + 0.5D - d5) / d11;
                           if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
                              int k2 = l1 - p_207803_16_ + (i2 - p_207803_17_) * p_207803_19_ + (j2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
                              if (!bitset.get(k2)) {
                                 bitset.set(k2);
                                 blockpos$mutable.setPos(l1, i2, j2);
                                 
                                 if (worldIn.getBlockState(blockpos$mutable).getBlock() == Blocks.END_STONE)
                                 {
                                    worldIn.setBlockState(blockpos$mutable, config.state, 2);
                                    ++i;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      return i > 0;
   }

}
