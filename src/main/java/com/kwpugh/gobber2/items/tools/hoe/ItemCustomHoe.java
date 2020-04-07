package com.kwpugh.gobber2.items.tools.hoe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCustomHoe extends HoeItem
{
	public ItemCustomHoe(IItemTier tier, float attackSpeedIn, Properties builder)
	{
		super(tier, attackSpeedIn, builder);
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		//Nothing at the moment
	}	

	@Override   //Taken from HoeItem to override taking damage on use
	public ActionResultType onItemUse(ItemUseContext context)
	{
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
		
		if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
		
		if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up()))
		{
			BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
		
			if (blockstate != null) {
				PlayerEntity playerentity = context.getPlayer();
				world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			
				if (!world.isRemote)
				{
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

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
       if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F)
       {
          stack.damageItem(0, entityLiving, (p_220038_0_) -> {
             p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
          });
       }
       return true;
    }

}
