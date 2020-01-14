package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

/*
 * Credit to Codenamerevy
 */

public class ItemCustomRingReturn extends Item
{
	private static int duration = 25;
	
    public ItemCustomRingReturn(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity)
    {
        if(!world.isRemote())
        {
            PlayerEntity player = (ServerPlayerEntity) entity;
            BlockPos currentPos = player.getPosition();

            if(world.getDimension().getType() != DimensionType.OVERWORLD)
            {              
                teleportToBed(player);
                player.sendStatusMessage(new TranslationTextComponent("Welcome Home!"), true);
            }

            BlockPos bedLoc = player.getBedLocation(player.dimension);

            if (bedLoc == null)
            {   
                player.sendStatusMessage(new TranslationTextComponent("Sleep in a bed first"), true);
                return stack;
            }
            if (entity.getRidingEntity() != null) {
                entity.stopRiding();
            }
            setPositionAndUpdate(entity, world, bedLoc);
            player.sendStatusMessage(new TranslationTextComponent("Welcome Home!"), true);
        }
        return stack;
    }
    
    @Override
    public int getUseDuration(ItemStack stack)
    {
        return duration;
    }
    
    public void teleportToBed(PlayerEntity player)
    {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

        DimensionType prevDim = player.dimension;
        DimensionType destDim = prevDim == player.dimension ? DimensionType.OVERWORLD : player.dimension;

        serverPlayer.teleport(player.getServer().getWorld(destDim), serverPlayer.serverPosX, serverPlayer.serverPosY, serverPlayer.serverPosZ, serverPlayer.rotationYaw, serverPlayer.rotationPitch);
    }

    public void setPositionAndUpdate(LivingEntity entity, World world, BlockPos bedLoc)
    {
        entity.setPositionAndUpdate(bedLoc.getX() + 0.5F, bedLoc.getY() + 0.6F, bedLoc.getZ() + 0.5F);
        entity.fallDistance = 0;
    }
    
    @Override
    public UseAction getUseAction (ItemStack stack)
    {
        return UseAction.BOW;
    } 
 
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Right-click and hold to return to bed location"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Must sleep in a bed first to set bed spawn"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Works across dimensions"));
	}  
}