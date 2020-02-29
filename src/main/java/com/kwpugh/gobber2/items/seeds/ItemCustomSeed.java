package com.kwpugh.gobber2.items.seeds;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCustomSeed extends BlockNamedItem
{

	public ItemCustomSeed(String name, Block crop, Properties builder)
	{
        super(crop, builder);

        this.setRegistryName("gobber2:gobber2_seed");       
    }
	
    public List<ModelResourceLocation> getVariants() {
        return Lists.newArrayList(new ModelResourceLocation("gobber2:gobber2_seed", "inventory"));
    }
    
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_seed.line1").applyTextStyle(TextFormatting.GREEN)));
	}  
}
