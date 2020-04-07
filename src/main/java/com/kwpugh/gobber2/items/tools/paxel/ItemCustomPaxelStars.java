package com.kwpugh.gobber2.items.tools.paxel;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.items.toolbaseclasses.PaxelBase;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants.WorldEvents;

public class ItemCustomPaxelStars extends PaxelBase
{
	public ItemCustomPaxelStars(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn,
			Properties builder)
	{
		super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder);
	}

    @Override
    public ActionResultType onItemUse(ItemUseContext iuc)
    {    	
    	BlockPos torchPos;
    	BlockPos pos = iuc.getPos();
    	//BlockState state = iuc.getWorld().getBlockState(pos);
    	
    	World world = iuc.getWorld();
    	BlockPos blockpos = iuc.getPos();
    	PlayerEntity player = iuc.getPlayer();
    	ItemStack stack = player.getHeldItemMainhand();
    	BlockState blockstate = world.getBlockState(blockpos);
    	BlockState resultToSet = null;
    	Block strippedResult = BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
          
    	if(!EnableUtil.isEnabled(stack))
    	{
    		if (strippedResult != null)
            {
                world.playSound(player, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                resultToSet = strippedResult.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS));
            }
            else
            {
                if (iuc.getFace() == Direction.DOWN)
                {
                    return ActionResultType.PASS;
                }
                
                BlockState foundResult = SHOVEL_LOOKUP.get(blockstate.getBlock());
                
                if (foundResult != null && world.isAirBlock(blockpos.up()))
                {
                    world.playSound(player, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    resultToSet = foundResult;
                }
                else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.get(CampfireBlock.LIT))
                {
                    world.playEvent(null, WorldEvents.FIRE_EXTINGUISH_SOUND, blockpos, 0);
                    resultToSet = blockstate.with(CampfireBlock.LIT, false);
                }
            }
            if (resultToSet == null)
            {
                return ActionResultType.PASS;
            }
            if (!world.isRemote)
            {
                world.setBlockState(blockpos, resultToSet, 11);
                
                if (player != null)
                {
                    iuc.getItem().damageItem(0, player, onBroken -> onBroken.sendBreakAnimation(iuc.getHand()));
                }
            }
            stack.setDamage(0);  //no damage
            
            return ActionResultType.SUCCESS;
    	}
    	
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
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
		ItemStack stack = player.getHeldItem(hand);
		
        if(!world.isRemote && player.isShiftKeyDown())
        {
            EnableUtil.changeEnabled(player, hand);
            player.sendMessage(new TranslationTextComponent("item.gobber2.gobber2_paxel_stars.line4", EnableUtil.isEnabled(stack)).applyTextStyle(TextFormatting.RED));
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }
        return super.onItemRightClick(world, player, hand);
    }

	@Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		stack.setDamage(0);  //no damage
        
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.setDamage(0);
        }
        return true;
    }

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.GOBBER2_INGOT_END.get();
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_paxel_stars.line2").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_paxel_stars.line3").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_paxel_stars.line4", EnableUtil.isEnabled(stack)).applyTextStyle(TextFormatting.RED)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_paxel_stars.line5").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_end.unbreakable").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
	} 
}
