package com.kwpugh.gobber2.items.tools;

import java.util.List;

import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomHoeEnd extends HoeItem
{
	public ItemCustomHoeEnd(IItemTier tier, float attackDamage, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamage, builder);
	}
	
	/**
	    * Called when this item is used when targetting a Block
	    * Overridden to set damage to 0
	    */
	@Override   
	public ActionResultType onItemUse(ItemUseContext context) {
	      World world = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
	      if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
	      if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
	         BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
	         if (blockstate != null) {
	            PlayerEntity playerentity = context.getPlayer();
	            world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
	            if (!world.isRemote) {
	               world.setBlockState(blockpos, blockstate, 11);
	               if (playerentity != null) {
	                  context.getItem().damageItem(0, playerentity, (p_220043_1_) -> {
	                     p_220043_1_.sendBreakAnimation(context.getHand());
	                  });
	               }
	            }

	            return ActionResultType.SUCCESS;
	         }
	      }

	      return ActionResultType.PASS;
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
	public int getBurnTime(ItemStack itemStack)
	{
		return 400;
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
    
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemList.gobber2_ingot_end;
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "An unbreakable hoe"));
	} 
}
