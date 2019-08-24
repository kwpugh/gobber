package com.kwpugh.gobber2.items.medallions;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomMedallionStepping extends Item
{

	public ItemCustomMedallionStepping(Properties properties)
	{
		super(properties);
	}

	float currentStepHeight;
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;
			player.stepHeight = currentStepHeight;
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
		if(!(player.isSneaking()))
		{
			player.sendMessage(new StringTextComponent("Current player step height: " + player.stepHeight));
		}
		
        if(player.isSneaking())
        {   
        	if(player.stepHeight < 1.0F)
		    {
		    	player.stepHeight = 1.0F;
		    	player.sendMessage(new StringTextComponent("Step Height set to 1 block"));
		    }
		    else if(player.stepHeight == 1.0F)
			{
		    	player.stepHeight = 2.1F;
		    	player.sendMessage(new StringTextComponent("Step Height set to 2 blocks"));
			}
			else if(player.stepHeight == 2.1F)
			{
				player.stepHeight = 3.1F;
				player.sendMessage(new StringTextComponent("Step Height set to 3 blocks"));
			}
			else if(player.stepHeight == 3.1F)
			{
				player.stepHeight = 0.6F;
				player.sendMessage(new StringTextComponent("Step Height set to vanilla default"));
				player.sendMessage(new StringTextComponent("You will need to relog to restore full sneak ability"));
			}		    
		    
        	currentStepHeight = player.stepHeight;

            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }
        return super.onItemRightClick(world, player, hand);
	}
	
   @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Allows the player to toggle through default, 1, 2, and 3 step height"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Sneak + right-click to toggle through"));
		list.add(new StringTextComponent(TextFormatting.YELLOW + "To restore full sneak abilities, please relog"));
	} 
}
