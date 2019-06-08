package com.destroystokyo.paper.lootable;

import net.minecraft.server.BlockPosition;
import net.minecraft.server.TileEntityLootable;
import net.minecraft.server.World;
import org.bukkit.Chunk;
import org.bukkit.block.Block;

import com.destroystokyo.paper.loottable.LootableBlockInventory;
import com.destroystokyo.paper.loottable.LootableInventory;

public interface CraftLootableBlockInventory extends LootableBlockInventory, CraftLootableInventory {

    TileEntityLootable getTileEntity();

    @Override
    default LootableInventory getAPILootableInventory() {
        return this;
    }

    @Override
    default World getNMSWorld() {
        return getTileEntity().getWorld();
    }

    default Block getBlock() {
        final BlockPosition position = getTileEntity().getPosition();
        final Chunk bukkitChunk = getTileEntity().getWorld().getChunkAtWorldCoords(position).bukkitChunk;
        return bukkitChunk.getBlock(position.getX(), position.getY(), position.getZ());
    }

    @Override
    default CraftLootableInventoryData getLootableData() {
        return getTileEntity().getLootableData();
    }
}
