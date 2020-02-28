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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.SpellcastingIllagerEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.WaterMobEntity;
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

public class BlockDefender extends Block
{

	public BlockDefender(Properties properties)
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
			player.sendMessage(new TranslationTextComponent("item.gobber2.block_defender.line1").applyTextStyle(TextFormatting.GREEN));
    	}
		
		return ActionResultType.SUCCESS;
	}
  
	@Override
	public void tick(BlockState state,ServerWorld world, BlockPos pos, Random random)
	{		
		if(!world.isRemote)
		{
			int radius = 64;
		
			world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world) + random.nextInt(10));
			
			BlockPos posUp = pos.up();		
			BlockState flaming = ((FireBlock)Blocks.FIRE).getStateForPlacement(world, posUp);
			world.setBlockState(posUp, flaming, 11);
			
			List<Entity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius), e -> (e instanceof LivingEntity));
			for(Entity entity : entities)
			{
				if(entity instanceof PlayerEntity)
				{
					PlayerEntity player = (PlayerEntity)entity;
				   
					int newfoodlevel = 1;
					float newsatlevel = 0.045F;
					SpecialAbilities.giveRegenffect(world, player, null, newfoodlevel, newsatlevel);   
				}
			   
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
		   
				if(entity instanceof MobEntity)
				{
					((MobEntity) entity).spawnExplosionParticle();
					entity.remove(true);
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
		tooltip.add((new TranslationTextComponent("item.gobber2.block_defender.line2").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.block_defender.line3").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.block_defender.line4").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	}
}

