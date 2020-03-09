package com.kwpugh.gobber2.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.lists.ItemList;
import com.kwpugh.gobber2.util.SpecialAbilities;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
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
	
		boolean iswearingFullDragonArmor = head != null && head.getItem() == ItemList.gobber2_helmet_dragon && 
				chest != null && chest.getItem() == ItemList.gobber2_chestplate_dragon &&
				legs != null && legs.getItem() == ItemList.gobber2_leggings_dragon && 
				feet != null && feet.getItem() == ItemList.gobber2_boots_dragon;
	
		boolean wasWearingDragonArmorLastTick = player.getPersistentData().getBoolean("wearingFullDragonArmor");
	  
		if(!iswearingFullDragonArmor && wasWearingDragonArmorLastTick && !player.isCreative())
		{
			player.abilities.allowFlying = false;
			player.abilities.isFlying = false;
		}
		else if((iswearingFullDragonArmor) && (player.dimension.getId() == -1 || player.dimension.getId() == 0 || player.dimension.getId() == 1 ))
		{
			player.abilities.allowFlying = true;
		}
		player.getPersistentData().putBoolean("wearingFullDragonArmor", iswearingFullDragonArmor);
	
		if(iswearingFullDragonArmor)
		{
			//Additional full set bonuses
			player.removeActivePotionEffect(Effects.BLINDNESS);
			player.removeActivePotionEffect(Effects.SLOWNESS);
			player.removeActivePotionEffect(Effects.MINING_FATIGUE);
			player.removeActivePotionEffect(Effects.INSTANT_DAMAGE);
			player.removeActivePotionEffect(Effects.NAUSEA);
			player.removeActivePotionEffect(Effects.HUNGER);
			player.removeActivePotionEffect(Effects.POISON);
			player.removeActivePotionEffect(Effects.WITHER);
			player.removeActivePotionEffect(Effects.LEVITATION);
			player.removeActivePotionEffect(Effects.UNLUCK);
			player.removeActivePotionEffect(Effects.WEAKNESS);
		} 
	
		//Check ArmorUtil for additional perks applied to armor
	
	  //Helmet
	  if(head.getItem() == ItemList.gobber2_helmet_dragon)
		{
			SpecialAbilities.giveExtraHearts(world, player, stack, 90);
			
			int newfoodlevel = 1;
			float newsatlevel = 0.10F;
			SpecialAbilities.giveRegenffect(world, player, stack, newfoodlevel, newsatlevel);			
		}
		else
		{
			SpecialAbilities.giveNoExtraHearts(world, player, stack);
		}
	  	  
	  //Chestplate
	  if(chest.getItem() == ItemList.gobber2_chestplate_dragon)
		{				
			player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(40.0D); 
		 }		
		  
	  //Leggings
	  if(legs.getItem() == ItemList.gobber2_leggings_dragon)
		{
	  	SpecialAbilities.giveConduitEffect(world, player, stack);
		}
		else
		{
			//something
		}		
	  	  
	  //Boots
	  if(feet.getItem() == ItemList.gobber2_boots_dragon)
		{
	  	SpecialAbilities.giveDolphinEffect(world, player, stack);
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
		return repair.getItem() == ItemList.gobber2_armor_repair;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_dragon.line1").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_dragon.line2").applyTextStyle(TextFormatting.GOLD)));
	}
}
