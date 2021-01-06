package com.kwpugh.gobber2.items.tools.hoe;

//import com.kwpugh.gobber2.init.ItemInit;
//
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.item.HoeItem;
//import net.minecraft.item.IItemTier;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class ItemCustomHoeNether extends HoeItem
//{
//public ItemCustomHoeNether(IItemTier p_i231595_1_, int p_i231595_2_, float p_i231595_3_, Properties p_i231595_4_)
//	{
//		super(p_i231595_1_, p_i231595_2_, p_i231595_3_, p_i231595_4_);
//	}
//
////	public ItemCustomHoeNether(IItemTier tier, float attackSpeedIn, Properties builder)
////	{
////		super(tier, attackSpeedIn, builder);
////	}
//	
//	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
//	{		
//		//Nothing at the moment
//	}	
//
//	@Override
//    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
//    {
//		stack.setDamage(0);  //no damage
//        
//        return true;
//    }
//
//    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
//    {
//        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
//        {
//            stack.setDamage(0);
//        }
//        return true;
//    }
//	
//	@Override
//	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
//	{
//		return true;
//	}
//	
//	@Override
//	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
//	{
//		return repair.getItem() == ItemInit.GOBBER2_INGOT_NETHER.get();
//	}
//}