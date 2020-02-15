package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingStealth extends Item
{

	public ItemCustomRingStealth(Properties properties)
	{
		super(properties);
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		
		if(!world.isRemote)
		{
			if(entity instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity)entity;

				ItemStack equipped = player.getHeldItemMainhand();

				if(!world.isRemote && !player.isCreative())
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
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_stealth.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_stealth.line2").applyTextStyle(TextFormatting.YELLOW)));
	} 
}
