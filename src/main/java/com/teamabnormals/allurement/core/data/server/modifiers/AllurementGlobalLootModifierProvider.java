package com.teamabnormals.allurement.core.data.server.modifiers;

import com.teamabnormals.allurement.common.loot.AscensionCurseLootModifier;
import com.teamabnormals.allurement.common.loot.HorseArmorLootModifier;
import com.teamabnormals.allurement.core.Allurement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;


public class AllurementGlobalLootModifierProvider extends GlobalLootModifierProvider {

	public AllurementGlobalLootModifierProvider(PackOutput output) {
		super(output, Allurement.MOD_ID);
	}

	@Override
	public void start() {
		this.add("enchanted_horse_armor", new HorseArmorLootModifier(new LootItemCondition[0]));
		this.add("curse_of_ascension", new AscensionCurseLootModifier(new LootItemCondition[0]));
	}
}