package com.kwpugh.gobber2.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomArmorDragon extends ArmorItem
{
	public ItemCustomArmorDragon(IArmorMaterial materialIn, EquipmentSlotType slots, Properties builder)
	{
		super(materialIn, slots, builder);
	}	
	  
	@Override
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		//Full Set Bonus
		if(!player.getPersistentData().contains("wearingFullDragonArmor"))player.getPersistentData().putBoolean("wearingFullDragonArmor", false);
			
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);	
	
		setDamage(head, 0);
		setDamage(chest, 0);
		setDamage(legs, 0);
		setDamage(feet, 0);
	
		boolean iswearingFullDragonArmor = head != null && head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON.get() && 
				chest != null && chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON.get() &&
				legs != null && legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON.get() && 
				feet != null && feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON.get();
	
		boolean wasWearingDragonArmorLastTick = player.getPersistentData().getBoolean("wearingFullDragonArmor");
	  
		if(!iswearingFullDragonArmor && wasWearingDragonArmorLastTick && !player.isCreative())
		{
			player.abilities.allowFlying = false;
			player.abilities.isFlying = false;
		}
		else if((iswearingFullDragonArmor) && (world.getDimensionKey().equals(World.OVERWORLD) || world.getDimensionKey().equals(World.THE_NETHER) || world.getDimensionKey().equals(World.THE_END)))
		{
			player.abilities.allowFlying = true;
		}
		player.getPersistentData().putBoolean("wearingFullDragonArmor", iswearingFullDragonArmor);
	
		if(iswearingFullDragonArmor)
		{
			//Additional full set bonuses			
			if(player.getActivePotionEffect(Effects.BLINDNESS) != null)
			{
				player.removePotionEffect(Effects.BLINDNESS);
			}
	
			if(player.getActivePotionEffect(Effects.SLOWNESS) != null)
			{
				player.removePotionEffect(Effects.SLOWNESS);
			}
			
			if(player.getActivePotionEffect(Effects.MINING_FATIGUE) != null)
			{
				player.removePotionEffect(Effects.MINING_FATIGUE);
			}
			
			if(player.getActivePotionEffect(Effects.INSTANT_DAMAGE) != null)
			{
				player.removePotionEffect(Effects.INSTANT_DAMAGE);
			}
			
			if(player.getActivePotionEffect(Effects.NAUSEA) != null)
			{
				player.removePotionEffect(Effects.NAUSEA);
			}
			
			if(player.getActivePotionEffect(Effects.HUNGER) != null)
			{
				player.removePotionEffect(Effects.HUNGER);
			}
			
			if(player.getActivePotionEffect(Effects.POISON) != null)
			{
				player.removePotionEffect(Effects.POISON);
			}
			
			if(player.getActivePotionEffect(Effects.WITHER) != null)
			{
				player.removePotionEffect(Effects.WITHER);
			}
			
			if(player.getActivePotionEffect(Effects.LEVITATION) != null)
			{
				player.removePotionEffect(Effects.LEVITATION);
			}
			
			if(player.getActivePotionEffect(Effects.UNLUCK) != null)
			{
				player.removePotionEffect(Effects.UNLUCK);
			}
			
			if(player.getActivePotionEffect(Effects.WEAKNESS) != null)
			{
				player.removePotionEffect(Effects.WEAKNESS);
			}
		} 
	
		//Check ArmorUtil for additional perks applied to armor
	
		//Helmet
		if(head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON.get())
		{
			PlayerSpecialAbilities.giveYellowHearts(world, player, stack, 20, 0.66F);
			
			PlayerSpecialAbilities.giveRegenEffect(world, player, stack, 1, 0.15F);			
		}
		else
		{
			PlayerSpecialAbilities.giveNoExtraHearts(world, player, stack);
		}
	  	  
		//Chestplate
		if(chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON.get())
		{				
			//something
		}		
		  
		//Leggings
		if(legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON.get())
		{
	  	PlayerSpecialAbilities.giveConduitEffect(world, player, stack);
		}
		else
		{
			//something
		}		
	  	  
		//Boots
		if(feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON.get())
		{
			PlayerSpecialAbilities.giveDolphinEffect(world, player, stack);
		}
		else
		{
			//something
		}		
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.GOBBER2_ARMOR_REPAIR.get();
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_dragon.line1").mergeStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_dragon.line2").mergeStyle(TextFormatting.GOLD)));
	}
}
