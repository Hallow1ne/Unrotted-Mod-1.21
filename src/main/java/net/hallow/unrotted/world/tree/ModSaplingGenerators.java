package net.hallow.unrotted.world.tree;

import net.hallow.unrotted.Unrotted;
import net.hallow.unrotted.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator BLACKWOOD = new SaplingGenerator(Unrotted.MOD_ID + ":blackwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLACKWOOD_KEY), Optional.empty());
    public static final SaplingGenerator ROTWOOD = new SaplingGenerator(Unrotted.MOD_ID + ":rotwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ROTWOOD_KEY), Optional.empty());
}
