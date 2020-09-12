package com.kwpugh.gobber2.items.tools.sword;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomSwordSniper extends SwordItem
{
	public ItemCustomSwordSniper(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	int swordSniperCooldown = GobberConfigBuilder.SNIPER_SWORD_COOLDOWN.get();
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		player.getCooldownTracker().setCooldown(this, swordSniperCooldown);
		
		if(!world.isRemote)
		{
		    if(player.isSneaking())
		    {
		        EnableUtil.changeEnabled(player, hand);
		        player.sendStatusMessage(new TranslationTextComponent("item.gobber2.gobber2_sword_sniper.line4", EnableUtil.isEnabled(stack)).mergeStyle(TextFormatting.RED), true);
		        
		    }
		    
		    if(EnableUtil.isEnabled(stack))
			{
	            ArrowItem itemarrow = (ArrowItem)Items.ARROW;
	            AbstractArrowEntity entityarrow = itemarrow.createArrow(world, new ItemStack(Items.ARROW), player);
	            float arrowVelocity = 60.0F;
	            entityarrow.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, arrowVelocity, 1.0F);
	            entityarrow.setDamage(1);
	            world.addEntity(entityarrow);
	            entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
			}	
		    else
		    {
		    	//TBD
		    }
		    return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
		}
		return super.onItemRightClick(world, player, hand);
	}

	@Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		stack.setDamage(0);  //no damage
        
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
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn)
	{
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_sword_sniper.line1").mergeStyle(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_sword_sniper.line2").mergeStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_sword_sniper.line3").mergeStyle(TextFormatting.YELLOW)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_sword_sniper.line4", EnableUtil.isEnabled(stack)).mergeStyle(TextFormatting.RED)));
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_sword_sniper.line5").mergeStyle(TextFormatting.LIGHT_PURPLE)));
	}  
}