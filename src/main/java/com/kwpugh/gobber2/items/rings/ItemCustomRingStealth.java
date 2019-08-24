package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingStealth extends Item
{

	public ItemCustomRingStealth(Properties properties)
	{
		super(properties);
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof PlayerEntity && !world.isRemote)
		{
			PlayerEntity player = (PlayerEntity)entity;

			ItemStack equipped = player.getHeldItemMainhand();

			if(!world.isRemote)
			{
				if(stack == equipped)
		        {
		        	player.abilities.disableDamage = true;
		        	player.setInvisible(true);
		        }
		        else
		        {
		        	player.abilities.disableDamage = false;
		        	player.setInvisible(false);
		        }
			}	
			
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Allows the player to go undetected by hostile mobs"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Works while in player main hand"));
	}  

}
