package com.kwpugh.gobber2.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.SpellcastingIllagerEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class BlockLooter extends Block
{
	public BlockLooter(Properties properties)
	{
		super(properties);
	}

	int minTickTime = 5;
	int maxTickTime = 20;

	//Start it up when placed
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), world.rand.nextInt(maxTickTime - minTickTime + 1));
	}

	//Start it up if right-clicked on
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), worldIn.rand.nextInt(maxTickTime - minTickTime + 1));
		if(worldIn.isRemote)
    	{
			player.sendMessage(new TranslationTextComponent("item.gobber2.block_looter.line1").applyTextStyle(TextFormatting.GREEN));
    	}
		
		return ActionResultType.SUCCESS;
	}
       
	@Override
	public void tick(BlockState state,ServerWorld world, BlockPos pos,  Random random)
	{	
		if(!world.isRemote)
		{			   
			int radius = 32;

			world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world) + random.nextInt(10));
			
			BlockPos posUp = pos.up();		
			BlockState flaming = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, posUp);
			world.setBlockState(posUp, flaming, 11);
			
			//Scan the radius for LivingEntity and store in list
			List<Entity> mobs = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius), e -> (e instanceof LivingEntity));
			for(Entity mob : mobs)
			{				
				//If a player is within the list, kick start the block, does work if player had left the area (on purpose)
				if(mob instanceof PlayerEntity)
				{
					//world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), random.nextInt(minTickTime));
//					world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world) + random.nextInt(10));
//					
//					BlockPos posUp = pos.up();		
//					BlockState flaming = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, posUp);
//					world.setBlockState(posUp, flaming, 11);
				}
				
				if(mob instanceof ZombieEntity || mob instanceof ZombieVillagerEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.GOLD_INGOT);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof SpiderEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.STRING);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof SkeletonEntity || mob instanceof StrayEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.BONE);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof PillagerEntity || mob instanceof RavagerEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.IRON_INGOT);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof CreeperEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.GUNPOWDER);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
			
				if(mob instanceof SlimeEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.SLIME_BALL);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof WitchEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.EMERALD);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof EndermanEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.ENDER_PEARL);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof BlazeEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.BLAZE_ROD);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof WitherSkeletonEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.WITHER_SKELETON_SKULL);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof PhantomEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.PHANTOM_MEMBRANE);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof VindicatorEntity || mob instanceof SpellcastingIllagerEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					ItemStack drop = new ItemStack(Items.DIAMOND);				
					world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
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
		tooltip.add((new TranslationTextComponent("item.gobber2.block_looter.line2").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.block_looter.line3").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	}
}

