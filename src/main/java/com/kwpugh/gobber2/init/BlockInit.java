package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.blocks.BlockDefender;
import com.kwpugh.gobber2.blocks.BlockEndBlock;
import com.kwpugh.gobber2.blocks.BlockGobberBlock;
import com.kwpugh.gobber2.blocks.BlockGobberGlass;
import com.kwpugh.gobber2.blocks.BlockGobberGlassEnd;
import com.kwpugh.gobber2.blocks.BlockGobberGlassNether;
import com.kwpugh.gobber2.blocks.BlockGobberPlant;
import com.kwpugh.gobber2.blocks.BlockHealer;
import com.kwpugh.gobber2.blocks.BlockLooter;
import com.kwpugh.gobber2.blocks.BlockMaturator;
import com.kwpugh.gobber2.blocks.BlockNetherBlock;
import com.kwpugh.gobber2.blocks.BlockOreEnd;
import com.kwpugh.gobber2.blocks.BlockOreGobber;
import com.kwpugh.gobber2.blocks.BlockOreNether;
import com.kwpugh.gobber2.blocks.BlockProtector;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Gobber2.modid);

	public static final RegistryObject<Block> GOBBER2_ORE = BLOCKS.register("gobber2_ore", () -> new BlockOreGobber(Block.Properties.create(Material.ROCK, MaterialColor.IRON).hardnessAndResistance(5.0F, 5.0F).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.STONE)));
	public static final RegistryObject<Block> GOBBER2_ORE_NETHER = BLOCKS.register("gobber2_ore_nether", () -> new BlockOreNether(Block.Properties.create(Material.ROCK, MaterialColor.IRON).hardnessAndResistance(5.0F, 5.0F).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(4).sound(SoundType.STONE)));
	public static final RegistryObject<Block> GOBBER2_ORE_END = BLOCKS.register("gobber2_ore_end", () -> new BlockOreEnd(Block.Properties.create(Material.ROCK, MaterialColor.IRON).hardnessAndResistance(5.0F, 5.0F).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(5).sound(SoundType.STONE)));
	
	public static final RegistryObject<Block> GOBBER2_BLOCK = BLOCKS.register("gobber2_block", () -> new BlockGobberBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> GOBBER2_BLOCK_NETHER = BLOCKS.register("gobber2_block_nether", () -> new BlockNetherBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> GOBBER2_BLOCK_END = BLOCKS.register("gobber2_block_end", () -> new BlockEndBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<Block> GOBBER2_LUCKY_BLOCK = BLOCKS.register("gobber2_lucky_block", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)));
	
	public static final RegistryObject<Block> GOBBER2_GLASS = BLOCKS.register("gobber2_glass", () -> new BlockGobberGlass(Block.Properties.create(Material.GLASS, MaterialColor.STONE).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> GOBBER2_GLASS_NETHER = BLOCKS.register("gobber2_glass_nether", () -> new BlockGobberGlassNether(Block.Properties.create(Material.GLASS, MaterialColor.STONE).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> GOBBER2_GLASS_END = BLOCKS.register("gobber2_glass_end", () -> new BlockGobberGlassEnd(Block.Properties.create(Material.GLASS, MaterialColor.STONE).hardnessAndResistance(2.0F, 2.0F).sound(SoundType.GLASS)));
	
	public static final RegistryObject<Block> GOBBER2_PLANT = BLOCKS.register("gobber2_plant", () -> new BlockGobberPlant("gobber2_plant", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f, 0.0f).sound(SoundType.CROP)));
	public static final RegistryObject<Block> GOBBER2_PLANT_NETHER = BLOCKS.register("gobber2_plant_nether", () -> new BlockGobberPlant("gobber2_plant_nether", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f, 0.0f).sound(SoundType.CROP)));
	public static final RegistryObject<Block> GOBBER2_PLANT_END = BLOCKS.register("gobber2_plant_end", () -> new BlockGobberPlant("gobber2_plant_end", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f, 0.0f).sound(SoundType.CROP)));
	
	public static final RegistryObject<Block> BLOCK_HEALER = BLOCKS.register("block_healer", () -> new BlockHealer(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> BLOCK_PROTECTOR = BLOCKS.register("block_protector", () -> new BlockProtector(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> BLOCK_DEFENDER = BLOCKS.register("block_defender", () -> new BlockDefender(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> BLOCK_LOOTER = BLOCKS.register("block_looter", () -> new BlockLooter(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> BLOCK_MATURATOR = BLOCKS.register("block_maturator", () -> new BlockMaturator(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.METAL)));	
}
