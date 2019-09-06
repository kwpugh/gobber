package com.kwpugh.gobber2.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockGobberGlassWitherproof extends Block
{
	private boolean witherproof = false;
    private boolean transparent = false;

	public BlockGobberGlassWitherproof(Block.Properties p_i49994_1_)
	{
		super(p_i49994_1_);
	}

//	public static final VoxelShape GLASS_NOT_SOLID_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.00D, 0.0D, 0.0D, 0.0D);
//	
//	public static final VoxelShape GLASS_SOLID_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);	
//	
//	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
//	{	
//		return GLASS_SOLID_AABB;
//    }
//
//	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
//	{
//		return GLASS_SOLID_AABB;
//	}
	
    public BlockGobberGlassWitherproof setTransparent(boolean transparent) {

        this.transparent = transparent;

        return this;
    }
    
    public BlockGobberGlassWitherproof setWitherproof(boolean witherproof) {

        this.witherproof = witherproof;
        return this;
    }

    @Override
    public boolean canEntityDestroy(BlockState state, IBlockReader world, BlockPos pos, Entity entity) {
        if (witherproof)
            return !(entity instanceof WitherEntity) && super.canEntityDestroy(state, world, pos, entity);

        return super.canEntityDestroy(state, world, pos, entity);
    }
    
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag)
	{
		super.addInformation(stack, world, tooltip, flag);				
		tooltip.add(new StringTextComponent(TextFormatting.BLUE + "A very sturdy glass block, right-click with Nether Hoe to drop"));
		tooltip.add(new StringTextComponent(TextFormatting.RED + "WARNING: NOT WITHER PROOF YET!"));
	}
}
