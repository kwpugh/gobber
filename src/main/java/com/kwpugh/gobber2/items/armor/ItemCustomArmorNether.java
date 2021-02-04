package com.kwpugh.gobber2.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
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

public class ItemCustomArmorNether extends ArmorItem
{
	public ItemCustomArmorNether(IArmorMaterial materialIn, EquipmentSlotType slots, Properties builder)
	{
		super(materialIn, slots, builder);
	}
	
	boolean enablePerks = GobberConfigBuilder.ENABLE_GOBBER_NETHER_ARMOR_HEALTH_PERKS.get();
	
	@Override
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		if(!enablePerks) return;
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
	    ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);	
	 
	    //Full Set
    	if(head.getItem() == ItemInit.GOBBER2_HELMET_NETHER.get() && 
    			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER.get() && 
    			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER.get() && 
    			feet.getItem() == ItemInit.GOBBER2_BOOTS_NETHER.get())
    	{
			if(player.getActivePotionEffect(Effects.POISON) != null)
			{
				player.removePotionEffect(Effects.POISON);
			}
			
			if(player.getActivePotionEffect(Effects.WITHER) != null)
			{
				player.removePotionEffect(Effects.WITHER);
			}
    	}	

    	//Check ArmorUtil for additional perks applied to armor   	
	    //Helmet
	    if(head.getItem() == ItemInit.GOBBER2_HELMET_NETHER.get())
		{
			PlayerSpecialAbilities.giveYellowHearts(world, player, stack, 4, 0.33F);
			PlayerSpecialAbilities.giveRegenEffect(world, player, stack, 1, 0.05F);			
		}
//		else
//		{
//			PlayerSpecialAbilities.giveNoExtraHearts(world, player, stack);
//		}
	    
	    //Chestplate
	    if(chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER.get())
		{
	 		//player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(40.0D); 
		}		
	    
	    //Leggings
	    if(legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER.get())
		{
			
		}
		else
		{
			
		}		
	    
	    //Boots
	    if(feet.getItem() == ItemInit.GOBBER2_BOOTS_NETHER.get())
		{
			
	    }
		else
	    {
			
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
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_nether.line1").mergeStyle(TextFormatting.AQUA)));
		
		if(enablePerks)
		{
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_nether.line2").mergeStyle(TextFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_nether.line3").mergeStyle(TextFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_nether.line4").mergeStyle(TextFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_nether.line5").mergeStyle(TextFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor_nether.line6").mergeStyle(TextFormatting.GOLD)));	
		}
	}
}