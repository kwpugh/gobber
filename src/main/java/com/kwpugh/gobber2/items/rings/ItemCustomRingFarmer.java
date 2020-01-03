package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SaplingBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/**
 * 
 * Only effective on Crops, Saplings, and Bamboo until I find an alternative to blockstate.tick() 
 * 
 */

public class ItemCustomRingFarmer extends Item
{

	public ItemCustomRingFarmer(Properties properties)
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
        ItemStack equippedOff = player.getHeldItemOffhand();
        
        if(stack == equippedMain || stack == equippedOff)
        {
            int range = 7;
            for(int x = -range; x < range+1; x++)
            {
                for(int z = -range; z < range+1; z++)
                {
                    for(int y = -range; y < range+1; y++)
                    {
                        int theX = MathHelper.floor(player.func_226277_ct_()+x);
                        int theY = MathHelper.floor(player.func_226278_cu_()+y);
                        int theZ = MathHelper.floor(player.func_226281_cx_()+z);
                        
                        BlockPos targetPos = new BlockPos(theX, theY, theZ);                       
                        BlockState blockstate = world.getBlockState(targetPos);

                        if ((blockstate.getBlock() instanceof CropsBlock) ||
                        		(blockstate.getBlock() instanceof SaplingBlock) ||                    
                        		(blockstate.getBlock() instanceof BambooBlock) ||
                        		(blockstate.getBlock() instanceof BambooSaplingBlock)) 
                        {
                            IGrowable igrowable = (IGrowable)blockstate.getBlock();
                            if ((igrowable.canGrow(world, targetPos, blockstate, world.isRemote)) && (player.ticksExisted % 120 == 0))
                            {
                               if (world instanceof ServerWorld)
                               {
                                  if (igrowable.canUseBonemeal(world, world.rand, targetPos, blockstate))
                                  {
                                     igrowable.func_225535_a_((ServerWorld)world, world.rand, targetPos, blockstate);
                                  }
                               }
                            }
                        }
                    }
                }
            }
       }
    }
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Works on many crops, plants, and trees"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Range: 14 blocks"));
	}  
}