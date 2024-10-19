package de.silentesc.veinminer.events;

import de.silentesc.veinminer.classes.VeinMiner;
import de.silentesc.veinminer.classes.VeinMinerBlock;
import de.silentesc.veinminer.data.VeinMinerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class PlayerBlockBreakEvent {
    /*
     * Returns false to cancel event
     */
    public static boolean onPlayerBlockBreakEventBefore(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        Block block = state.getBlock();
        ItemStack mainHandItemStack = player.getMainHandStack();

        // Checks
        if (world.isClient())
            return true;
        if (player.isCreative())
            return true;
        if (!(mainHandItemStack.getItem() instanceof ToolItem mainHandToolItem))
            return true;
        if (!(mainHandToolItem instanceof AxeItem && VeinMinerBlocks.getLogBlocks().contains(block)) && !(mainHandToolItem instanceof PickaxeItem && VeinMinerBlocks.getOreBlocks().contains(block)))
            return true;
        if (!mainHandToolItem.isCorrectForDrops(mainHandItemStack, state))
            return true;

        VeinMinerBlock originalBlock = new VeinMinerBlock(pos, state, blockEntity);
        List<Block> blocksToMine = VeinMinerBlocks.getLogBlocks().contains(state.getBlock()) ? VeinMinerBlocks.getLogBlocks() : VeinMinerBlocks.getOreBlocks();
        VeinMiner veinMiner = new VeinMiner(blocksToMine, originalBlock, world, player);

        veinMiner.startVeinMiner();

        return false;
    }
}
