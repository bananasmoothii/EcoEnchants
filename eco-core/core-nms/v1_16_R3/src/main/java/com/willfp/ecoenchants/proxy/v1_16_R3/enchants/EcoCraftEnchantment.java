package com.willfp.ecoenchants.proxy.v1_16_R3.enchants;

import com.willfp.ecoenchants.enchantments.EcoEnchant;
import com.willfp.ecoenchants.enchantments.support.vanilla.VanillaEnchantmentMetadata;
import com.willfp.ecoenchants.enchantments.util.EnchantmentUtils;
import net.minecraft.server.v1_16_R3.Enchantment;
import org.bukkit.craftbukkit.v1_16_R3.enchantments.CraftEnchantment;
import org.jetbrains.annotations.NotNull;

public class EcoCraftEnchantment extends CraftEnchantment {
    private final VanillaEnchantmentMetadata metadata;

    public EcoCraftEnchantment(@NotNull final Enchantment target,
                               @NotNull final VanillaEnchantmentMetadata metadata) {
        super(target);
        this.metadata = metadata;
    }

    @Override
    public int getMaxLevel() {
        return metadata.maxLevel() == null ? this.getHandle().getMaxLevel() : metadata.maxLevel();
    }

    @Override
    public boolean conflictsWith(@NotNull final org.bukkit.enchantments.Enchantment other) {
        if (other instanceof EcoEnchant) {
            return other.conflictsWith(this);
        }

        return metadata.conflicts() == null ? super.conflictsWith(other) : metadata.conflicts().contains(other.getKey());
    }

    public void register() {
        EnchantmentUtils.register(this);
    }
}
