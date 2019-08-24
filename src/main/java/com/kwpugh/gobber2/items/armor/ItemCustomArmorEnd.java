package com.kwpugh.gobber2.items.armor;

import java.util.List;

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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomArmorEnd extends ArmorItem
{
	public ItemCustomArmorEnd(IArmorMaterial materialIn, EquipmentSlotType slots, Properties builder)
	{
		super(materialIn, slots, builder);
	}
	
	@Override
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
	    ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);	
	 
	    setDamage(head, 0);
	    setDamage(chest, 0);
	    setDamage(legs, 0);
	    setDamage(feet, 0);
	    
	    //Full Set
    	if(head.getItem() == ItemList.gobber2_helmet_end && 
    			chest.getItem() == ItemList.gobber2_chestplate_end &&
    			legs.getItem() == ItemList.gobber2_leggings_end && 
    			feet.getItem() == ItemList.gobber2_boots_end)
    	{
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
    		
//			int currentDim = player.dimension.getId();
//		
//			if(currentDim == 1) 
//	    	{
//	       		player.abilities.allowFlying = true;
//	    	}
//	       	else
//	       	{
//	       		player.abilities.allowFlying = false;
//	       		player.abilities.isFlying = false;
//	       		player.sendPlayerAbilities();
//	       	}
    	}	

	    //Helmet
	    if(head.getItem() == ItemList.gobber2_helmet_end)
		{
			SpecialAbilities.giveExtraHearts(world, player, stack);
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
	 		if(player.isInWater())
	 		{
	 			SpecialAbilities.giveConduitEffect(world, player, chest);
	 		}
	 					
	 		player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(40.0D); 
		 }		

	    //Leggings
	    if(legs.getItem() == ItemList.gobber2_leggings_end)
		{
			
		}
		else
		{
			
		}		
	    
	    //Boots
	    if(feet.getItem() == ItemList.gobber2_boots_end)
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
		return repair.getItem() == ItemList.gobber2_armor_repair;
	}
    
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.AQUA + "Repair with Armor Plate"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Helmet: Rapid auto-feeding with saturation and aborption hearts"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Chestplate: Conduit Power in water, fire protection, and knockback resistance"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Leggings: No fall damage"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Boots: TBD"));
		list.add(new StringTextComponent(TextFormatting.GOLD + "Full suit bonus: Negative effect protection and flight only in the End"));
	}
}
