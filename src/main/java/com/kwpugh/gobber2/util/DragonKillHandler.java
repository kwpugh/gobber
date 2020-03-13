package com.kwpugh.gobber2.util;

import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.world.dimension.EndDimension;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/*
 * Credits to Darkere from More Dragon Eggs mod
 * 
 * I suggest you use the More Dragon Eggs
 * mod if you can and support its author
 * 
 * If you cannot, for some reason, this
 * feature can be turned on in the config file
 * 
 */

public class DragonKillHandler
{
    public DragonKillHandler()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event)
    {
        if(event.getEntity().getEntityWorld().getDimension() instanceof EndDimension)
        {
        	if(GeneralModConfig.ENABLE_DRAGON_KILL_EVERY_KILL.get())
        	{
                if (event.getEntity() instanceof EnderDragonEntity)
                {
                    EnderDragonEntity d = (EnderDragonEntity) event.getEntity();
                    if (d.getFightManager() != null && d.getFightManager().hasPreviouslyKilledDragon())
                    {
                        event.getEntity().getEntityWorld().setBlockState(event.getEntity().getEntityWorld().getHeight(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.getDefaultState());
                    }
                }	
        	}
        }
    }
}