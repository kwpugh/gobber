package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingVoid extends Item
{
	public ItemCustomRingVoid(Properties properties)
	{
		super(properties);
	}
    
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand)
	{
		ItemStack stack = entity.getHeldItem(hand);
		
		//int currentDim = entity.dimension.getId();
		
		if(!world.isRemote)
		{
			  if (world instanceof ServerWorld && !entity.isPassenger() && !entity.isBeingRidden())
			  {
			        RegistryKey<World> registrykey = world.getDimensionKey() == World.THE_END ? World.OVERWORLD : World.THE_END;
			        ServerWorld serverworld = ((ServerWorld)world).getServer().getWorld(registrykey);
			        
			        if (serverworld == null)
			        {
			        	return ActionResult.resultSuccess(stack);
			        }

			        entity.changeDimension(serverworld);		        
			  }	
			  
			  return ActionResult.resultSuccess(stack);
		}
		
		return ActionResult.resultFail(stack);
	}
			  	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line1").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line2").mergeStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line3").mergeStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_void.line4").mergeStyle(TextFormatting.RED)));

	}  
}
