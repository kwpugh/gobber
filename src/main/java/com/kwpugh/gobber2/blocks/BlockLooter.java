package com.kwpugh.gobber2.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.lists.ItemList;
import com.kwpugh.gobber2.util.SpecialAbilities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.SpellcastingIllagerEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class BlockLooter extends Block
{

	public BlockLooter(Properties properties)
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
		  player.sendMessage(new StringTextComponent("The Looter is active in a range of 12 blocks"));
		  return ActionResultType.SUCCESS;
	  }
    
	  @Override
	  public BlockRenderType getRenderType(BlockState state)
	  {
		  return BlockRenderType.MODEL;
	  }
	    
	  @Override
	  public void func_225534_a_(BlockState state,ServerWorld world, BlockPos pos,  Random random)
	  {
		   if (!world.isRemote)
		   {
			   int radius = 18;  

			   List<Entity> entities = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius));
			   for(Entity entity : entities)
			   {
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
							   }
						   }
					   }
				   }
			   }
		   }
	
		   if(!world.isRemote)
		   {			   
			   int radius = 18;
			
			   List<Entity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius), e -> (e instanceof LivingEntity || e instanceof IProjectile));
			   for(Entity entity : entities)
			   {
				// These types of mobs are excluded 
					if(entity instanceof PlayerEntity ||
							entity instanceof ArmorStandEntity ||
							entity instanceof VillagerEntity || 
							entity instanceof WanderingTraderEntity ||
							entity instanceof AnimalEntity || 
							entity instanceof IronGolemEntity || 
							entity instanceof DolphinEntity ||
							entity instanceof WaterMobEntity ||
							entity instanceof GuardianEntity ||
							entity instanceof ElderGuardianEntity ||
							entity instanceof SpellcastingIllagerEntity ||
							entity instanceof VexEntity ||
							entity instanceof VindicatorEntity ||
							entity instanceof GhastEntity ||
							entity instanceof BlazeEntity ||
							entity instanceof WitherSkeletonEntity ||
							entity instanceof WitherEntity ||
							entity instanceof EnderDragonEntity)
					{
						continue;
					}
				
					double distanceSqA = entity.getDistanceSq((double) pos.getX(), (double) pos.getY(), (double) pos.getZ());
					double distance = Math.sqrt(distanceSqA);
					
					Item drop = null;
					
//					if(distance < radius && distance != 0)
//					{
						if(entity instanceof ZombiePigmanEntity)
						{
							drop = Items.GOLD_NUGGET;
						}
						else if(entity instanceof SkeletonEntity)
						{
							drop = Items.BONE; 
						}
						else if(entity instanceof CreeperEntity)
						{
							drop = Items.GUNPOWDER; 
						}
						else if(entity instanceof HuskEntity)
						{
							drop = Items.WHEAT;
						}
						else if(entity instanceof SlimeEntity)
						{
							drop = Items.SLIME_BALL;
						}
						else if(entity instanceof DrownedEntity)
						{
							drop = Items.COD;
						}
						else if(entity instanceof PhantomEntity)
						{
							drop = Items.PHANTOM_MEMBRANE;
						}
						else if(entity instanceof SpiderEntity || entity instanceof CaveSpiderEntity)
						{
							drop = Items.STRING;
						}
						((MobEntity) entity).spawnExplosionParticle();
						((LivingEntity) entity).setHealth(0);
						world.addEntity(new ItemEntity(world, pos.getX()+5, pos.getY(), pos.getZ(), new ItemStack(drop))); 						
//					}
			   }
		   }
	   }
    
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag)
	{
		super.addInformation(stack, world, tooltip, flag);				
		tooltip.add(new StringTextComponent(TextFormatting.BLUE + "The Looter "));
		tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Range: 12 blocks"));
	}
}

