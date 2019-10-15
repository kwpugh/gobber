package com.kwpugh.gobber2.util;

/*
 * Neurodr0me's - Hammer code copy-pasted from Practical Tools: https://www.curseforge.com/minecraft/mc-mods/practical-tools
 * 
 * All credit goes to Neurodr0me, couldn't figure it out on my own!
 * 
 * Maybe some day.
 */

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HammerUtil
{
    public static final Random random = new Random();

    public static void attemptBreakNeighbors(World world, BlockPos pos, PlayerEntity player, Set<Block> effectiveOn, Set<Material> effectiveMaterials)
    {    	
    	RayTraceResult trace = calcRayTrace(world, player, RayTraceContext.FluidMode.ANY);

        if (trace.getType() == RayTraceResult.Type.BLOCK)
        {
            BlockRayTraceResult blockTrace = (BlockRayTraceResult) trace;
            Direction face = blockTrace.getFace();

            for (int a = -1; a <= 1; a++)
            {
                for (int b = -1; b <= 1; b++)
                {
                    if (a == 0 && b == 0) continue;

                    BlockPos target = null;

                    if (face == Direction.UP    || face == Direction.DOWN)  target = pos.add(a, 0, b);
                    if (face == Direction.NORTH || face == Direction.SOUTH) target = pos.add(a, b, 0);
                    if (face == Direction.EAST  || face == Direction.WEST)  target = pos.add(0, a, b);

                    attemptBreak(world, target, player, effectiveOn, effectiveMaterials);
                }
            }
        }
    }

    public static void attemptBreak(World world, BlockPos pos, PlayerEntity player, Set<Block> effectiveOn, Set<Material> effectiveMaterials)
    {

        BlockState state = world.getBlockState(pos);
        
        boolean isEffective = (effectiveOn.contains(state.getBlock()) || effectiveMaterials.contains(state.getMaterial()));
        
        boolean witherImmune = BlockTags.WITHER_IMMUNE.contains(state.getBlock());
        
        
        if(isEffective && !witherImmune)	
        {
        	world.destroyBlock(pos, false);  //true or false?
	    	Block.spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());
        }
    }
    	
	public static RayTraceResult calcRayTrace(World worldIn, PlayerEntity player, RayTraceContext.FluidMode fluidMode)
	{
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        Vec3d vec3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue() + 1;;
        Vec3d vec3d1 = vec3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
    }
}