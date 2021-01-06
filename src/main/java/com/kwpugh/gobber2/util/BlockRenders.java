package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.init.BlockInit;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BlockRenders
{
	public static void defineRenders()
	{
		RenderType cutoutMipped = RenderType.getCutoutMipped();
		RenderType translucent = RenderType.getTranslucent();
		
		RenderTypeLookup.setRenderLayer(BlockInit.GOBBER2_PLANT.get(), cutoutMipped);
		RenderTypeLookup.setRenderLayer(BlockInit.GOBBER2_PLANT_NETHER.get(), cutoutMipped);
		RenderTypeLookup.setRenderLayer(BlockInit.GOBBER2_PLANT_END.get(), cutoutMipped);	
		RenderTypeLookup.setRenderLayer(BlockInit.GOBBER2_GLASS.get(), translucent);	
		RenderTypeLookup.setRenderLayer(BlockInit.GOBBER2_GLASS_NETHER.get(), translucent);	
		RenderTypeLookup.setRenderLayer(BlockInit.GOBBER2_GLASS_END.get(), translucent);
		RenderTypeLookup.setRenderLayer(BlockInit.CLEAR_GLASS.get(), translucent);
	}	
}