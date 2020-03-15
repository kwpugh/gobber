package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.GeneralModConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingAcceleration extends Item
{
	public ItemCustomRingAcceleration(Properties properties)
	{
		super(properties);
	}

	public static double velocityAcceleration = GeneralModConfig.RING_ACCELERATION_VELOCITY.get();
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{		
		ActionResult<ItemStack> result = super.onItemRightClick(world, player, hand);
		ItemStack itemstack = result.getResult();
		
		ItemStack equippedMain = player.getHeldItemMainhand();
		
		 if(equippedMain == itemstack)  //Only works while in the main hand
		 {   			
			 // Right-click while in air gives acceleration in direction looking
			if(!player.onGround)
			{
				Vec3d look = player.getLookVec().normalize();
				double lookX = look.x;
				double lookY = look.y;
				double lookZ = look.z;
				
				if(velocityAcceleration < .30)
				{
					player.addVelocity(lookX * velocityAcceleration, lookY * velocityAcceleration, lookZ * velocityAcceleration);
				}
				else
				{
					player.sendStatusMessage(new TranslationTextComponent("item.gobber2.gobber2_ring_acceleration.line3"), true);
				}				
			}
		 }	
		 return result; 
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_acceleration.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_acceleration.line2").applyTextStyle(TextFormatting.YELLOW)));
	}  
}
