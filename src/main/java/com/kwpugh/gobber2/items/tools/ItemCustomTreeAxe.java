package com.kwpugh.gobber2.items.tools;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/*
 * Adapted from several sources
 * 
 * Credits to original authors:
 * 
 * - astradamus
 * - neurodr0me
 * - DoubleDoorDev
 * - and few others I don't remember
 * 
 * 
 * 
 */
public class ItemCustomTreeAxe extends AxeItem
{   
    public static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE);

    public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.WOOD, Material.GOURD, Material.CACTUS);

    public static final int LOG_BREAK_DELAY = 1;

    public ItemCustomTreeAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
    {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        stack.attemptDamageItem(3, random, null);

        if (entityLiving instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) entityLiving;

            if (!attemptFellTree(world, pos, player))
            {
                attemptBreakNeighbors(world, pos, player, EFFECTIVE_ON, EFFECTIVE_MATERIALS, false);
            }
        }

        return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
    }

    private boolean attemptFellTree(World world, BlockPos pos, PlayerEntity player)
    {

        ArrayList<BlockPos> logs = new ArrayList<>();
        ArrayList<BlockPos> candidates = new ArrayList<>();
        candidates.add(pos);

        int leaves = 0;

        for (int i = 0; i < candidates.size(); i++)
        {
            if (logs.size() > 200) return false; 

            BlockPos candidate = candidates.get(i);
            Block block = world.getBlockState(candidate).getBlock();

            if (BlockTags.LEAVES.contains(block))
            {
                leaves++;
            }
            else if (logs.size() == 0 || BlockTags.LOGS.contains(block))
            {
                logs.add(candidate);

                for (int x = -1; x <= 1; x++)
                {
                    for (int y = 0; y <= 1; y++)
                    { 
                        for (int z = -1; z <= 1; z++)
                        {
                            BlockPos neighbor = candidate.add(x, y, z);
                            if (candidates.contains(neighbor)) continue; 
                            candidates.add(neighbor);
                        }
                    }
                }
            }
        }

        if (logs.size() == 0) return false; 

        if (leaves >= logs.size()/6.0)
        { 
            MinecraftForge.EVENT_BUS.register(new Object()
            {
                int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItemMainhand());
                int silkLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand());

                int delay = LOG_BREAK_DELAY;
                int i = 0;

                @SubscribeEvent
                public void onTick(TickEvent.WorldTickEvent event)
                {
                    if (delay-- > 0) return;
                    delay = LOG_BREAK_DELAY;
                    if (i < logs.size()) {
                        BlockPos log = logs.get(i);
                        attemptBreak(world, log, player, EFFECTIVE_ON, EFFECTIVE_MATERIALS, fortuneLevel, silkLevel, false);
                        i++;
                    }
                    else
                    {
                        MinecraftForge.EVENT_BUS.unregister(this);
                    }
                }
            });

            return true;
        }

        return false;
    }
    

    public static final Random random = new Random();

    public static void attemptBreakNeighbors(World world, BlockPos pos, PlayerEntity player, Set<Block> effectiveOn, Set<Material> effectiveMaterials, boolean checkHarvestLevel)
    {
        world.setBlockState(pos, Blocks.GLASS.getDefaultState());
        RayTraceResult trace = calcRayTrace(world, player, RayTraceContext.FluidMode.ANY);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        if (trace.getType() == RayTraceResult.Type.BLOCK)
        {
            BlockRayTraceResult blockTrace = (BlockRayTraceResult) trace;
            Direction face = blockTrace.getFace();

            int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItemMainhand());
            int silkLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand());

            for (int a = -1; a <= 1; a++)
            {
                for (int b = -1; b <= 1; b++)
                {
                    if (a == 0 && b == 0) continue;

                    BlockPos target = null;

                    if (face == Direction.UP    || face == Direction.DOWN)  target = pos.add(a, 0, b);
                    if (face == Direction.NORTH || face == Direction.SOUTH) target = pos.add(a, b, 0);
                    if (face == Direction.EAST  || face == Direction.WEST)  target = pos.add(0, a, b);

                    attemptBreak(world, target, player, effectiveOn, effectiveMaterials, fortuneLevel, silkLevel, checkHarvestLevel);
                }
            }
        }
    }

    public static void attemptBreak(World world, BlockPos pos, PlayerEntity player, Set<Block> effectiveOn, Set<Material> effectiveMaterials, int fortuneLevel, int silkLevel, boolean checkHarvestLevel)
    {
        BlockState state = world.getBlockState(pos);

        boolean validHarvest = !checkHarvestLevel || player.getHeldItemMainhand().canHarvestBlock(state);
        boolean isEffective = effectiveOn.contains(state.getBlock()) || effectiveMaterials.contains(state.getMaterial());
        boolean witherImmune = BlockTags.WITHER_IMMUNE.contains(state.getBlock());

        if (validHarvest && isEffective && !witherImmune)
        {
            world.destroyBlock(pos, false);
            Block.spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());

            int exp = state.getExpDrop(world, pos, fortuneLevel, silkLevel);
            if (exp > 0)
            {
                state.getBlock().dropXpOnBlockBreak(world, pos, exp);
            }
        }
    }

    public static RayTraceResult calcRayTrace(World worldIn, PlayerEntity player, RayTraceContext.FluidMode fluidMode)
    {
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        Vec3d vec3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();;
        Vec3d vec3d1 = vec3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
    }
    
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.gobber2.gobber2_tree_axe.line1").applyTextStyle(TextFormatting.GREEN)));			
	}
} 