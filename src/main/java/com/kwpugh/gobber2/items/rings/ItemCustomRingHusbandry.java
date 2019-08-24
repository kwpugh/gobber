package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingHusbandry extends Item
{

	public ItemCustomRingHusbandry(Properties properties)
	{
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ActionResult<ItemStack> result = super.onItemRightClick(world, player, hand);

			if (!world.isRemote)
			{
				int radius = 10;
		        
			   	List<AnimalEntity> mobs = world.getEntitiesWithinAABB(AnimalEntity.class, player.getBoundingBox().grow(radius, radius, radius));
			    for (AnimalEntity animal : mobs)
			    {
			       	animal.setInLove(player);
			    }
			}		
		return result; 	
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Causes animals in a 10 block radius to mate"));
		list.add(new StringTextComponent(TextFormatting.BLUE + "Normal breeding cycles still apply"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to use"));
	}   
}
