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

public class ItemCustomArmorEnd extends ArmorItem
{
	public ItemCustomArmorEnd(IArmorMaterial materialIn, EquipmentSlotType slots, Properties builder)
	{
		super(materialIn, slots, builder);
	}
		  
	@Override
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		//Full Set Bonus
		if(!player.getPersistentData().contains("wearingFullEndArmor"))player.getPersistentData().putBoolean("wearingFullEndArmor", false);
			
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);	
 
		setDamage(head, 0);
		setDamage(chest, 0);
		setDamage(legs, 0);
		setDamage(feet, 0);
    
		boolean isWearingFullEndArmor = head != null && head.getItem() == ItemList.gobber2_helmet_end && 
				chest != null && chest.getItem() == ItemList.gobber2_chestplate_end &&
				legs != null && legs.getItem() == ItemList.gobber2_leggings_end && 
				feet != null && feet.getItem() == ItemList.gobber2_boots_end;
    
		boolean wasWearingArmorLastTick = player.getPersistentData().getBoolean("wearingFullEndArmor");
        
		if(!isWearingFullEndArmor && wasWearingArmorLastTick && !player.isCreative())
		{
			player.abilities.allowFlying = false;
			player.abilities.isFlying = false;
		}
		else if(isWearingFullEndArmor && player.dimension.getId() == 1)
		{
			player.abilities.allowFlying = true;
		}
		player.getPersistentData().putBoolean("wearingFullEndArmor", isWearingFullEndArmor);
    
		if(isWearingFullEndArmor)
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
	    if(head.getItem() == ItemList.gobber2_helmet_end)
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
	    if(chest.getItem() == ItemList.gobber2_chestplate_end)
		{				
	 		player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(40.0D); 
		 }		

	    
	    
	    //Leggings
	    if(legs.getItem() == ItemList.gobber2_leggings_end)
		{
	    	SpecialAbilities.giveConduitEffect(world, player, stack);
		}
		else
		{
			//something
		}		
	    
	    
	    
	    //Boots
	    if(feet.getItem() == ItemList.gobber2_boots_end)
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
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_end.line1").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_end.line2").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_end.line3").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_end.line4").applyTextStyle(TextFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_end.line5").applyTextStyle(TextFormatting.GOLD)));
	}
}
