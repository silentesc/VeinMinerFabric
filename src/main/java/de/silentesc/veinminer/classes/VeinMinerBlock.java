package de.silentesc.veinminer.classes;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public record VeinMinerBlock(BlockPos pos, BlockState state, BlockEntity blockEntity) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeinMinerBlock that = (VeinMinerBlock) o;
        return pos.equals(that.pos) && state.equals(that.state) && (Objects.equals(blockEntity, that.blockEntity));
    }
}
