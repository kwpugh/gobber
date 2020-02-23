package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.lists.BlockList;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BlockRenders
{
	public static void defineRenders()
	{
		RenderType cutoutMipped = RenderType.getCutoutMipped();
		RenderType translucent = RenderType.getTranslucent();
		
		RenderTypeLookup.setRenderLayer(BlockList.gobber2_plant, cutoutMipped);
		RenderTypeLookup.setRenderLayer(BlockList.gobber2_plant_nether, cutoutMipped);
		RenderTypeLookup.setRenderLayer(BlockList.gobber2_plant_end, cutoutMipped);	
		RenderTypeLookup.setRenderLayer(BlockList.gobber2_glass, translucent);	
		RenderTypeLookup.setRenderLayer(BlockList.gobber2_glass_nether, translucent);	
		RenderTypeLookup.setRenderLayer(BlockList.gobber2_glass_end, translucent);
	}	
}