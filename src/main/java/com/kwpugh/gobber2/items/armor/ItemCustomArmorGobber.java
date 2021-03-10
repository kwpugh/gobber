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

public class ItemCustomArmorGobber extends ArmorItem
{
	public ItemCustomArmorGobber(IArmorMaterial materialIn, EquipmentSlotType slots, Properties builder)
	{
		super(materialIn, slots, builder);
	}	
	
	boolean enablePerks = GobberConfigBuilder.ENABLE_GOBBER_ARMOR_HEALTH_PERKS.get();
	int hunger = GobberConfigBuilder.GOBBER_ARMOR_HUNGER.get();
	double saturation = GobberConfigBuilder.GOBBER_ARMOR_SATURATION.get();
	
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		if(!enablePerks) return;
		
		if(player instanceof PlayerEntity)
		{
			ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
			ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
			ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		    ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);
		    
		    //Full Set
	    	if(head.getItem() == ItemInit.GOBBER2_HELMET.get() && 
	    			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE.get() && 
	    			legs.getItem() == ItemInit.GOBBER2_LEGGINGS.get() && 
	    			feet.getItem() == ItemInit.GOBBER2_BOOTS.get())
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
		    if(head.getItem() == ItemInit.GOBBER2_HELMET.get())
			{
				//PlayerSpecialAbilities.giveRegenEffect(world, player, stack, 1, 0.02F);	
		    	PlayerSpecialAbilities.giveRegenEffect(world, player, stack, hunger, (float) saturation);
			}
			else
			{
				//something
			}
		    
		    //Chestplate
		    if(chest.getItem() == ItemInit.GOBBER2_CHESTPLATE.get())
			{
		    	//something
			}
		    
		    //Leggings - No Fall Damage moved to ArmorUtil as event
		    if(legs.getItem() == ItemInit.GOBBER2_LEGGINGS.get())
			{
		    	//something
			}
			else
			{
				//something
			}
		    
		    //Boots
		    if(feet.getItem() == ItemInit.GOBBER2_BOOTS.get())
			{
		    	//something
			}
			else
			{
				//something
			}		    	
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
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor.line1").mergeStyle(TextFormatting.AQUA)));
		
		if(enablePerks)
		{
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor.line2").mergeStyle(TextFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor.line3").mergeStyle(TextFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor.line4").mergeStyle(TextFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor.line5").mergeStyle(TextFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_armor.line6").mergeStyle(TextFormatting.GOLD)));	
		}
	}
}