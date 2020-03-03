package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingHaste extends Item
{

	public ItemCustomRingHaste(Properties properties)
	{
		super(properties);
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof PlayerEntity && !world.isRemote && EnableUtil.isEnabled(stack))
		{
			PlayerEntity player = (PlayerEntity)entity;
			if (player.ticksExisted % 180 == 0)
			{
				player.addPotionEffect(new EffectInstance(Effects.HASTE, 260, 4, false, false));
			} 	
		}
	}	

	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
		ItemStack stack = player.getHeldItem(hand);
		
        if(!world.isRemote && player.isShiftKeyDown())
        {
            EnableUtil.changeEnabled(player, hand);
            player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_haste.line1", EnableUtil.isEnabled(stack)).applyTextStyle(TextFormatting.GREEN)));
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }
        return super.onItemRightClick(world, player, hand);
    }

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_haste.line2").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_haste.line3", EnableUtil.isEnabled(stack)).applyTextStyle(TextFormatting.RED)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_haste.line4").applyTextStyle(TextFormatting.YELLOW)));
	}   
}
