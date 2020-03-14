package com.kwpugh.gobber2.util;

/*
 * Utility to check if ClientEventHandler key is pressed and open Enderchest screen
 */

//public class CuriosEnderchestUtil
//{	
//    public static ICapabilityProvider initCapabilities()
//    {
//        ICurio curio = new ICurio()
//        {          
//            @Override
//            public void onCurioTick(String identifier, int index, LivingEntity livingEntity)
//            {
//                if (livingEntity instanceof PlayerEntity)
//                {
//                    PlayerEntity player = ((PlayerEntity) livingEntity);
//                    World world = player.world;
//                    
//                    if(ClientEventSubscriber.playerHasKeyPressed)
//                    {
//                		EnderChestInventory enderChest = player.getInventoryEnderChest();
//                		
//                		if (enderChest != null)
//                		{
//                			if (!world.isRemote)
//                			{
//                				 player.openContainer(new SimpleNamedContainerProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> {
//                		               return ChestContainer.createGeneric9X3(p_220114_1_, p_220114_2_, enderChest);
//                		            }, ItemCustomRingEnderchest.field_220115_d));
//                			}
//                		}
//                    }
//                }
//            }
//        };
//
//        return new ICapabilityProvider()
//        {
//            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);
//
//            @Nonnull
//            @Override
//            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
//            {
//                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
//            }
//        };
//    }
//}