package com.kwpugh.gobber2.blocks.tileentities;

import java.util.List;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.TileInit;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class BlockHealerTile extends TileEntity implements ITickableTileEntity 
{	
	int radius = GobberConfigBuilder.HEALER_RADIUS.get();
	
    public BlockHealerTile()
	{
		super(TileInit.BLOCK_HEALER.get());
	}
   
	@Override
    public void tick() 
    {
		if (world != null && !world.isRemote)
		{     
			List<Entity> entities = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius));
			for(Entity entity : entities)
			{
				PlayerEntity player = (PlayerEntity)entity;
				
				if(entity instanceof PlayerEntity)
				{					
					int newfoodlevel = 1;
					float newsatlevel = 0.025F;
					PlayerSpecialAbilities.giveRegenEffect(world, player, null, newfoodlevel, newsatlevel);
				}
			}
		}			
    }
}