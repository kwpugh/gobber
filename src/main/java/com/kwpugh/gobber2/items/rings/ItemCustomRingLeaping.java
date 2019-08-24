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

public class ItemCustomRingLeaping extends Item
{

	public ItemCustomRingLeaping(Properties properties)
	{
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand)
	{
		ActionResult<ItemStack> result = super.onItemRightClick(world, entity, hand);
 
    	Vec3d look = entity.getLookVec().normalize();
		double lookX = look.x;
		double lookY = look.y;
		double lookZ = look.z;
		
		//Get some vertical height to start
		if(entity.onGround && !entity.isSneaking())	
		{
			entity.setMotion(lookX * 0.0, lookY * 3.0, lookZ * 0.0);
		}
        
		//Once aloft, provide some horizontal movement
		if(!entity.onGround)
		{	
			entity.addVelocity(lookX * 0.4, lookY * 0.4, lookZ * 0.4);
		}
		return result;
		 
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Provides a small leaping ability"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to use"));
	}  
}
