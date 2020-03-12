package com.kwpugh.gobber2.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.GeneralModConfig;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CoralBlock;
import net.minecraft.block.CoralPlantBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FireBlock;
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
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockMaturator extends Block
{
	public BlockMaturator(Properties properties)
	{
		super(properties);
	}
	
	int minTickTime = GeneralModConfig.MATURATOR_MIN_TICK.get();
	int maxTickTime = GeneralModConfig.MATURATOR_MAX_TICK.get();
	
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), world.rand.nextInt(maxTickTime - minTickTime + 1));
	}
	
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
    	worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), worldIn.rand.nextInt(maxTickTime - minTickTime + 1));
    	if(worldIn.isRemote)
    	{
    		player.sendMessage(new TranslationTextComponent("item.gobber2.block_maturator.line1").applyTextStyle(TextFormatting.GREEN));
    	}
    	
        return ActionResultType.SUCCESS;
    }
    
    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
    {
    	boolean enableMaturatorAnimalEffect = GeneralModConfig.ENABLE_MATURATOR_ANIMAL_EFFECT.get();
    	
    	if(!world.isRemote)
		{
    		world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world) + rand.nextInt(minTickTime));
    		
			BlockPos posUp = pos.up();		
			BlockState flaming = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, posUp);
			world.setBlockState(posUp, flaming, 11);
			
    		for (BlockPos targetPos : BlockPos.getAllInBoxMutable(pos.add(-16, -2, -16), pos.add(16, 8, 16)))
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
		        	state1.tick((ServerWorld) world, targetPos, world.rand); 	                                                             
                }
			}
    		
    		if(enableMaturatorAnimalEffect)
    		{
        		int radius = 16;
        		
        		List<Entity> entities = world.getEntitiesWithinAABB(AnimalEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius), e -> (e instanceof LivingEntity));
    			for(Entity entity : entities)
    			{
    				((AgeableEntity) entity).ageUp(120,true);;
    				
    			}    			
    		}
		}
    }	
	
	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.block_maturator.line2").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.block_maturator.line3").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	}
}
