package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.GeneralModConfig;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Based on work by: Maciej916 in Ma-Essentials
 */

public class ItemCustomRingAbove extends Item
{
	public ItemCustomRingAbove(Properties properties)
	{
		super(properties);
	}

	int ringAboveCooldown = GeneralModConfig.RING_ABOVE_COOLDOWN.get();
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
	
		if (world.getDimension().getType() != DimensionType.OVERWORLD)
		{
			player.sendStatusMessage(new TranslationTextComponent("item.gobber2.gobber2_ring_above.line5"), true);
		}
		
		player.getCooldownTracker().setCooldown(this, ringAboveCooldown);
		
		if (!world.isRemote && (world.getDimension().getType() == DimensionType.OVERWORLD))
		{
			if(player.isShiftKeyDown())
			{
				//Checking from bottom of world and working upward
				double x = player.getPosX();
				double y = 0;
				double z = player.getPosZ();
				
				Chunk chunk = world.getChunk((int) player.getPosX() >> 4, (int)player.getPosZ() >> 4);

				while (y < world.getHeight())
				{
		            y++;

		            BlockPos groundPos = new BlockPos(x, y+2, z);
		            if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR))
		            {
		                BlockPos legPos = new BlockPos(x, y+1, z);
		                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
		                {
		                    BlockPos headPos = new BlockPos(x, y, z);
		                    if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
		                    {	                    	
		                    	player.stopRiding();
		    	           		((ServerPlayerEntity)player).connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
		                    	
		    	           		return ActionResult.resultSuccess(stack);                       
		                    }
		                }
		            }
		        }
				
			}
			else
			{	
				//Checking from top of world downward
				double x = player.getPosX();
				double y = world.getMaxHeight();
				double z = player.getPosZ();
				
				Chunk chunk = world.getChunk((int) player.getPosX() >> 4, (int)player.getPosZ() >> 4);

				while (y > 0)
				{
		            y--;

		            BlockPos groundPos = new BlockPos(x, y-2, z);
		            if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR))
		            {
		                BlockPos legPos = new BlockPos(x, y-1, z);
		                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
		                {
		                    BlockPos headPos = new BlockPos(x, y, z);
		                    if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
		                    {	                    	
		                    	player.stopRiding();
		    	           		((ServerPlayerEntity)player).connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
		                    	
		    	           		return ActionResult.resultSuccess(stack);                       
		                    }
		                }
		            }
		        }		
			}
		}
		
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_above.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_above.line2").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_above.line3").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_above.line4").applyTextStyle(TextFormatting.RED)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring.cooldown",ringAboveCooldown).applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	} 	
}
