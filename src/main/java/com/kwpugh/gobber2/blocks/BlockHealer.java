package com.kwpugh.gobber2.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.SpecialAbilities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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

public class BlockHealer extends Block
{
	public BlockHealer(Properties properties)
	{
		super(properties);
	}

	int minTickTime = 5;
	int maxTickTime = 20;
	
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
    		player.sendMessage(new TranslationTextComponent("item.gobber2.block_healer.line1").applyTextStyle(TextFormatting.GREEN));
    	}
    	
        return ActionResultType.SUCCESS;
    }
    
	@Override
	public void tick(BlockState state,ServerWorld world, BlockPos pos,  Random random)
	{
		if (!world.isRemote)
		{
			int radius = 16;  
      
			world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world) + random.nextInt(10));
			
			BlockPos posUp = pos.up();		
			BlockState flaming = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, posUp);
			world.setBlockState(posUp, flaming, 11);
			
			List<Entity> entities = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius));
			for(Entity entity : entities)
			{
				PlayerEntity player = (PlayerEntity)entity;
				
				if(entity instanceof PlayerEntity)
				{
					//world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), random.nextInt(minTickTime));
//					world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world) + random.nextInt(10));
//					
//					BlockPos posUp = pos.up();		
//					BlockState flaming = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, posUp);
//					world.setBlockState(posUp, flaming, 11);
					
					int newfoodlevel = 1;
					float newsatlevel = 0.025F;
					SpecialAbilities.giveRegenffect(world, player, null, newfoodlevel, newsatlevel);
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
		tooltip.add((new TranslationTextComponent("item.gobber2.block_healer.line2").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.block_healer.line3").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	}
}
