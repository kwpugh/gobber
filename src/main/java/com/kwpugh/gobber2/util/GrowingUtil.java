package com.kwpugh.gobber2.util;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CoralBlock;
import net.minecraft.block.CoralPlantBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.MelonBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SeaGrassBlock;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.block.StemGrownBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.block.TallSeaGrassBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GrowingUtil
{
	public static void growCrops(World world, PlayerEntity player, int baseTickDelay, int radius)
	{
		BlockPos playerPos = new BlockPos(player.getPositionVec());
		
		for (BlockPos targetPos : BlockPos.getAllInBoxMutable(playerPos.add(-radius, -2, -radius), playerPos.add(radius, 3, radius)))
		{
			BlockState blockstate = world.getBlockState(targetPos);
			Block block = blockstate.getBlock();

			if ((blockstate.getBlock() instanceof CropsBlock) ||
					(block instanceof SaplingBlock) ||
					(block instanceof VineBlock) ||                     		               
					(block instanceof SugarCaneBlock) ||
					(block instanceof SweetBerryBushBlock) ||
					(block instanceof NetherWartBlock) ||
					(block instanceof CactusBlock) ||
					(block instanceof MelonBlock) ||
					(block instanceof StemBlock) ||
					(block instanceof PumpkinBlock) ) 
			{
				if (player.ticksExisted % (baseTickDelay) == 0)
				{
					blockstate.tick((ServerWorld) world, targetPos, world.rand);
				}                                                               
			}

			if ((block instanceof CoralBlock) ||		
					(block instanceof BambooSaplingBlock) || 
					(block instanceof BambooBlock)  ||
					(block instanceof CocoaBlock) || 
					(block instanceof StemGrownBlock) ||
					(block instanceof CoralPlantBlock) ||
					(block instanceof CoralBlock) ||
					(block instanceof TallSeaGrassBlock) ||
					(block instanceof SeaGrassBlock) ||
					(block instanceof SeaPickleBlock) ||
					(block instanceof ChorusFlowerBlock) )
			{        				
				if (player.ticksExisted % (baseTickDelay * 2) == 0)
				{
					blockstate.tick((ServerWorld) world, targetPos, world.rand);
				}                     		
			}

			if(block instanceof GrassBlock && player.isShiftKeyDown())
			{
				if (player.ticksExisted % (baseTickDelay * 6 ) == 0)
				{
					((GrassBlock) block).grow((ServerWorld) world, world.rand, targetPos, blockstate);	
				}
			}
		}
	}
}
