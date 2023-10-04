package com.teamabnormals.allurement.common.enchantment;

import com.teamabnormals.allurement.core.AllurementConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SpreadOfAilmentsEnchantment extends Enchantment {

	public SpreadOfAilmentsEnchantment(Enchantment.Rarity rarity, EquipmentSlot... slot) {
		super(rarity, EnchantmentCategory.CROSSBOW, slot);
	}

	@Override
	public int getMinCost(int level) {
		return 12 + (level - 1) * 20;
	}

	@Override
	public int getMaxCost(int p_45173_) {
		return 50;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean isTradeable() {
		return AllurementConfig.COMMON.enableSpreadOfAilments.get();
	}

	@Override
	public boolean isDiscoverable() {
		return AllurementConfig.COMMON.enableSpreadOfAilments.get();
	}
}