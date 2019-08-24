package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomStaffSniper extends Item
{
	public ItemCustomStaffSniper(Properties properties)
	{
		super(properties);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        if (!worldIn.isRemote)
        {
            ArrowItem itemarrow = (ArrowItem)Items.ARROW;
            AbstractArrowEntity entityarrow = itemarrow.createArrow(worldIn, new ItemStack(Items.ARROW), playerIn);
            float arrowVelocity = 60.0F;
            entityarrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, arrowVelocity, 1.0F);
            entityarrow.setDamage(1);
            worldIn.addEntity(entityarrow);
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Fires very precise arrows at high velocity"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click in player main hand"));
		list.add(new StringTextComponent(TextFormatting.YELLOW + "Arrow supply: Unlimited"));
	} 
}
