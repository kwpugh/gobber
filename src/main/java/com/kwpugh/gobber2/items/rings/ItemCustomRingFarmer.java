package com.kwpugh.gobber2.items.rings;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CoralBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.VineBlock;
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

public class ItemCustomRingFarmer extends Item
{

	public ItemCustomRingFarmer(Properties properties)
	{
		super(properties);
	}

	
	//New
	public void func_225534_a_(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_)
	{
		this.func_225534_a_(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
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
                                              
                        //For basic growing blocks that use tick()
                        if ((blockstate.getBlock() instanceof CropsBlock) ||
                        		(blockstate.getBlock() instanceof SaplingBlock)) 
                        {
                        	if (!world.isRemote)
                    		{
                        		if (player.ticksExisted % 12 == 0)
                        		{
                        			//blockstate.tick(world, targetPos, world.rand);
                        			blockstate.getBlock().func_225533_a_(blockstate, world, targetPos, player, null, null);
                       		 	}                                                               
                    		}
                        }
                        
                        //For slower growing blocks that use tick()
                        if ((blockstate.getBlock() instanceof VineBlock) ||                     		               
                        		(blockstate.getBlock() instanceof SugarCaneBlock) ||
                        		(blockstate.getBlock() instanceof NetherWartBlock) ||
                        		(blockstate.getBlock() instanceof CactusBlock))
                        {
                        	if (!world.isRemote)
                    		{
                        		if (player.ticksExisted % 5 == 0)
                        		{
                        			//blockstate.tick(world, targetPos, world.rand);
                       		 	}                                                               
                    		}
                        }
                        	
                        //For faster growing blocks that use tick()
                        if ((blockstate.getBlock() instanceof BambooBlock) ||                         		
                        		(blockstate.getBlock() instanceof CoralBlock) ||		
                        		(blockstate.getBlock() instanceof CocoaBlock) ||  
                        		(blockstate.getBlock() instanceof ChorusFlowerBlock) )
                        {
                        	if (!world.isRemote)
                    		{
                        		if (player.ticksExisted % 60 == 0)
                        		{
                        			//blockstate.tick(world, targetPos, world.rand);
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
		list.add(new StringTextComponent(TextFormatting.BLUE + "Works on most crops, plants, and trees"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Range: 14 blocks"));
	}  
}