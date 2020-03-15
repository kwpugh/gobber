package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomRingTeleport extends Item
{

	public ItemCustomRingTeleport(Properties properties)
	{
		super(properties);
	}

	
//	//NEW CODE - NOT READY FOR PRIMETIME
//	
//	//Set the location in the ring
//	public ActionResultType onItemUse(ItemUseContext context)
//	{
//		MinecraftServer serverWorld = context.getWorld().getServer();
//		World world = context.getWorld();
//		 
//		BlockPos pos = context.getPos();
//		PlayerEntity player = context.getPlayer();
//		
//		Direction direction = context.getFace();
//		ItemStack stackRing = context.getPlayer().getHeldItemMainhand();
//		 
//		if(getPosition(stackRing) == null && player.isShiftKeyDown())
//		{
//			setPosition(stackRing, serverWorld, world, pos.offset(direction), player);
//			player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line1").applyTextStyle(TextFormatting.GREEN)));
//	 
//			return ActionResultType.SUCCESS;
//		}
// 
//		if(getPosition(stackRing) != null)
//		{
//			player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line2").applyTextStyle(TextFormatting.GREEN)));
//		 
//			return ActionResultType.SUCCESS;
//		}
// 
//		return ActionResultType.PASS;
//	}
//
//	//Set position and dimension in the NBT
//	public static void setPosition(ItemStack stack, MinecraftServer serverWorld, World world, BlockPos pos, PlayerEntity player)
//	{
//		if(!world.isRemote)
//		{
//			return;
//		}
//		
//		CompoundNBT tags;
//		ResourceLocation currentDim = player.dimension.getRegistryName();
//		String dim = currentDim.toString();
//		
//		if (!stack.hasTag())
//		{
//			tags = new CompoundNBT();
//		}
//		else
//		{
//			tags = stack.getTag();
//		}
//	 
//		if (pos == null)
//		{
//			tags.remove("position");
//			tags.remove("dimension");
//		}
//		else
//		{
//			tags.put("position", NBTUtil.writeBlockPos(pos));
//			tags.putString("dimension", dim);
//		}
//		stack.setTag(tags);
//	}
//	
//	//Read player position from NBT tag
//	public static BlockPos getPosition(ItemStack stack)
//	{
//		if (!stack.hasTag())
//		{
//			return null;
//		}		 
//
//		CompoundNBT tags = stack.getTag();
//	 
//		if (tags.contains("position"))
//		{
//			return NBTUtil.readBlockPos((CompoundNBT) tags.get("position"));
//		}
//		return null;
//	}
//	
//	//Read player dimension from NBT tag
//	public static String getDimension(ItemStack stack)
//	{
//		if (!stack.hasTag())
//		{
//			return null;
//		}
//	 
//		CompoundNBT tags = stack.getTag();
//	 
//		if (tags.contains("dimension"))
//		{
//			return tags.getString("dimension");
//		}	
//		return null;
//	}
//	
//
//	
//	@OnlyIn(Dist.CLIENT)
//	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
//	{		
//		super.addInformation(stack, worldIn, tooltip, flagIn);
//		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line9").applyTextStyle(TextFormatting.GREEN)));
//		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line10").applyTextStyle(TextFormatting.GREEN)));
//		
//		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line11").applyTextStyle(TextFormatting.YELLOW)));
//		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line12").applyTextStyle(TextFormatting.YELLOW)));
//		
//		if(getPosition(stack) != null)
//		{
//			BlockPos pos = getPosition(stack);
//			
//			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line13").applyTextStyle(TextFormatting.RED)));
//			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line14", getDimension(stack), pos.getX(), pos.getY(), pos.getZ()).applyTextStyle(TextFormatting.LIGHT_PURPLE)));
//		}
//	}
	
	
	
	
	
		
		
		
	//Set the location in the ring
	public ActionResultType onItemUse(ItemUseContext context)
	{
		 World world = context.getWorld();
		 BlockPos pos = context.getPos();
		 PlayerEntity player = context.getPlayer();
		 Direction direction = context.getFace();
		 ItemStack stackRing = context.getPlayer().getHeldItemMainhand();

		 if(getPosition(stackRing) == null && player.isShiftKeyDown())
		 {
			 setPosition(stackRing, world, pos.offset(direction), player);
			 if(!world.isRemote)
			 {
				 player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line1").applyTextStyle(TextFormatting.GREEN)));		 
			 }
			 return ActionResultType.SUCCESS;
		 }
		 
		 if(getPosition(stackRing) != null)
		 {
			 if(!world.isRemote)
			 {
				 player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line2").applyTextStyle(TextFormatting.GREEN)));	 
			 }			
			 return ActionResultType.SUCCESS;
		 }
		 
		 return ActionResultType.PASS;
	}
	
	//Use the teleport function or clear it	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		if(getPosition(stack) != null && !player.isShiftKeyDown())
		{
			teleport(player, world, stack);
			world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
		}
	 
		if(getPosition(stack) != null && player.isShiftKeyDown())
		{
			setPosition(stack, world, null, player);
			if(!world.isRemote)
			{
				player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line3").applyTextStyle(TextFormatting.GREEN)));	
			}
		}
	 
		return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand)); 
	}

	//Capture player position
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

	//Capture player dimension
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
	
	//Set position and dimension in the NBT
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

	//Teleport
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
		
		if(getDimension(stack) != currentDim)
		{
			if(getDimension(stack) == 0)
			{
				player.changeDimension(DimensionType.OVERWORLD);
				player.setPositionAndUpdate(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);

				if(!world.isRemote)
				{
					player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line4").applyTextStyle(TextFormatting.GREEN)));	
				}
			}
			else if(getDimension(stack) == 1)
			{
				player.changeDimension(DimensionType.THE_END);
				player.setPositionAndUpdate(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);

				if(!world.isRemote)
				{
					player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line5").applyTextStyle(TextFormatting.GREEN)));	
				}
			} 	
			else if(getDimension(stack) == -1)
			{
				player.changeDimension(DimensionType.THE_NETHER);
				player.setPositionAndUpdate(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);

				if(!world.isRemote)
				{
					player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line6").applyTextStyle(TextFormatting.GREEN)));	
				}
			}
			else
			{
				if(!world.isRemote)
				{
					player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line7").applyTextStyle(TextFormatting.GREEN)));
					player.sendMessage((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line8").applyTextStyle(TextFormatting.GREEN)));	
				}
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		String dimName;
		
		switch(getDimension(stack))
		{
		case 1:
			dimName = "End"
;			break;
		case 0:
			dimName = "OverWorld";
			break;
		case -1:
			dimName = "Nether";
			break;
		default:
			dimName = "Unknown";
			break;
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line9").applyTextStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line10").applyTextStyle(TextFormatting.GREEN)));
		
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line11").applyTextStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line12").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		
		if(getPosition(stack) != null)
		{
			BlockPos pos = getPosition(stack);
		 
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line13").applyTextStyle(TextFormatting.RED)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_ring_teleport.line14", dimName, pos.getX(), pos.getY(), pos.getZ()).applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		}
	}
	
	
	
	
}
