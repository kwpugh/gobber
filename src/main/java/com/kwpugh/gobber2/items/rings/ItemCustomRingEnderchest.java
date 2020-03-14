package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingEnderchest extends Item
{
	public static final TranslationTextComponent field_220115_d = new TranslationTextComponent("item.gobber2.gobber2_ring_enderchest.title");
	
	public ItemCustomRingEnderchest(Properties properties)
	{
		super(properties);
	}

//	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
//	{		
//		if(entity instanceof PlayerEntity && !world.isRemote)
//		{
//			PlayerEntity player = (PlayerEntity)entity;
//
//			if(ClientEventSubscriber.playerHasKeyPressed)
//	        {
//	    		EnderChestInventory enderChest = player.getInventoryEnderChest();
//	    		
//	    		if (enderChest != null)
//	    		{
//	    			if (!world.isRemote)
//	    			{
//	    				 player.openContainer(new SimpleNamedContainerProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> {
//	    		               return ChestContainer.createGeneric9X3(p_220114_1_, p_220114_2_, enderChest);
//	    		            }, ItemCustomRingEnderchest.field_220115_d));
//	    			}
//	    		}
//	        }	
//		}
//	}
//	
//    @Override
//    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundNBT unused)
//    {
//        if (SupportMods.CURIOS.isLoaded())
//        {
//            return CuriosEnderchestUtil.initCapabilities();
//        }
//        
//        return super.initCapabilities(stack, unused);
//    }
    
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		
		EnderChestInventory enderChest = player.getInventoryEnderChest();
		
		if (enderChest != null)
		{
			if (!world.isRemote)
			{
				 player.openContainer(new SimpleNamedContainerProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> {
		               return ChestContainer.createGeneric9X3(p_220114_1_, p_220114_2_, enderChest);
		            }, field_220115_d));
			}
		}
		return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
	}
	 
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_enderchest.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_enderchest.line2").applyTextStyle(TextFormatting.YELLOW)));
	} 
}
