package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public class ItemRecord extends Item {

    private static final Map<SoundEffect, ItemRecord> a = Maps.newHashMap();
    private final SoundEffect b;
    protected ItemRecord(String s, SoundEffect soundeffect) {
        this.b = soundeffect;
        this.maxStackSize = 1;
        this.b(CreativeModeTab.f);
        ItemRecord.a.put(this.b, this);
    }

    public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        IBlockData iblockdata = world.getType(blockposition);

        if (iblockdata.getBlock() == Blocks.JUKEBOX && !((Boolean) iblockdata.get(BlockJukeBox.HAS_RECORD)).booleanValue()) {
            if (!world.isClientSide) {
                return EnumInteractionResult.SUCCESS; // CraftBukkit - handled in ItemStack
            }

            return EnumInteractionResult.SUCCESS;
        } else {
            return EnumInteractionResult.PASS;
        }
    }

    public EnumItemRarity g(ItemStack itemstack) {
        return EnumItemRarity.RARE;
    }
}
