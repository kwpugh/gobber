package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.dimension.EndDimension;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/*
 * Inspired and adapted from Sinhika's code in NetherRocks
 * 
 */

@EventBusSubscriber(modid = Gobber2.modid, bus = EventBusSubscriber.Bus.FORGE )
public final class ModEventSubscriber
{	
    @SubscribeEvent(receiveCanceled = true, priority= EventPriority.HIGHEST)
    public static void onLivingHurtEvent(LivingAttackEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();

            //Void protection
            if ((event.getSource() == DamageSource.OUT_OF_WORLD) &&
                ArmorUtil.isPlayerGotVoidProtection(player))
            {
                if (event.isCancelable()) event.setCanceled(true);
            } 
            
            //No Fall Damage
            if ((event.getSource() == DamageSource.FALL) &&
                ArmorUtil.isPlayerGotFallProtection(player))
            {
                if (event.isCancelable()) event.setCanceled(true);
            } 
            
            //Fire & lava damage
            if (((event.getSource() == DamageSource.IN_FIRE) ||
            		(event.getSource() == DamageSource.ON_FIRE) || 
            		(event.getSource() == DamageSource.LAVA)) && 
            		ArmorUtil.isPlayerGotFireProtection(player))
            {
                if (event.isCancelable()) event.setCanceled(true);
            } 
            
            //Drowning
            if ((event.getSource() == DamageSource.DROWN) &&
                    ArmorUtil.isPlayerGotWaterBreathing(player))
            {
            	if (event.isCancelable()) event.setCanceled(true);
            }
        } 
    }
    
    @SubscribeEvent
    public static void onLivingSetAttackTarget(LivingSetAttackTargetEvent event)
    {
    	if (event.getTarget() instanceof PlayerEntity && event.getEntityLiving()instanceof MobEntity)
        {	
    		PlayerEntity player = (PlayerEntity) event.getTarget();
    		MobEntity attacker = (MobEntity) event.getEntityLiving();
    		
    		//hostile mobs won't target player
    		if (ArmorUtil.isPlayerGotStealth(player))
    		{
    			attacker.setAttackTarget(null);
    		}
        }
    }
} 