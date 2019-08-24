package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingBlaze extends Item
{

	public ItemCustomRingBlaze(Properties properties)
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
					double x = player.posX;
					double y = player.posY;
					double z = player.posZ;
					double d0 = 10.0D;
					double d1 = 5.0D;
					
					MobEntity hostileMob = scanForHostileMobs(world, x, y, z, d0, d1);
		
					if(hostileMob != null)
					{			
						if (!world.isRemote)
						{	
							hostileMob.spawnExplosionParticle();
							hostileMob.remove();
							hostileMob.entityDropItem(Items.BLAZE_ROD, 2);
						}
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
			// Only select these types of mobs for killing effect
			if (entitymob instanceof BlazeEntity)
			{
				closestMob = entitymob;
				return closestMob;
			}
		}
		return null;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Kills nearby Blaze and drops a bounty of Blaze Rods"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Works while in player main hand"));
	}  
}
