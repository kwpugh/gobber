package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.blocks.tileentities.BlockMaturatorTile;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class TileInit 
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Gobber2.modid);

	// We don't have a datafixer for our TileEntities, so we pass null into build.
	public static final RegistryObject<TileEntityType<BlockMaturatorTile>> BLOCK_MATURATOR = TILE_ENTITY_TYPES.register("block_maturator", () ->
			TileEntityType.Builder.create(BlockMaturatorTile::new, BlockInit.BLOCK_MATURATOR.get())
					.build(null)
	);

}