package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.blocks.BlockDefender;
import com.kwpugh.gobber2.blocks.BlockEndBlock;
import com.kwpugh.gobber2.blocks.BlockGobberBlock;
import com.kwpugh.gobber2.blocks.BlockGobberGlass;
import com.kwpugh.gobber2.blocks.BlockGobberGlassEnd;
import com.kwpugh.gobber2.blocks.BlockGobberGlassNether;
import com.kwpugh.gobber2.blocks.BlockGobberPlant;
import com.kwpugh.gobber2.blocks.BlockGobberPlantEnd;
import com.kwpugh.gobber2.blocks.BlockGobberPlantNether;
import com.kwpugh.gobber2.blocks.BlockHealer;
import com.kwpugh.gobber2.blocks.BlockLooter;
import com.kwpugh.gobber2.blocks.BlockMaturator;
import com.kwpugh.gobber2.blocks.BlockNetherBlock;
import com.kwpugh.gobber2.blocks.BlockOreEnd;
import com.kwpugh.gobber2.blocks.BlockOreGobber;
import com.kwpugh.gobber2.blocks.BlockOreNether;
import com.kwpugh.gobber2.blocks.BlockProtector;
import com.kwpugh.gobber2.lists.BlockList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BlockInit
{
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
				BlockList.gobber2_ore = new BlockOreGobber(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 5.0f).lightValue(2).harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.STONE)).setRegistryName(location("gobber2_ore")),
				BlockList.gobber2_ore_nether = new BlockOreNether(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 5.0f).lightValue(2).harvestTool(ToolType.PICKAXE).harvestLevel(4).sound(SoundType.STONE)).setRegistryName(location("gobber2_ore_nether")),
				BlockList.gobber2_ore_end = new BlockOreEnd(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 5.0f).lightValue(2).harvestTool(ToolType.PICKAXE).harvestLevel(5).sound(SoundType.STONE)).setRegistryName(location("gobber2_ore_end")),
				
				BlockList.gobber2_block = new BlockGobberBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("gobber2_block")),
				BlockList.gobber2_block_nether = new BlockNetherBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("gobber2_block_nether")),
				BlockList.gobber2_block_end = new BlockEndBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("gobber2_block_end")),
				
				BlockList.gobber2_lucky_block = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("gobber2_lucky_block")),
				
				BlockList.gobber2_glass = new BlockGobberGlass(Block.Properties.create(Material.GLASS).doesNotBlockMovement().hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("gobber2_glass")),
				BlockList.gobber2_glass_nether = new BlockGobberGlassNether(Block.Properties.create(Material.GLASS).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("gobber2_glass_nether")),
				BlockList.gobber2_glass_end = new BlockGobberGlassEnd(Block.Properties.create(Material.GLASS).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("gobber2_glass_end")),
		 
				BlockList.gobber2_plant = new BlockGobberPlant("gobber2_plant", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f, 0.0f).lightValue(15).sound(SoundType.CROP)).setRegistryName(location("gobber2_plant")),
				BlockList.gobber2_plant_nether = new BlockGobberPlantNether("gobber2_plant_nether", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f, 0.0f).lightValue(15).sound(SoundType.CROP)).setRegistryName(location("gobber2_plant_nether")),
				BlockList.gobber2_plant_end = new BlockGobberPlantEnd("gobber2_plant_end", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f, 0.0f).lightValue(15).sound(SoundType.CROP)).setRegistryName(location("gobber2_plant_end")),
		
				BlockList.block_healer = new BlockHealer(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("block_healer")),
				BlockList.block_protector = new BlockProtector(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("block_protector")),
				BlockList.block_defender = new BlockDefender(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("block_defender")),
				BlockList.block_looter = new BlockLooter(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("block_looter")),
				BlockList.block_maturator = new BlockMaturator(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("block_maturator"))
			);
			Gobber2.logger.info("Gobber Blocks registered.");
		}
	}
	
	private static ResourceLocation location(String name)
	{
		return new ResourceLocation(Gobber2.modid, name);
	}	
}
