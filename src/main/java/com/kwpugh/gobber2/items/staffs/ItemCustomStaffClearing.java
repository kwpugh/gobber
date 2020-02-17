package com.kwpugh.gobber2.items.staffs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomStaffClearing extends Item
{

	public ItemCustomStaffClearing(Properties properties)
	{
		super(properties);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

		if (!world.isRemote)
		{
			Block block;
			List<BlockPos> poslist = new ArrayList<BlockPos>();

			for (int x = -9; x <= 9; x++)
			{
				for (int y = -9; y <= 9; y++)
				{
					for (int z = -6; z <= 20; z++)
					{
						BlockPos pos = player.getPosition().add(x, y, z);
						block = world.getBlockState(pos).getBlock();
						
						if (block == Blocks.GRASS || 
								block == Blocks.DEAD_BUSH || 
								block == Blocks.TALL_GRASS || 
								block == Blocks.FERN || 
								block == Blocks.LARGE_FERN || 
								block == Blocks.SEAGRASS || 
								block == Blocks.TALL_SEAGRASS || 
								block == Blocks.CACTUS || 
								block == Blocks.SUGAR_CANE || 
								block == Blocks.BAMBOO || 
								block instanceof MushroomBlock || 
								block instanceof FlowerBlock)
						{
							poslist.add(player.getPosition().add(x, y, z));
						}
						
						if(player.isCrouching())
						{
							if (block instanceof LeavesBlock)
							{
								poslist.add(player.getPosition().add(x, y, z));
							}
						}
					}
				}
			}

			if (!poslist.isEmpty())
			{
				for (int i = 0; i <= poslist.size() - 1; i++)
				{
					BlockPos targetpos = poslist.get(i);
					block = world.getBlockState(targetpos).getBlock();

					if (block == Blocks.GRASS || block == Blocks.DEAD_BUSH || block == Blocks.TALL_GRASS || block instanceof FlowerBlock)
					{
						world.destroyBlock(targetpos, true);
					}
					
					if(player.isCrouching())
					{
						if (block instanceof LeavesBlock)
						{
							world.destroyBlock(targetpos, true);
						}
					}
				}
			}
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	 public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
	 {
	      stack.damageItem(1, attacker, (p_220045_0_) -> {
	         p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	      });

    	  target.addPotionEffect(new EffectInstance(Effects.POISON, (1200)));
    	  target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, (1200)));
    	  target.addPotionEffect(new EffectInstance(Effects.HUNGER, (1200)));
    	  target.addPotionEffect(new EffectInstance(Effects.NAUSEA, (1200)));
    	  target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, (1200)));
    	  target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, (1200)));
    	  target.addPotionEffect(new EffectInstance(Effects.POISON, (1200)));
    	  target.addPotionEffect(new EffectInstance(Effects.WITHER, (1200)));

	      return true;
	   }

	 @OnlyIn(Dist.CLIENT)
	 public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	 {
		 super.addInformation(stack, worldIn, tooltip, flagIn);
		 tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_clearing.line1").applyTextStyle(TextFormatting.GREEN)));
		 tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_clearing.line2").applyTextStyle(TextFormatting.GREEN)));
		 tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_clearing.line3").applyTextStyle(TextFormatting.YELLOW)));
	 }
}
