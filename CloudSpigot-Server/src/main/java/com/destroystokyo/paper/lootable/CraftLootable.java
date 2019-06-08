package com.destroystokyo.paper.lootable;

import com.destroystokyo.paper.loottable.Lootable;

import net.minecraft.server.World;

interface CraftLootable extends Lootable {

    World getNMSWorld();

    default org.bukkit.World getBukkitWorld() {
        return getNMSWorld().getWorld();
    }
}
