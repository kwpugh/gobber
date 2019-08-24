package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingAcceleration extends Item
{

	public ItemCustomRingAcceleration(Properties properties)
	{
		super(properties);
	}

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
				
				player.addVelocity(lookX * 0.35, lookY * 0.35, lookZ * 0.35);	
			}
		 }	
		 return result;
		 
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Provides a boost in velocity while player is aloft"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click and hold, then jump"));
	}   
}
