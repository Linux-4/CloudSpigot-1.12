package com.destroystokyo.paper.lootable;

import net.minecraft.server.World;
import org.bukkit.entity.Entity;

import com.destroystokyo.paper.loottable.LootableEntityInventory;
import com.destroystokyo.paper.loottable.LootableInventory;

public interface CraftLootableEntityInventory extends LootableEntityInventory, CraftLootableInventory {

    net.minecraft.server.Entity getHandle();

    @Override
    default LootableInventory getAPILootableInventory() {
        return this;
    }

    default Entity getEntity() {
        return getHandle().getBukkitEntity();
    }

    @Override
    default World getNMSWorld() {
        return getHandle().getWorld();
    }

    @Override
    default CraftLootableInventoryData getLootableData() {
        if (getHandle() instanceof CraftLootableInventory) {
            return ((CraftLootableInventory) getHandle()).getLootableData();
        }
        return null;
    }
}
