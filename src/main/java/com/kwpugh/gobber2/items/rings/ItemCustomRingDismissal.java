package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingDismissal extends Item
{
	public ItemCustomRingDismissal(Properties properties)
	{
		super(properties);
	}
	double velocity = GobberConfigBuilder.RING_DISMISSAL_VELOCITY.get();
	double lift = GobberConfigBuilder.RING_DISMISSAL_LIFT.get();
	double range = GobberConfigBuilder.RING_DISMISSAL_RANGE.get();
	
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
					Vector3d look = player.getLookVec().normalize();	
					double lookX = look.getX();
					double lookY = look.getY();
					double lookZ = look.getZ();
										
					double x = player.getPosX();
					double y = player.getPosY();
					double z = player.getPosZ();

					double d0 = range;
					double d1 = 4.0D;

					MobEntity hostileMob = scanForHostileMobs(world, x, y, z, d0, d1);

					if(hostileMob != null)
					{
						hostileMob.addVelocity(lookX * velocity, lookY * lift, lookZ * velocity);
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
			EntityClassification isCreature = entitymob.getEntity().getClassification(true);

			// Exclude some of the harder mobs
			if (isCreature == EntityClassification.CREATURE ||
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
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_dismissal.line1").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_dismissal.line2").mergeStyle(TextFormatting.YELLOW)));
	}
}
