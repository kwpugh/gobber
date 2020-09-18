package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.blocks.tileentities.BlockDefenderTile;
import com.kwpugh.gobber2.blocks.tileentities.BlockHealerTile;
import com.kwpugh.gobber2.blocks.tileentities.BlockLooterTile;
import com.kwpugh.gobber2.blocks.tileentities.BlockMaturatorTile;
import com.kwpugh.gobber2.blocks.tileentities.BlockProtectorTile;

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

	public static final RegistryObject<TileEntityType<BlockHealerTile>> BLOCK_HEALER = TILE_ENTITY_TYPES.register("block_healer", () ->
	TileEntityType.Builder.create(BlockHealerTile::new, BlockInit.BLOCK_HEALER.get())
			.build(null)
	);

	public static final RegistryObject<TileEntityType<BlockProtectorTile>> BLOCK_PROTECTOR = TILE_ENTITY_TYPES.register("block_protector", () ->
	TileEntityType.Builder.create(BlockProtectorTile::new, BlockInit.BLOCK_PROTECTOR.get())
			.build(null)
	);
	
	public static final RegistryObject<TileEntityType<BlockDefenderTile>> BLOCK_DEFENDER = TILE_ENTITY_TYPES.register("block_defender", () ->
	TileEntityType.Builder.create(BlockDefenderTile::new, BlockInit.BLOCK_DEFENDER.get())
			.build(null)
	);
	
	public static final RegistryObject<TileEntityType<BlockLooterTile>> BLOCK_LOOTER = TILE_ENTITY_TYPES.register("block_looter", () ->
	TileEntityType.Builder.create(BlockLooterTile::new, BlockInit.BLOCK_LOOTER.get())
			.build(null)
	);
}