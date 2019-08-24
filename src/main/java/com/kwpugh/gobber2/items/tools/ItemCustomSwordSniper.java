package com.kwpugh.gobber2.items.tools;

import java.util.List;

import com.kwpugh.gobber2.lists.ItemList;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomSwordSniper extends SwordItem
{
	public ItemCustomSwordSniper(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		if(!world.isRemote)
		{
		    if(player.isSneaking())
		    {
		        EnableUtil.changeEnabled(player, hand);
		        player.sendMessage(new StringTextComponent("Sniper ability active: " + EnableUtil.isEnabled(stack)));
		    }
		    
		    if(EnableUtil.isEnabled(stack))
			{
	            ArrowItem itemarrow = (ArrowItem)Items.ARROW;
	            AbstractArrowEntity entityarrow = itemarrow.createArrow(world, new ItemStack(Items.ARROW), player);
	            float arrowVelocity = 60.0F;
	            entityarrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, arrowVelocity, 1.0F);
	            entityarrow.setDamage(1);
	            world.addEntity(entityarrow);	 	
			}	
		    else
		    {
		    	player.addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 2400, (int) 4));
		    }
		    return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
		}
		return super.onItemRightClick(world, player, hand);
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		stack.setDamage(0);  //no damage to sword
        
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.setDamage(0);
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
		return repair.getItem() == ItemList.gobber2_ingot_end;
	}
	
    @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "An unbreakable sword with a special ability"));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Right-click to fire arrows"));
		list.add(new StringTextComponent(TextFormatting.RED + "Sniper ability active: " + EnableUtil.isEnabled(stack)));
		list.add(new StringTextComponent(TextFormatting.GOLD + "Sneak right-click to toggle ability on/off"));
		list.add(new StringTextComponent(TextFormatting.YELLOW + "Arrow supply: unlimited"));
	} 
}