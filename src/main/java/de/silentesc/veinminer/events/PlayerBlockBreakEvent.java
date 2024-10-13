package de.silentesc.veinminer.events;

import de.silentesc.veinminer.VeinMiner;
import de.silentesc.veinminer.data.TreeCapitatorBlocks;
import de.silentesc.veinminer.data.VeinMinerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class PlayerBlockBreakEvent {
    public static boolean onPlayerBlockBreakEventBefore(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        // Return if code is running on client
        if (world.isClient()) {
            return true;
        }

        Block block = state.getBlock();

        // Tree Capitator
        if (TreeCapitatorBlocks.getLogBlocks().contains(block)) {
            return false;
        }

        // Vein Miner
        if (VeinMinerBlocks.getOreBlocks().contains(block)) {
            List<ItemStack> droppedItems =  Block.getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, player, player.getMainHandStack());

            

            for (ItemStack itemStack : droppedItems) {
                VeinMiner.LOGGER.info("Dropped Item: {} x{}", itemStack.getItem().getName().getString(), itemStack.getCount());
                // player.giveItemStack(itemStack);
            }
            // world.removeBlockEntity(pos);

            // TODO
            // Check if tool is ok
            // Get item & xp drops for tool (Fortune? Silk Touch?)
            // Break block
            // Search blocks around and break if fitting
            return false;
        }

        return true;
    }
}
