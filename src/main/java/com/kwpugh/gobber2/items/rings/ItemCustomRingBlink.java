package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.GeneralModConfig;
import com.kwpugh.gobber2.util.RayTraceUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingBlink extends Item
{
	public ItemCustomRingBlink(Properties properties)
	{
		super(properties);
	}

	int blinkCooldown = GeneralModConfig.RING_BLINK_COOLDOWN.get();
	  
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		player.getCooldownTracker().setCooldown(this, blinkCooldown);
		 
		if (!world.isRemote)
		{						
			RayTraceResult pos = RayTraceUtil.getNearestPositionWithAir(world, player, 100);
            if(pos != null && (pos.getType() == RayTraceResult.Type.BLOCK || player.rotationPitch >= -5))
            {
            	int side = pos.getType().ordinal();
                if(side != -1)
                {
                    double x = pos.getHitVec().x-(side == 4 ? 0.5 : 0)+(side == 5 ? 0.5 : 0);
                    double y = pos.getHitVec().y-(side == 0 ? 2.0 : 0)+(side == 1 ? 0.5 : 0);
                    double z = pos.getHitVec().z-(side == 2 ? 0.5 : 0)+(side == 3 ? 0.5 : 0);
	           		
	           		player.stopRiding();
	           		((ServerPlayerEntity)player).connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);

                    world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);

                    return ActionResult.resultSuccess(stack);
                }
            }		 
        }

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_blink.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_blink.line2").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_blink.line3").applyTextStyle(TextFormatting.RED)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring.cooldown",blinkCooldown).applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	}   
}
