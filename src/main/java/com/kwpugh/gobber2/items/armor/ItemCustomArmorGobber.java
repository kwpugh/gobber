package com.kwpugh.gobber2.items.armor;

import java.util.List;

import com.kwpugh.gobber2.lists.ItemList;
import com.kwpugh.gobber2.util.SpecialAbilities;

import net.minecraft.client.util.ITooltipFlag;
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

public class ItemCustomArmorGobber extends ArmorItem
{
	public ItemCustomArmorGobber(IArmorMaterial materialIn, EquipmentSlotType slots, Properties builder)
	{
		super(materialIn, slots, builder);
	}	
	
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		if(player instanceof PlayerEntity)
		{
			ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
			ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
			ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		    ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);
		    
		    //Full Set
	    	if(head.getItem() == ItemList.gobber2_helmet && 
	    			chest.getItem() == ItemList.gobber2_chestplate && 
	    			legs.getItem() == ItemList.gobber2_leggings && 
	    			feet.getItem() == ItemList.gobber2_boots)
	    	{
				player.removeActivePotionEffect(Effects.POISON);
	    	}	
		    
		    //Helmet
		    if(head.getItem() == ItemList.gobber2_helmet)
			{
				int newfoodlevel = 1;
				float newsatlevel = 0.0F;
				SpecialAbilities.giveRegenffect(world, player, stack, newfoodlevel, newsatlevel);			
			}
			else
			{
				//something
			}
		    
		    //Chestplate
		    if(chest.getItem() == ItemList.gobber2_chestplate)
			{
		 		if(player.isInWater())
		 		{
		 			SpecialAbilities.giveBreathing(world, player, chest);
		 		}
			}
		    
		    //Leggings - No Fall Damage moved to ArmorUtil as event
		    if(legs.getItem() == ItemList.gobber2_leggings)
			{
				
			}
			else
			{
				
			}
		    
		    //Boots
		    if(feet.getItem() == ItemList.gobber2_boots)
			{
		    	
			}
			else
			{
				
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
		return repair.getItem() == ItemList.gobber2_armor_repair;
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.AQUA + "Repair with Armor Plate"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Helmet: Slow auto-feeding"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Chestplate: Underwater breathing"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Leggings: No fall damage"));
		list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Boots: TBD"));
		list.add(new StringTextComponent(TextFormatting.GOLD + "Full suit bonus: Posion protection "));
	}
}
