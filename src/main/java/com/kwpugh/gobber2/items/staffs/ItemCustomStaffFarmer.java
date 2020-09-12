package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.util.GrowingUtil;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.MelonBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomStaffFarmer extends Item
{
	int radius = GobberConfigBuilder.STAFF_FARMER_RADIUS.get();
	int baseTickDelay = GobberConfigBuilder.STAFF_FARMER_TICK_DELAY.get();
	boolean staffFarmerReplant = GobberConfigBuilder.STAFF_FARMER_REPLANT.get();
	
	public ItemCustomStaffFarmer(Properties properties)
	{
		super(properties);
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {      
    	if(!(entity instanceof PlayerEntity) || world.isRemote)
        {
            return;
        }

    	PlayerEntity player = (PlayerEntity)entity;
        ItemStack equippedMain = player.getHeldItemMainhand();
        
        if(stack == equippedMain)
        {
        	if (!world.isRemote)
        	{  
        		GrowingUtil.growCrops(world, player, baseTickDelay, radius);
        	}
        }
    }

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		boolean maxAge;
		
		if (!world.isRemote)
		{
			BlockPos playerPos = new BlockPos(player.getPositionVec());
    		
			for (BlockPos targetPos : BlockPos.getAllInBoxMutable(playerPos.add(-radius, -2, -radius), playerPos.add(radius, 3, radius)))
    		{
				Block block = world.getBlockState(targetPos).getBlock();
				BlockState state = world.getBlockState(targetPos);
				BlockState defaultState = block.getDefaultState();
				
				//These plants are simply broken with drops
				if(block instanceof CocoaBlock ||
						block instanceof MelonBlock ||
						block instanceof PumpkinBlock ||
						block instanceof CactusBlock ||
						block instanceof SugarCaneBlock ||
						block instanceof NetherWartBlock ||
						block instanceof BambooBlock)
				{
					world.destroyBlock(targetPos, true);
				}
				
				//Crops are harvested, if at max age, and re-planted
				if(block instanceof CropsBlock)
				{
					maxAge = state.get(((CropsBlock) block).getAgeProperty()) >= ((CropsBlock) block).getMaxAge();
					
					if(maxAge)
					{
						world.destroyBlock(targetPos, true);
						
						if(staffFarmerReplant)
						{
							world.setBlockState(targetPos, defaultState);	
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
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_farmer.line1").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_farmer.line3").mergeStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_farmer.line4").mergeStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_farmer.line2", radius).mergeStyle(TextFormatting.LIGHT_PURPLE)));
	}
}
