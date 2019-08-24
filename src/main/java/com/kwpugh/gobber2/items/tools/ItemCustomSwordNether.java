package com.kwpugh.gobber2.items.tools;

import java.util.List;

import com.kwpugh.gobber2.lists.ItemList;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomSwordNether extends SwordItem
{
	public ItemCustomSwordNether(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        if (!worldIn.isRemote)
        {
        	playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 2400, (int) 4));
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }

	@Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		stack.setDamage(1);
        
        if(target instanceof WitherSkeletonEntity)
        {
        	target.remove();
        	target.entityDropItem(Items.WITHER_SKELETON_SKULL, 1);
        }
        return true;
    }
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
    
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemList.gobber2_ingot_nether;
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click for extra strength"));
	} 
}
