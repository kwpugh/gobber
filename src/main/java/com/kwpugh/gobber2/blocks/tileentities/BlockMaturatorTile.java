package com.kwpugh.gobber2.blocks.tileentities;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.TileInit;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CoralBlock;
import net.minecraft.block.CoralPlantBlock;
import net.minecraft.block.CropsBlock;
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
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class BlockMaturatorTile extends TileEntity implements ITickableTileEntity 
{
	int radius = GobberConfigBuilder.MATURATOR_RADIUS.get();
	int rediusVertical = GobberConfigBuilder.MATURATOR_VERTICAL_RANGE.get();
	int minTickInterval = GobberConfigBuilder.MATURATOR_MIN_TICK.get();
	
    public BlockMaturatorTile()
	{
		super(TileInit.BLOCK_MATURATOR.get());
	}
   
	@Override
    public void tick() 
    {
		if (world != null && !world.isRemote)
		{
			if (world.getGameTime() % minTickInterval == 0)
			{
				for (BlockPos targetPos : BlockPos.getAllInBoxMutable(pos.add(-radius, -2, -radius), pos.add(radius, rediusVertical, radius)))
				{
					BlockState state1 = world.getBlockState(targetPos);
					
			        if ((state1.getBlock() instanceof CropsBlock) ||
	                		(state1.getBlock() instanceof SaplingBlock) ||
	                		(state1.getBlock() instanceof VineBlock) ||                     		               
	                		(state1.getBlock() instanceof SugarCaneBlock) ||
	                		(state1.getBlock() instanceof SweetBerryBushBlock) ||
	                		(state1.getBlock() instanceof NetherWartBlock) ||
	                		(state1.getBlock() instanceof CactusBlock) ||
	                		(state1.getBlock() instanceof MelonBlock) ||
	                		(state1.getBlock() instanceof StemBlock) ||
	                		(state1.getBlock() instanceof PumpkinBlock) ||
	                		(state1.getBlock() instanceof CoralBlock) ||		
	                		(state1.getBlock() instanceof BambooSaplingBlock) || 
	                		(state1.getBlock() instanceof BambooBlock)  ||
	                		(state1.getBlock() instanceof CocoaBlock) || 
	                		(state1.getBlock() instanceof StemGrownBlock) ||
	                		(state1.getBlock() instanceof CoralPlantBlock) ||
	                		(state1.getBlock() instanceof CoralBlock) ||
	                		(state1.getBlock() instanceof TallSeaGrassBlock) ||
	                		(state1.getBlock() instanceof SeaGrassBlock) ||
	                		(state1.getBlock() instanceof SeaPickleBlock) ||
	                		(state1.getBlock() instanceof ChorusFlowerBlock)  ) 
	                {  
			        	state1.randomTick((ServerWorld) world, targetPos, world.getServer().func_241755_D_().getRandom());
	                }
				}	    		
			}	
		}				
    }
}