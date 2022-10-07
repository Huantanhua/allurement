package com.teamabnormals.allurement.core;

import com.teamabnormals.allurement.core.data.client.AllurementLanguageProvider;
import com.teamabnormals.allurement.core.data.server.modifiers.AllurementGlobalLootModifierProvider;
import com.teamabnormals.allurement.core.data.server.tags.AllurementEnchantmentTagsProvider;
import com.teamabnormals.allurement.core.registry.AllurementEnchantments;
import com.teamabnormals.allurement.core.registry.AllurementLootModifiers;
import com.teamabnormals.blueprint.common.world.storage.tracking.DataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Allurement.MOD_ID)
public class Allurement {
	public static final String MOD_ID = "allurement";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public static final TrackedData<Boolean> INFINITY_ARROW = TrackedData.Builder.create(DataProcessors.BOOLEAN, () -> false).enableSaving().build();
	public static final TrackedData<Float> ABSORBED_DAMAGE = TrackedData.Builder.create(DataProcessors.FLOAT, () -> 0.0F).enableSaving().build();

	public Allurement() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		AllurementEnchantments.ENCHANTMENTS.register(bus);
		AllurementLootModifiers.GLOBAL_LOOT_MODIFIERS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);

		context.registerConfig(ModConfig.Type.COMMON, AllurementConfig.COMMON_SPEC);
		context.registerConfig(ModConfig.Type.CLIENT, AllurementConfig.CLIENT_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		TrackedDataManager.INSTANCE.registerData(new ResourceLocation(MOD_ID, "shot_infinity_arrow"), INFINITY_ARROW);
		TrackedDataManager.INSTANCE.registerData(new ResourceLocation(MOD_ID, "absorbed_damage"), ABSORBED_DAMAGE);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		generator.addProvider(includeServer, new AllurementEnchantmentTagsProvider(generator, existingFileHelper));
		generator.addProvider(includeServer, new AllurementGlobalLootModifierProvider(generator));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new AllurementLanguageProvider(generator));
	}
}