package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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

        if (  (stack.getOrCreateChildTag("mob_data").isEmpty()) &&
        		(entity instanceof AnimalEntity ||
        				entity instanceof HorseEntity ||
        				entity instanceof DonkeyEntity ||
        				entity instanceof LlamaEntity ||
        				entity instanceof MuleEntity ||
        				entity instanceof GolemEntity ||
        				entity instanceof MonsterEntity ||
        				entity instanceof VillagerEntity ||
        				entity instanceof PhantomEntity
        				))
		{
			CompoundNBT tag = entity.serializeNBT();
			if (!player.world.isRemote)
			{
				entity.remove();
				stack.getTag().put("mob_data", tag);
				stack.getTag().putString("name", entity.getDisplayName().getString());
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
				Entity entity = EntityType.loadEntityAndExecute(tag, iuc.getWorld(), a -> a);
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

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_ensnarement.line1").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_ensnarement.line2").mergeStyle(TextFormatting.GREEN)));

		if (stack.hasTag())
		{
			CompoundNBT tag = stack.getOrCreateChildTag("mob_data");

			if (tag.isEmpty())
			{
				tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_ensnarement.line3").mergeStyle(TextFormatting.GREEN)));
			}
			else
			{
				tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_ensnarement.line4", stack.getTag().getString("name")).mergeStyle(TextFormatting.GREEN)));
			}
		}
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_ensnarement.line5").mergeStyle(TextFormatting.AQUA)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_staff_ensnarement.line6").mergeStyle(TextFormatting.YELLOW)));
	}
}