package com.kwpugh.gobber2.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockGobberGlassWitherproof extends GlassBlock
{

	public BlockGobberGlassWitherproof(Properties properties)
	{
		super(properties);
	}


	public static final VoxelShape GLASS_NOT_SOLID_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.00D, 0.0D, 0.0D, 0.0D);
	public static final VoxelShape GLASS_SOLID_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);	
	
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{			
		if((context.getEntity() instanceof PlayerEntity) && (context.getEntity().isSneaking()))
		{
			return GLASS_SOLID_AABB;
		}
		
		if(context.getEntity() instanceof SpiderEntity)
		{
			SpiderEntity spider = (SpiderEntity) context.getEntity();
			
			((SpiderEntity) spider).setBesideClimbableBlock(false);
			return GLASS_SOLID_AABB;
		}
		
		if(context.getEntity() instanceof PlayerEntity)       
		{
			return GLASS_NOT_SOLID_AABB;
		}
		else     
		{
			return GLASS_SOLID_AABB;
		}  
    }

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		if((context.getEntity() instanceof PlayerEntity) && (context.getEntity().isSneaking()))
		{
			return GLASS_SOLID_AABB;
		}
		
		if(context.getEntity() instanceof PlayerEntity)
		{
			return GLASS_NOT_SOLID_AABB;
		}
		else
		{
			return GLASS_SOLID_AABB;
		}
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		//TBD
	}
	
    
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag)
	{
		super.addInformation(stack, world, tooltip, flag);				
		tooltip.add(new StringTextComponent(TextFormatting.BLUE + "A very sturdy glass block, drops the block when broken"));
		tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Players can walk through, mobs cannot"));
		tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Player can sneak to break or walk on glass"));
		tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Spiders cannot climb the glass"));
		tooltip.add(new StringTextComponent(TextFormatting.RED + "WARNING: NOT WITHER PROOF YET!"));
	}
}
