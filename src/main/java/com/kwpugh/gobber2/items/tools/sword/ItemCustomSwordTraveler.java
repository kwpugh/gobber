package com.kwpugh.gobber2.items.tools.sword;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomSwordTraveler extends SwordItem
{
	public ItemCustomSwordTraveler(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
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
		if(entity.onGround && !entity.isCrouching())	
		{
			entity.setMotion(lookX * 0.0, lookY * 4.5, lookZ * 0.0);
		}
        
		//Once aloft, provide some horizontal movement
		if(!entity.onGround)
		{	
			entity.addVelocity(lookX * 0.6, lookY * 0.6, lookZ * 0.6);
		}
		return result;		 
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_sword_traveler.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_sword_traveler.line2").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_end.unbreakable").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	} 
}