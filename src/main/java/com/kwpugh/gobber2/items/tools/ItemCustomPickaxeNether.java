package com.kwpugh.gobber2.items.tools;


import java.util.List;

import com.kwpugh.gobber2.lists.ItemList;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomPickaxeNether extends PickaxeItem
{
	public ItemCustomPickaxeNether(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof PlayerEntity && !world.isRemote && EnableUtil.isEnabled(stack))
		{
			PlayerEntity player = (PlayerEntity)entity;

			if (player.ticksExisted % 240 == 0)
			{
				player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 520, 0, false, false));
			} 		
		}
	}	

	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
		ItemStack stack = player.getHeldItem(hand);
		
        if(!world.isRemote && player.isCrouching())
        {
            EnableUtil.changeEnabled(player, hand);
            player.sendMessage(new StringTextComponent("Night vision ability active: " + EnableUtil.isEnabled(stack)));
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }
        return super.onItemRightClick(world, player, hand);
    }
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemList.gobber2_ingot_nether;
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click for Night Vision"));
		list.add(new StringTextComponent(TextFormatting.RED + "Night vision ability active: " + EnableUtil.isEnabled(stack)));
		list.add(new StringTextComponent(TextFormatting.GOLD + "Sneak right-click to toggle ability on/off"));
	} 
}
