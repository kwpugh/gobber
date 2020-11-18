package com.kwpugh.gobber2.util.handlers;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Gobber2.modid, bus = EventBusSubscriber.Bus.FORGE )
public class AnvilEventHandler
{
	@SubscribeEvent
	public static void upgradeSomeTools(AnvilUpdateEvent event)
	{
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();
		
		if(left.isEmpty() || right.isEmpty())
		{
			return;
		}

		if(left.getItem().equals(ItemInit.GOBBER2_PICKAXE.get()) && right.getItem().equals(Items.EMERALD))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_PICKAXE.get());
			output.addEnchantment(Enchantments.SILK_TOUCH, 1);
			output.addEnchantment(Enchantments.EFFICIENCY, 3);
			output.setDisplayName(new StringTextComponent("Improved Gobber Pickaxe"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_SWORD_NETHER.get()) && right.getItem().equals(Items.NETHER_STAR))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_SWORD_NETHER.get());
			output.addEnchantment(Enchantments.SHARPNESS, 5);
			output.addEnchantment(Enchantments.KNOCKBACK, 2);
			output.setDisplayName(new StringTextComponent("Deadly Nether Sword"));
			event.setCost(10);
			event.setOutput(output);
		}

		if(left.getItem().equals(ItemInit.GOBBER2_HAMMER_END.get()) && right.getItem().equals(ItemInit.DRAGON_STAR.get()))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_HAMMER_END.get());
			output.addEnchantment(Enchantments.EFFICIENCY, 5);
			output.addEnchantment(Enchantments.FORTUNE, 5);
			output.setDisplayName(new StringTextComponent("Awesome End Hammer of Fortune"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_PICKAXE_END.get()) && right.getItem().equals(ItemInit.DRAGON_STAR.get()))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_PICKAXE_END.get());
			output.addEnchantment(Enchantments.EFFICIENCY, 5);
			output.addEnchantment(Enchantments.FORTUNE, 5);
			output.setDisplayName(new StringTextComponent("Awesome End Pickaxe of Fortune"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_SWORD_SNIPER.get()) && right.getItem().equals(ItemInit.DRAGON_STAR.get()))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_SWORD_SNIPER.get());
			output.addEnchantment(Enchantments.SHARPNESS, 10);
			output.addEnchantment(Enchantments.LOOTING, 10);
			output.setDisplayName(new StringTextComponent("Devastating Sniper Sword"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_HELMET_DRAGON.get()) && right.getItem().equals(Items.DRAGON_BREATH))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_HELMET_DRAGON.get());
			output.addEnchantment(Enchantments.THORNS, 5);
			output.addEnchantment(Enchantments.PROTECTION, 5);
			output.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 5);
			output.setDisplayName(new StringTextComponent("Thorny Dragon Helmet"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_CHESTPLATE_DRAGON.get()) && right.getItem().equals(Items.DRAGON_BREATH))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_CHESTPLATE_DRAGON.get());
			output.addEnchantment(Enchantments.THORNS, 5);
			output.addEnchantment(Enchantments.PROTECTION, 5);
			output.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 5);
			output.setDisplayName(new StringTextComponent("Thorny Dragon Chestplate"));
			event.setCost(10);
			event.setOutput(output);
		}
	
		if(left.getItem().equals(ItemInit.GOBBER2_LEGGINGS_DRAGON.get()) && right.getItem().equals(Items.DRAGON_BREATH))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_LEGGINGS_DRAGON.get());
			output.addEnchantment(Enchantments.THORNS, 5);
			output.addEnchantment(Enchantments.PROTECTION, 5);
			output.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 5);
			output.setDisplayName(new StringTextComponent("Thorny Dragon Leggings"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_BOOTS_DRAGON.get()) && right.getItem().equals(Items.DRAGON_BREATH))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_BOOTS_DRAGON.get());
			output.addEnchantment(Enchantments.THORNS, 5);
			output.addEnchantment(Enchantments.PROTECTION, 5);
			output.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 5);
			output.setDisplayName(new StringTextComponent("Thorny Dragon Boots"));
			event.setCost(10);
			event.setOutput(output);
		}
	}
}
