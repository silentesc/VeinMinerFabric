package de.silentesc.veinminer.data;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeCapitatorBlocks {
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

    public static List<Block> getLogBlocks() {
        return logBlocks;
    }
}
