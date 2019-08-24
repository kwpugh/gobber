package com.kwpugh.gobber2.items.staffs;

import java.util.ArrayList;
import java.util.List;

import com.kwpugh.gobber2.lists.BlockList;
import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CoralBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.MelonBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomStaffFarmer extends Item
{

	public ItemCustomStaffFarmer(Properties properties)
	{
		super(properties);
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		Block block;
		boolean maxAge;
		
		if (!world.isRemote)
		{
			List<BlockPos> poslist = new ArrayList<BlockPos>();

			for (int x = -12; x <= 12; x++)
			{
				for (int y = -1; y <= 1; y++)
				{
					for (int z = -12; z <= 12; z++)
					{
						BlockPos pos = player.getPosition().add(x, y, z);
						block = world.getBlockState(pos).getBlock();
						
						if(block instanceof CropsBlock ||
								block instanceof CactusBlock ||
								block instanceof SugarCaneBlock ||
								block instanceof BambooBlock ||
								block instanceof MelonBlock ||
								block instanceof PumpkinBlock)
						{
							poslist.add(player.getPosition().add(x, y, z));
						}
					}
				}
			}

			if (!poslist.isEmpty())
			{
				for (int i = 0; i <= poslist.size() - 1; i++)
				{
					BlockPos targetPos = poslist.get(i);
					BlockState state = world.getBlockState(targetPos);
					block = world.getBlockState(targetPos).getBlock();	
					BlockState defaultState = block.getDefaultState();
					
					//These plants are simply broken with drops
					if(block instanceof CactusBlock ||
							block instanceof SugarCaneBlock ||
							block instanceof BambooBlock ||
							block instanceof MelonBlock ||
							block instanceof PumpkinBlock)
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
							world.setBlockState(targetPos, defaultState);	
						}
					}
					
					//Gobber plants are checked separately because they do not have natural drops (intentionally) and Globettes need to be spawned manually
					if(block == BlockList.gobber2_plant || 
							block == BlockList.gobber2_plant_nether || 
							block == BlockList.gobber2_plant_end)
					{
						maxAge = state.get(((CropsBlock) block).getAgeProperty()) >= ((CropsBlock) block).getMaxAge();
						
						if(maxAge)
						{
							if(block == BlockList.gobber2_plant)
							{
								world.destroyBlock(targetPos, true);
								world.setBlockState(targetPos, defaultState);
								world.addEntity(new ItemEntity(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), new ItemStack(ItemList.gobber2_globette, 1)));
							}
							
							if(block == BlockList.gobber2_plant_nether)
							{
								world.destroyBlock(targetPos, true);
								world.setBlockState(targetPos, defaultState);
								world.addEntity(new ItemEntity(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), new ItemStack(ItemList.gobber2_globette_nether, 1)));
							}
							
							if(block == BlockList.gobber2_plant_end)
							{
								world.destroyBlock(targetPos, true);
								world.setBlockState(targetPos, defaultState);
								world.addEntity(new ItemEntity(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), new ItemStack(ItemList.gobber2_globette_end, 1)));
							}	
						}
					}
				}
			}
			
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	public void inventoryTick(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        
    	if(!(entity instanceof PlayerEntity) || world.isRemote)
        {
            return;
        }

    	PlayerEntity player = (PlayerEntity)entity;
        ItemStack equipped = player.getHeldItemMainhand();
        
        if(stack == equipped)
        {
            int range = 7;
            for(int x = -range; x < range+1; x++)
            {
                for(int z = -range; z < range+1; z++)
                {
                    for(int y = -range; y < range+1; y++)
                    {
                        int theX = MathHelper.floor(player.posX+x);
                        int theY = MathHelper.floor(player.posY+y);
                        int theZ = MathHelper.floor(player.posZ+z);
                        
                        BlockPos tagetPos = new BlockPos(theX, theY, theZ);
                        
                        BlockState blockstate = world.getBlockState(tagetPos);

                        //Check if blocks implement IGrowable, then use grow()
                        if (blockstate.getBlock() instanceof IGrowable)
                        {
                        	IGrowable igrowable = (IGrowable)blockstate.getBlock();
                            
                        	//Exclude these ones
                            if((igrowable == Blocks.GRASS_BLOCK) ||
                            		(igrowable == Blocks.TALL_GRASS) ||
                            		(igrowable == Blocks.GRASS) ||
                            		(igrowable == Blocks.SUNFLOWER) || 
                            		(igrowable == Blocks.LILAC) || 
                            		(igrowable == Blocks.ROSE_BUSH) || 
                            		(igrowable == Blocks.PEONY) || 
                            		(igrowable == Blocks.SEAGRASS) ||
                            		(igrowable == Blocks.TALL_SEAGRASS))
                            {
                            	continue;
                            }
                            if (igrowable.canGrow(world, tagetPos, blockstate, world.isRemote))
                            {
                                {
                                	if (!world.isRemote)
                                    {
                                		 if (player.ticksExisted % 60 == 0) {
                                			 igrowable.grow(world, world.rand, tagetPos, blockstate);
                                		 }
                                    }
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
                        			blockstate.tick(world, tagetPos, world.rand);
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
                        			blockstate.tick(world, tagetPos, world.rand);
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
		list.add(new StringTextComponent(TextFormatting.BLUE + "Combines the Ring of the Farmer and the Staff of the Harvest"));
		list.add(new StringTextComponent(TextFormatting.GREEN +"Area of effect: 12x2x12"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to use"));
	} 
}
