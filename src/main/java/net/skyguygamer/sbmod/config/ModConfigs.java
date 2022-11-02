package net.skyguygamer.sbmod.config;

import com.mojang.datafixers.util.Pair;
import net.skyguygamer.sbmod.SbMod;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    //public static String TEST;
    //public static int SOME_INT;
    //public static double SOME_DOUBLE;
    //public static int MAX_DAMAGE_DOWSING_ROD;
    public static boolean welcome;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(SbMod.SBMOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        //configs.addKeyValuePair(new Pair<>("key.test.value1", "Just a Testing string!"), "String");
        //configs.addKeyValuePair(new Pair<>("key.test.value2", 50), "int");
        //configs.addKeyValuePair(new Pair<>("key.test.value3", 4142.5), "double");
        //configs.addKeyValuePair(new Pair<>("dowsing.rod.max.damage", 32), "int");
        configs.addKeyValuePair(new Pair<>("welcome", true), "boolean");
    }

    private static void assignConfigs() {
        //TEST = CONFIG.getOrDefault("key.test.value1", "Nothing");
        //SOME_INT = CONFIG.getOrDefault("key.test.value2", 42);
        //SOME_DOUBLE = CONFIG.getOrDefault("key.test.value3", 42.0d);
        //MAX_DAMAGE_DOWSING_ROD = CONFIG.getOrDefault("dowsing.rod.max.damage", 32);
        welcome = CONFIG.getOrDefault("welcome", true);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
