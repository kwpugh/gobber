package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Based upon code created by Shadows of Fire
 * and my original code from 1.12.2 version of Gobber
 */

public class ItemCustomStaffEnsnarement extends Item
{
	public ItemCustomStaffEnsnarement(Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity)
	{
      if (entity instanceof PlayerEntity ||
        		!entity.isNonBoss() ||
        		!entity.isAlive())
        	return false;
        	      
        if (stack.getOrCreateChildTag("mob_data").isEmpty())
		{
			CompoundNBT tag = entity.serializeNBT();
			if (!player.world.isRemote)
			{
				entity.remove();
				stack.getTag().put("mob_data", tag);
				stack.getTag().putString("name", entity.getDisplayName().getFormattedText());
			}
			return true;
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext iuc)
	{
		CompoundNBT tag = iuc.getItem().getOrCreateChildTag("mob_data");
		if (!tag.isEmpty())
		{
			BlockPos pos = iuc.getPos().offset(iuc.getFace());
			if (!iuc.getWorld().isRemote)
			{
				Entity entity = EntityType.func_220335_a(tag, iuc.getWorld(), a -> a);
				if (entity != null)
				{
					entity.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
					((ServerWorld) iuc.getWorld()).addEntityIfNotDuplicate(entity);
					iuc.getItem().getTag().remove("mob_data");

					if (iuc.getItem().attemptDamageItem(1, iuc.getWorld().rand, (ServerPlayerEntity) iuc.getPlayer()))
					{
						iuc.getItem().shrink(1);
					}
					return ActionResultType.SUCCESS;
				}
			}
		}
		return ActionResultType.FAIL;
	}

	@Override
	public CompoundNBT getShareTag(ItemStack stack)
	{
		CompoundNBT tag = super.getShareTag(stack);
		CompoundNBT mob = new CompoundNBT();
		
		if (tag == null)
		{
			return null;
		}
		
		tag = tag.copy();
		
		if (tag.getCompound("mob_data").contains("id"))
		{
			mob.putString("id", tag.getCompound("mob_data").getString("id"));
		}

		tag.put("mob_data", mob);
		
		return tag;
	}

	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return stack.hasTag() && !stack.getOrCreateChildTag("mob_data").isEmpty();
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		list.add(new StringTextComponent(TextFormatting.BLUE + "Captures and stores many mobs and animals"));
		list.add(new StringTextComponent(TextFormatting.BLUE + "Does not work on Players or Boss mobs"));
		
		if (stack.hasTag())
		{
			CompoundNBT tag = stack.getOrCreateChildTag("mob_data");

			if (tag.isEmpty())
			{
				list.add(new StringTextComponent(TextFormatting.GOLD + "Contains: empty"));
			}
			else
			{
				list.add(new StringTextComponent(TextFormatting.RED + "Contains: " + stack.getTag().getString("name")));
			}
		}
	}



}