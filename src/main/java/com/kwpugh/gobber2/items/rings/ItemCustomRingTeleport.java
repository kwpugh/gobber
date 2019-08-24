package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomRingTeleport extends Item
{

	public ItemCustomRingTeleport(Properties properties)
	{
		super(properties);
	}
	
	 public ActionResultType onItemUse(ItemUseContext context)
	 {
		 World world = context.getWorld();
		 BlockPos pos = context.getPos();
		 PlayerEntity player = context.getPlayer();
		 Direction direction = context.getFace();
		 ItemStack stackRing = context.getPlayer().getHeldItemMainhand();
		 
		 if(getPosition(stackRing) == null && player.isSneaking())
		 {
			 setPosition(stackRing, world, pos.offset(direction), player);
			 player.sendMessage(new StringTextComponent("Location set!"));
			 return ActionResultType.SUCCESS;
		 }
	 
		 if(getPosition(stackRing) != null)
		 {
			 player.sendMessage(new StringTextComponent("Location already set."));
			 return ActionResultType.SUCCESS;
		 }
		 
		 return ActionResultType.PASS;
	}
	

	 
	 public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	 {
		 ItemStack stack = player.getHeldItem(hand);
		  
		 if(getPosition(stack) != null && !player.isSneaking())
	     {
			 teleport(player, world, stack);
			 world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
	     }
		 
		 if(getPosition(stack) != null && player.isSneaking())
		 {
			 setPosition(stack, world, null, player);
			 player.sendMessage(new StringTextComponent("Location cleared!"));
		 }
	 
		 return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand)); 
	 }
	  

	 public void teleport(PlayerEntity player, World world, ItemStack stack)
	 {
		 if(world.isRemote)
		 {
			 return;
		 }
	     int currentDim = player.dimension.getId();  
		 BlockPos pos = getPosition(stack);
		 
		 if(getDimension(stack) == currentDim)
		 {
			 player.setPositionAndUpdate(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
		 }
		 else
		 {
			 player.sendMessage(new StringTextComponent("You are not currently in the stored dimension")); 
		 } 
	 }

	 public static BlockPos getPosition(ItemStack stack)
	 {
		 if (!stack.hasTag())
		 {
			 return null;
		 }		 

		 CompoundNBT tags = stack.getTag();
		 
		 if (tags.contains("pos"))
		 {
			 return NBTUtil.readBlockPos((CompoundNBT) tags.get("pos"));
		 }
		 return null;
	 }

	 public static void setPosition(ItemStack stack, World world, BlockPos pos, PlayerEntity player)
	 {
		 if(world.isRemote)
		 {
			 return;
		 }
		 
		 CompoundNBT tags;
		 
		 if (!stack.hasTag())
		 {
			 tags = new CompoundNBT();
		 }
		 else
		 {
			 tags = stack.getTag();
		 }
		 
		 if (pos == null)
		 {
			 tags.remove("pos");
			 tags.remove("dim");
		 }
		 else
		 {
			 tags.put("pos", NBTUtil.writeBlockPos(pos));
			 tags.putInt("dim", player.dimension.getId());
		 }
		 stack.setTag(tags);
	 }
	 
	 public static int getDimension(ItemStack stack)
	 {
		 if (!stack.hasTag())
		 {
			 return Integer.MAX_VALUE;
		 }
		 
		 CompoundNBT tags = stack.getTag();
		 
		 if (tags.contains("dim"))
		 {
			 return tags.getInt("dim");
		 }	
		 return Integer.MAX_VALUE;
	 }

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Allows player to teleport to saved location on right-click"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Does not work across dimensions"));
		list.add(new StringTextComponent(TextFormatting.WHITE + "Set: " + TextFormatting.AQUA + "point at a block and sneak + right-click"));
		list.add(new StringTextComponent(TextFormatting.WHITE + "Clear: " + TextFormatting.AQUA + "point in the air and sneak + right-click"));
		
		 if(getPosition(stack) != null)
		 {
			 BlockPos pos = getPosition(stack);
			 
			 list.add(new StringTextComponent(TextFormatting.GOLD + "Location Stored:"));
			 list.add(new StringTextComponent(TextFormatting.YELLOW + "Dim: " + getDimension(stack) + "  X: " + pos.getX() + "  Y: " + pos.getY() + "  Z: " + pos.getZ()));
		 }
	}   
	 
	 
	 
	 
}
