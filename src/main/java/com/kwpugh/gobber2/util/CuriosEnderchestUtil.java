package com.kwpugh.gobber2.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.kwpugh.gobber2.items.rings.ItemCustomRingEnderchest;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

/*
 * Utility to check if ClientEventHandler key is pressed and open Enderchest screen
 */

public class CuriosEnderchestUtil
{	
    public static ICapabilityProvider initCapabilities()
    {
        ICurio curio = new ICurio()
        {          
            @Override
            public void onCurioTick(String identifier, int index, LivingEntity livingEntity)
            {
                if (livingEntity instanceof PlayerEntity)
                {
                    PlayerEntity player = ((PlayerEntity) livingEntity);
                    World world = player.world;
                    
                    if(ClientEventSubscriber.playerHasKeyPressed)
                    {
                		EnderChestInventory enderChest = player.getInventoryEnderChest();
                		
                		if (enderChest != null)
                		{
                			if (!world.isRemote)
                			{
                				 player.openContainer(new SimpleNamedContainerProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> {
                		               return ChestContainer.createGeneric9X3(p_220114_1_, p_220114_2_, enderChest);
                		            }, ItemCustomRingEnderchest.field_220115_d));
                			}
                		}
                    }
                }
            }
        };

        return new ICapabilityProvider()
        {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
            {
                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };
    }
}