package com.kwpugh.gobber2.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.SpecialAbilities;

import net.minecraft.block.Block;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockHealer extends Block
{
	public BlockHealer(Properties properties)
	{
		super(properties.func_226896_b_());
	}

	int minTickTime = 5;
	int maxTickTime = 20;
	
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), world.rand.nextInt(maxTickTime - minTickTime + 1));
	}
  
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
	{
	   BlockState stateIn = worldIn.getBlockState(pos);
	   worldIn.getPendingBlockTicks().scheduleTick(pos, stateIn.getBlock(), worldIn.rand.nextInt(maxTickTime - minTickTime + 1));   
	}
	
    @Override
    public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
    	worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), worldIn.rand.nextInt(maxTickTime - minTickTime + 1));
    	player.sendMessage(new StringTextComponent("The Healer is active for players in a range of 64 blocks"));
        return ActionResultType.SUCCESS;
    }
    
	@Override
	public void func_225534_a_(BlockState state,ServerWorld world, BlockPos pos,  Random random)
	{
		if (!world.isRemote)
		{
			int radius = 64;  
      
			List<Entity> entities = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius));
			for(Entity entity : entities)
			{
				PlayerEntity player = (PlayerEntity)entity;
				if(entity instanceof PlayerEntity)
				{
					double distanceSqA = entity.getDistanceSq((double) pos.getX(), (double) pos.getY(), (double) pos.getZ());
					double distance = Math.sqrt(distanceSqA);
					
					if(distance < radius && distance != 0)
					{
						if(distance < 1D) distance = 1D;
						{
							if(entity != null)
							{
								world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), random.nextInt(minTickTime));
								
								BlockPos posUp = pos.up();		
							    BlockState flaming = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, posUp);
							    world.setBlockState(posUp, flaming, 11);

								int newfoodlevel = 1;
								float newsatlevel = 0.035F;
								SpecialAbilities.giveRegenffect(world, player, null, newfoodlevel, newsatlevel);
							}
						}
					}
				}
			}	
		}
    }
  
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag)
	{
		super.addInformation(stack, world, tooltip, flag);				
		tooltip.add(new StringTextComponent(TextFormatting.BLUE + "The Healer provides a slow, but steady health regen"));
		tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Range: 64 blocks"));
	}
}
