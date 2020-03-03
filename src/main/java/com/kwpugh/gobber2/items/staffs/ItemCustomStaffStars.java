package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomStaffStars extends Item
{
	public ItemCustomStaffStars(Properties properties)
	{
		super(properties);
	}

    @Override
    public ActionResultType onItemUse(ItemUseContext iuc)
    {    	
    	BlockPos torchPos;
    	BlockPos pos = iuc.getPos();
		if(iuc.getWorld().getBlockState(pos).getBlock() == Blocks.TORCH
				|| iuc.getWorld().getBlockState(pos).getBlock() == Blocks.WALL_TORCH)
		{
			return ActionResultType.FAIL;
		}
    	
    	Boolean isWallTorch = false;
    	switch(iuc.getFace())
    	{
    	case DOWN:
    		return ActionResultType.FAIL;
    	case UP:
    		torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
    		break;
    	case NORTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
    		isWallTorch = true;
    		break;
    	case SOUTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
    		isWallTorch = true;
    		break;
    	case WEST:
    		torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
    		isWallTorch = true;
    		break;
    	case EAST:
    		torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
    		isWallTorch = true;
    		break;
    	default:
    		return ActionResultType.FAIL;
    	}
    	
    	if(iuc.getWorld().getBlockState(torchPos).isAir())
    	{		
    		if (isWallTorch)
    		{
    			iuc.getWorld().setBlockState(torchPos, Blocks.WALL_TORCH.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, iuc.getFace()));
    			iuc.getWorld().playSound(null, iuc.getPlayer().getPosition(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
    		}
    		else
    		{
    			iuc.getWorld().setBlockState(torchPos, Blocks.TORCH.getDefaultState());
    			iuc.getWorld().playSound(null, iuc.getPlayer().getPosition(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
    		}
    		return ActionResultType.SUCCESS;
    	}
    	return ActionResultType.FAIL;
    }
    
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_stars.line1").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_stars.line2").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_stars.line3").applyTextStyle(TextFormatting.YELLOW)));
	}
}
