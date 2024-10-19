package de.silentesc.veinminer.data;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VeinMinerBlocks {
    private static final List<Block> logBlocks = new ArrayList<>(
            Arrays.asList(
                    Blocks.ACACIA_LOG,
                    Blocks.BIRCH_LOG,
                    Blocks.CHERRY_LOG,
                    Blocks.JUNGLE_LOG,
                    Blocks.OAK_LOG,
                    Blocks.DARK_OAK_LOG,
                    Blocks.MANGROVE_LOG,
                    Blocks.SPRUCE_LOG
            )
    );

    private static final List<Block> oreBlocks = new ArrayList<>(
            Arrays.asList(
                    Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE,
                    Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE,
                    Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
                    Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
                    Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE,
                    Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE,
                    Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE,
                    Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE,
                    Blocks.NETHER_QUARTZ_ORE,
                    Blocks.NETHER_GOLD_ORE
            )
    );

    public static List<Block> getLogBlocks() {
        return logBlocks;
    }

    public static List<Block> getOreBlocks() {
        return oreBlocks;
    }
}
