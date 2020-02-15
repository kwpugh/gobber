package com.kwpugh.gobber2.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.lists.ItemList;

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
		//Chest Bonus
		if(!player.getPersistentData().contains("wearingDragonChestplate"))player.getPersistentData().putBoolean("wearingDragonChestplate", false);
			
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
	
		setDamage(chest, 0);
    
		boolean isWearingDragonChestplate = chest != null && chest.getItem() == ItemList.gobber2_chestplate_dragon;
    
		boolean wasWearingChestplateLastTick = player.getPersistentData().getBoolean("wearingDragonChestplate");
        
		if(!isWearingDragonChestplate && wasWearingChestplateLastTick && !player.isCreative())
		{
			player.abilities.allowFlying = false;
			player.abilities.isFlying = false;
		}
		else if(isWearingDragonChestplate)
		{
			player.abilities.allowFlying = true;
		}
		player.getPersistentData().putBoolean("wearingDragonChestplate", isWearingDragonChestplate);
    
		if(isWearingDragonChestplate)
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
			
			player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(40.0D); 
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
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_chestplate_dragon.line1").applyTextStyle(TextFormatting.GOLD)));
	}
}
