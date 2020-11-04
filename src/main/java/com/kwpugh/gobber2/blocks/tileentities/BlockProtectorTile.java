package com.kwpugh.gobber2.blocks.tileentities;

import java.util.List;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.TileInit;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
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
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;


public class BlockProtectorTile extends TileEntity implements ITickableTileEntity 
{	
	int radius = GobberConfigBuilder.PROTECTOR_RADIUS.get();
	
    public BlockProtectorTile()
	{
		super(TileInit.BLOCK_PROTECTOR.get());
	}
   
	@Override
    public void tick() 
    {
		if(world != null && !world.isRemote)
		{
			List<Entity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius), e -> (e instanceof LivingEntity));
			for(Entity entity : entities)
			{
				if(entity instanceof PlayerEntity)
				{
					PlayerEntity player = (PlayerEntity)entity;

					int newfoodlevel = 1;
					float newsatlevel = 0.035F;
					PlayerSpecialAbilities.giveRegenEffect(world, player, null, newfoodlevel, newsatlevel);
				}

				EntityClassification isCreature = entity.getEntity().getClassification(true);

				// These types of mobs are excluded
				if(isCreature == EntityClassification.CREATURE ||
					entity instanceof PlayerEntity ||
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
}