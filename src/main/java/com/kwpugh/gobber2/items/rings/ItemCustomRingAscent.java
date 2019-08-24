package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingAscent extends Item
{

	public ItemCustomRingAscent(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ActionResult<ItemStack> result = super.onItemRightClick(world, player, hand);

			if (!world.isRemote)
			{
				if(player.onGround)
				{
					player.addPotionEffect(new EffectInstance(Effects.LEVITATION, 3600, 0, false, false));
				}
			}		
		return result; 	
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{			
		if(entity instanceof PlayerEntity && entity.isSneaking())
		{
			((LivingEntity) entity).removeActivePotionEffect(Effects.LEVITATION);	
		}	
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Provides the player 3 minutes of Levitation"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to use, press sneak to remove effect"));
		list.add(new StringTextComponent(TextFormatting.YELLOW + "Use with Ring of Acceleration for faster traveling"));
		list.add(new StringTextComponent(TextFormatting.RED + "Provides No Fall Damage while held in main hand"));
	}   
}
