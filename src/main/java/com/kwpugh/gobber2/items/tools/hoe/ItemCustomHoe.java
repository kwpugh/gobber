package com.kwpugh.gobber2.items.tools.hoe;

//import com.kwpugh.gobber2.init.ItemInit;
//import com.kwpugh.gobber2.lists.ToolMaterialList;
//
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.HoeItem;
//import net.minecraft.item.IItemTier;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.ActionResultType;
//import net.minecraft.util.Hand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class ItemCustomHoe extends HoeItem
//{	
//public ItemCustomHoe()
//
//
//
//	@Override
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
//    {
//        if (!worldIn.isRemote)
//        {
//        	//TBD
//        }
//        return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
//    }
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
//	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn)
//	{
//		stack.getOrCreateTag().putBoolean("Unbreakable", true);
//	}
//	
//	@Override
//	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
//	{
//		return true;
//	}
//}
