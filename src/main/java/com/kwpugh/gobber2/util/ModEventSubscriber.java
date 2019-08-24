package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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
        } 
    }
   
//    @SubscribeEvent
//    public static void onTickPlayerEvent(TickEvent.PlayerTickEvent event)
//    {
//        PlayerEntity player = (PlayerEntity) event.player;
//       
//        if(ArmorUtil.isPlayerGotCreativeFlight(player))
//        {
//            event.player.abilities.allowFlying = true;
//        }
//        
//        if(!ArmorUtil.isPlayerGotCreativeFlight(player))
//        {
//            if(event.player.abilities.isFlying)
//            {
//                event.player.abilities.isFlying = event.player.abilities.isCreativeMode ? true : false;
//                event.player.abilities.allowFlying = event.player.abilities.isCreativeMode ? true : false;
//            }
//        }
//    }		
} 