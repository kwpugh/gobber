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
			output.setDisplayName(new StringTextComponent("Improved Gobber Pickaxe"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_SWORD.get()) && right.getItem().equals(Items.NETHER_STAR))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_SWORD.get());
			output.addEnchantment(Enchantments.SHARPNESS, 3);
			output.addEnchantment(Enchantments.KNOCKBACK, 1);
			output.setDisplayName(new StringTextComponent("Deadly Gobber Sword"));
			event.setCost(10);
			event.setOutput(output);
		}
	}
}
