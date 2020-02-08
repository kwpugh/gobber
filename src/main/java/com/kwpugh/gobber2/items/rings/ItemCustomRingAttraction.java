package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.util.EnableUtil;
import com.kwpugh.gobber2.util.MagnetRange;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingAttraction extends Item
{

	public ItemCustomRingAttraction(Properties properties)
	{
		super(properties);
	}
	
	int range;

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{	
		if(entity instanceof PlayerEntity && !world.isRemote && EnableUtil.isEnabled(stack))
		{
			PlayerEntity player = (PlayerEntity)entity;
			
			boolean init = MagnetRange.getCurrentlySet(stack);
			
			if(!init)
			{
				range = 0;
			}
			else
			{
				range = MagnetRange.getCurrentRange(stack);
			}			

			double x = player.getPosX();
			double y = player.getPosY();
			double z = player.getPosZ();

			//Scan for and collect items
			List<ItemEntity> items = entity.world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
			for(ItemEntity e: items)
			{
				if(!player.isCrouching() && !e.getPersistentData().getBoolean("PreventRemoteMovement"))
				{						
					double factor = 0.035;
					e.addVelocity((x - e.getPosX()) * factor, (y - e.getPosY()+1.25) * factor, (z - e.getPosZ()) * factor);
				}
			}

			//Scan for and collect XP Orbs
			List<ExperienceOrbEntity> xp = entity.world.getEntitiesWithinAABB(ExperienceOrbEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
			for(ExperienceOrbEntity orb: xp)
			{
				if(!player.isCrouching())
				{						
					double factor = 0.035;
					orb.addVelocity((x - orb.getPosX()) * factor, (y - orb.getPosY()+1.25) * factor, (z - orb.getPosZ()) * factor);
                    //player.onItemPickup(orb, 1);
                    //player.giveExperiencePoints(orb.xpValue);
                    //orb.remove();
				}
			}
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
		ItemStack stack = player.getHeldItem(hand);	
		
		if(!world.isRemote && !(player.isCrouching()))
        {
            EnableUtil.changeEnabled(player, hand);
            player.sendMessage(new StringTextComponent("Attraction ability active: " + EnableUtil.isEnabled(stack)));
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }		
		
        if(!world.isRemote && player.isCrouching())
        {
			if(range == 0)
			{
				range = 4;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage(new StringTextComponent("Attraction range set to: " + MagnetRange.getCurrentRange(stack)));
			}
			else if(range == 4)
			{
				range = 8;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage(new StringTextComponent("Attraction range set to: " + MagnetRange.getCurrentRange(stack)));
			}
			else if(range == 8)
			{
				range = 12;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage(new StringTextComponent("Attraction range set to: " + MagnetRange.getCurrentRange(stack)));
			}
			else if(range == 12)
			{
				range = 0;
				MagnetRange.setCurrentRange(stack, range);
				player.sendMessage(new StringTextComponent("Attraction range set to: " + MagnetRange.getCurrentRange(stack)));
			}
        }
        
        return super.onItemRightClick(world, player, hand);
    }
    
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return EnableUtil.isEnabled(stack);
	}
	  
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "A magnet that draws dropped items toward the player"));
		list.add(new StringTextComponent(TextFormatting.RED + "Attraction ability active: " + EnableUtil.isEnabled(stack)));
		list.add(new StringTextComponent(TextFormatting.GOLD + "Works while in player inventory"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to toggle on/off, sneak + right-click to cycle through ranges"));
	}   

}
