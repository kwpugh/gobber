package com.kwpugh.gobber2.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RayTraceUtil
{
	public static RayTraceResult getNearestPositionWithAir(World world, PlayerEntity player, int reach)
    {
        return getMovingObjectPosWithReachDistance(world, player, reach, false, false, true);
    }

    private static RayTraceResult getMovingObjectPosWithReachDistance(World world, PlayerEntity player, double distance, boolean p1, boolean p2, boolean p3)
    {
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        double d0 = player.getPosX();
        double d1 = player.getPosY() + (double) player.getEyeHeight();
        double d2 = player.getPosZ();
        Vec3d vec3 = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3d vec31 = vec3.add((double) f6 * distance, (double) f5 * distance, (double) f7 * distance);
        
        return world.rayTraceBlocks(new RayTraceContext(vec3, vec31, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, player));
    }
}