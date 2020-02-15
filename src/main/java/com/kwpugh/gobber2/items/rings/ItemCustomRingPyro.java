package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingPyro extends Item
{
	public ItemCustomRingPyro(Properties properties)
	{
		super(properties);
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof PlayerEntity && !world.isRemote)
		{
			PlayerEntity player = (PlayerEntity)entity;
			
			ItemStack equipped = player.getHeldItemMainhand();

			if(!world.isRemote)
			{
				if(stack == equipped)
				{
					double x = player.getPosX();
					double y = player.getPosY();
					double z = player.getPosZ();
					
					double d0 = 8.0D;
					double d1 = 5.0D;
					
					MobEntity hostileMob = scanForHostileMobs(world, x, y, z, d0, d1);
		
					if(hostileMob != null)
					{					
						hostileMob.addPotionEffect(new EffectInstance(Effects.LEVITATION, 3600, 0, false, false));
						hostileMob.setFire(80);
					}					
				}
			}				
		}
	}
		   
	private MobEntity scanForHostileMobs(World world, double xpos, double ypos, double zpos, double d0, double d1)
	{
		List<MobEntity> list = world.<MobEntity>getEntitiesWithinAABB(MobEntity.class, new AxisAlignedBB
				((double) xpos - d0,
				 (double) ypos - d1,
				 (double) zpos - d0,
				 (double) xpos + d0, ypos + d1,
				 (double) zpos + d0));
	
		MobEntity closestMob = null;
	
		for (MobEntity entitymob : list)
		{
			// Exclude these mobs
			if (entitymob instanceof AnimalEntity ||
					entitymob instanceof ElderGuardianEntity ||
					entitymob instanceof EvokerEntity ||
					entitymob instanceof GuardianEntity ||
					entitymob instanceof VexEntity ||
					entitymob instanceof VindicatorEntity ||
					entitymob instanceof WitherEntity ||
					entitymob instanceof EnderDragonEntity)
			{
				continue;
			}
			else
			{
				closestMob = entitymob;
				return closestMob;
			}
		}
		return null;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_pyro.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_pyro.line2").applyTextStyle(TextFormatting.YELLOW)));
	} 
}
