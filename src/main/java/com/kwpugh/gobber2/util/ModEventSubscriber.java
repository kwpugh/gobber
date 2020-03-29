package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

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
                PlayerEquipsUtil.isPlayerGotVoidProtection(player))
            {
                if (event.isCancelable()) event.setCanceled(true);
            } 
            
            //No Fall Damage
            if ((event.getSource() == DamageSource.FALL) &&
                PlayerEquipsUtil.isPlayerGotFallProtection(player))
            {
                if (event.isCancelable()) event.setCanceled(true);
            } 
            
            //Fire & lava damage
            if (((event.getSource() == DamageSource.IN_FIRE) ||
            		(event.getSource() == DamageSource.ON_FIRE) || 
            		(event.getSource() == DamageSource.LAVA)) && 
            		PlayerEquipsUtil.isPlayerGotFireProtection(player))
            {
                if (event.isCancelable()) event.setCanceled(true);
            } 
            
            //Drowning
            if ((event.getSource() == DamageSource.DROWN) &&
                    PlayerEquipsUtil.isPlayerGotWaterBreathing(player))
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
    		if (PlayerEquipsUtil.isPlayerGotStealth(player))
    		{
    			attacker.setAttackTarget(null);
    		}
        }
    }
    

    @SubscribeEvent
    public static void breakingBlockSpeed(PlayerEvent.BreakSpeed event)
    {
        PlayerEntity player = event.getPlayer();
        ItemStack stack = player.getHeldItemMainhand(); 
        BlockPos pos = event.getPos();
        Block block = event.getState().getBlock();
        
        if (player != null && !(player instanceof FakePlayer) && !player.isCreative())
        {    
        	if(PlayerEquipsUtil.isPlayerGotHasteRing(player))
        	{
        		if(net.minecraftforge.common.ForgeHooks.canToolHarvestBlock(event.getPlayer().world, pos, stack))
        		{
        			if(block == Blocks.OBSIDIAN)
        			{
        				event.setNewSpeed(GeneralModConfig.HASTE_RING_BREAK_SPEED.get() * 8);
        			}
        			else
        			{
        				event.setNewSpeed(GeneralModConfig.HASTE_RING_BREAK_SPEED.get());
        			}
						
        		}
        	}
        }
    }
} 