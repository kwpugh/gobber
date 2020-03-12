package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.EnableUtil;
import com.kwpugh.gobber2.util.MagnetRange;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingAttraction extends Item
{
	public ItemCustomRingAttraction(Properties properties)
	{
		super(properties);
	}
	
	int range;

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{	
		if(entity instanceof PlayerEntity && !world.isRemote && EnableUtil.isEnabled(stack))
		{
			PlayerEntity player = (PlayerEntity)entity;
			
			boolean init = MagnetRange.getCurrentlySet(stack);
			
			if(!init)
			{
				range = 8;
			}
			else
			{
				range = MagnetRange.getCurrentRange(stack);
			}			

			double x = player.getPosX();
			double y = player.getPosY();
			double z = player.getPosZ();

			//Scan for and collect items
			List<ItemEntity> items = entity.world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
			for(ItemEntity e: items)
			{
				if(!player.isShiftKeyDown() && !e.getPersistentData().getBoolean("PreventRemoteMovement"))
				{						
					double factor = 0.035;
					e.addVelocity((x - e.getPosX()) * factor, (y - e.getPosY()+1.25) * factor, (z - e.getPosZ()) * factor);
				}
			}

			//Scan for and collect XP Orbs
			List<ExperienceOrbEntity> xp = entity.world.getEntitiesWithinAABB(ExperienceOrbEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
			for(ExperienceOrbEntity orb: xp)
			{
				if(!player.isShiftKeyDown())
				{						
					double factor = 0.035;
					orb.addVelocity((x - orb.getPosX()) * factor, (y - orb.getPosY()+1.25) * factor, (z - orb.getPosZ()) * factor);
				}
			}
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
		ItemStack stack = player.getHeldItem(hand);	
		
		if(!world.isRemote && !(player.isShiftKeyDown()))
        {
            EnableUtil.changeEnabled(player, hand);
            player.sendMessage(new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line2", EnableUtil.isEnabled(stack)).applyTextStyle(TextFormatting.GREEN));
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }		
		
        if(!world.isRemote && player.isShiftKeyDown())
        {
			if(range == 8)
			{
				range = 10;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRange.getCurrentRange(stack)).applyTextStyle(TextFormatting.GREEN)));
			}
			else if(range == 10)
			{
				range = 12;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRange.getCurrentRange(stack)).applyTextStyle(TextFormatting.GREEN)));
			}
			else if(range == 12)
			{
				range = 14;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRange.getCurrentRange(stack)).applyTextStyle(TextFormatting.GREEN)));
			}
			else if(range == 14)
			{
				range = 4;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRange.getCurrentRange(stack)).applyTextStyle(TextFormatting.GREEN)));
			}
			else if(range == 4)
			{
				range = 8;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line5", MagnetRange.getCurrentRange(stack)).applyTextStyle(TextFormatting.GREEN)));
			}
        }
        
        return super.onItemRightClick(world, player, hand);
    }
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return EnableUtil.isEnabled(stack);
	}
	  
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line2", EnableUtil.isEnabled(stack)).applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line3").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_attraction.line4").applyTextStyle(TextFormatting.RED)));
	}     
}
