package de.silentesc.veinminer.classes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class VeinMiner {
    private final List<Block> blocksToMine;
    private final VeinMinerBlock originalBlock;
    private final World world;
    private final PlayerEntity player;
    boolean veinMinerRunning = false;

    public VeinMiner(List<Block> blocksToMine, VeinMinerBlock originalBlock, World world, PlayerEntity player) {
        this.blocksToMine = blocksToMine;
        this.originalBlock = originalBlock;
        this.world = world;
        this.player = player;
    }

    public void startVeinMiner() {
        veinMinerRunning = true;

        // Fill the candidates list
        List<VeinMinerBlock> candidates = getCandidates(originalBlock);

        // Mine every block
        for (VeinMinerBlock veinMinerBlock : candidates) {
            mineBlock(veinMinerBlock);
        }
    }

    public void stopVeinMiner() {
        veinMinerRunning = false;
    }

    /*
     * Private utils
     */

    private List<VeinMinerBlock> getCandidates(VeinMinerBlock veinMinerBlock) {
        List<VeinMinerBlock> candidates = new ArrayList<>();
        candidates.add(veinMinerBlock);

        Queue<VeinMinerBlock> queue = new LinkedList<>();
        queue.add(veinMinerBlock);

        while (!queue.isEmpty()) {
            VeinMinerBlock currentBlock = queue.poll();

            // Check all blocks around the current block
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (!veinMinerRunning) return new ArrayList<>();
                        if (x == 0 && y == 0 && z == 0) continue;

                        BlockPos surroundingPos = currentBlock.pos().add(x, y, z);
                        BlockEntity surroundingBlockEntity = world.getBlockEntity(surroundingPos);
                        BlockState surroundingState = world.getBlockState(surroundingPos);

                        // Check if the block is a valid target (log or ore)
                        if (blocksToMine.contains(surroundingState.getBlock()) || blocksToMine.contains(surroundingState.getBlock())) {
                            VeinMinerBlock surroundingVeinMinerBlock = new VeinMinerBlock(surroundingPos, surroundingState, surroundingBlockEntity);

                            // Avoid adding duplicates to the queue
                            if (!candidates.contains(surroundingVeinMinerBlock)) {
                                candidates.add(surroundingVeinMinerBlock);
                                queue.add(surroundingVeinMinerBlock);
                            }
                        }
                    }
                }
            }
        }

        return candidates;
    }

    private void mineBlock(VeinMinerBlock veinMinerBlock) {
        if (!veinMinerRunning) return;

        // Damage player tool (also checks for enchantments like unbreaking. also breaks the tool)
        this.player.getMainHandStack().damage(1, player, EquipmentSlot.MAINHAND);

        // Drop items and xp (works with mending, fortune, silk touch)
        Block.getDroppedStacks(veinMinerBlock.state(), (ServerWorld) world, veinMinerBlock.pos(), veinMinerBlock.blockEntity(), player, player.getMainHandStack())
                .forEach(itemStack -> Block.dropStack(world, player.getBlockPos(), itemStack));
        veinMinerBlock.state().onStacksDropped((ServerWorld) world, player.getBlockPos(), player.getMainHandStack(), true);

        // Break block
        world.removeBlock(veinMinerBlock.pos(), false);

        // Play sound
        world.playSound(null, veinMinerBlock.pos(), SoundEvents.BLOCK_BAMBOO_BREAK, SoundCategory.BLOCKS, 1f, 1f);
    }
}
