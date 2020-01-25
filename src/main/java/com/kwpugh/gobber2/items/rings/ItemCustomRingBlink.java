package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.util.RayTraceUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingBlink extends Item
{
	public ItemCustomRingBlink(Properties properties)
	{
		super(properties);
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote)
		{						
			RayTraceResult pos = RayTraceUtil.getNearestPositionWithAir(world, player, 100);
            if(pos != null && (pos.getType() == RayTraceResult.Type.BLOCK || player.rotationPitch >= -5))
            {
            	int side = pos.getType().ordinal();
                if(side != -1)
                {
                    double x = pos.getHitVec().x-(side == 4 ? 0.5 : 0)+(side == 5 ? 0.5 : 0);
                    double y = pos.getHitVec().y-(side == 0 ? 2.0 : 0)+(side == 1 ? 0.5 : 0);
                    double z = pos.getHitVec().z-(side == 2 ? 0.5 : 0)+(side == 3 ? 0.5 : 0);
	           		
	           		player.stopRiding();
	           		((ServerPlayerEntity)player).connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);

                    world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);

                    return ActionResult.func_226248_a_(stack);
                }
            }		 
        }

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Teleports player to the location looking"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to use"));
		list.add(new StringTextComponent(TextFormatting.YELLOW + "Range limit: 100 blocks"));
	} 

}
