package com.kwpugh.gobber2.blocks.tileentities;

import java.util.List;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.TileInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.HoglinEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.SpellcastingIllagerEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class BlockLooterTile extends TileEntity implements ITickableTileEntity 
{	
	int radius = GobberConfigBuilder.LOOTER_RADIUS.get();
	boolean xpOnly = false;
    public BlockLooterTile()
	{
		super(TileInit.BLOCK_LOOTER.get());
	}
   
	@Override
    public void tick() 
    {
		if(world != null && !world.isRemote && !world.isBlockPowered(pos))
		{
			//Scan the radius for LivingEntity and store in list
			List<Entity> mobs = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius), e -> (e instanceof LivingEntity));
			for(Entity mob : mobs)
			{
				if(mob instanceof ArmorStandEntity)
				{
					xpOnly = true;
				}

				if(mob instanceof ZombieEntity || mob instanceof ZombieVillagerEntity || mob instanceof PiglinBruteEntity || mob instanceof PiglinEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.GOLD_INGOT);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
				
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof SpiderEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.STRING);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
					
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof SkeletonEntity || mob instanceof StrayEntity)
				{		
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.BONE);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}					
		
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
					if(!xpOnly)
					{					
						ItemStack drop = new ItemStack(Items.GUNPOWDER);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}

					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
			
				if(mob instanceof SlimeEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{	
						ItemStack drop = new ItemStack(Items.SLIME_BALL);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
					
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof WitchEntity || mob instanceof SilverfishEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.EMERALD);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}

					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof EndermanEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.ENDER_PEARL);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
			
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof BlazeEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.BLAZE_ROD);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
		
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof WitherSkeletonEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.WITHER_SKELETON_SKULL);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
				
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof PhantomEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.PHANTOM_MEMBRANE);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
				
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof VindicatorEntity || mob instanceof SpellcastingIllagerEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.DIAMOND);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
				
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
				
				if(mob instanceof HoglinEntity || mob instanceof ZoglinEntity)
				{
					((MobEntity) mob).spawnExplosionParticle();
					mob.remove(true);
					if(!xpOnly)
					{
						ItemStack drop = new ItemStack(Items.LEATHER);				
						world.addEntity(new ItemEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, drop));
					}
				
					world.addEntity(new ExperienceOrbEntity(world, pos.getX()+3, pos.getY(), pos.getZ()+3, 1));
				}
		   }
		}			
    }
}