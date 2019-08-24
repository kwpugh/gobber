package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemCustomRingEnderchest extends Item
{

	public static final TranslationTextComponent field_220115_d = new TranslationTextComponent("container.enderchest");
	
	public ItemCustomRingEnderchest(Properties properties)
	{
		super(properties);
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		
		EnderChestInventory enderChest = player.getInventoryEnderChest();
		
		if (enderChest != null)
		{
			if (!world.isRemote)
			{
				 player.openContainer(new SimpleNamedContainerProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> {
		               return ChestContainer.createGeneric9X3(p_220114_1_, p_220114_2_, enderChest);
		            }, field_220115_d));
			}
		}
		return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
	}
	 
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Provides player access to their Enderchest"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to use"));
	}  

}
