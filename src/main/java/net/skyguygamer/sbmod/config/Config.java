package net.skyguygamer.sbmod.config;


import eu.midnightdust.lib.config.MidnightConfig;

import java.util.ArrayList;
import java.util.List;

public class Config extends MidnightConfig {
    @Comment(centered = true) public static Comment toggles;      // Centered comments are the same as normal ones - just centered!
    @Entry public static boolean toggleWelcomeMessage = false;
    @Entry public static boolean toggleTips = false;
    @Entry public static boolean toggleAdvancements = false;
    @Entry public static boolean toggleNewPlayerWelcome = false;
    @Entry public static boolean togglePlayerVoted = false;
    @Entry public static boolean toggleVoterRewards = false;
    @Entry public static boolean toggleFriendJoin = false;
    @Entry public static boolean togglePlayersPerishedInVoid = false;
    @Comment(centered = true) public static Comment features;
    @Entry(name = "Amount of lottery tickets to buy",isSlider = true, min = 1, max = 5) public static int lotteryTickets = 2;
    //@Entry public static boolean sendMessages = false
    //@Entry(min = 5) public static int autoAdvertTime = 7;
    //@Entry public static String autoAdvert = "";
    //@Entry public static boolean hoverHack = false;
    @Entry public static boolean joinCommands = true;
    @Entry public static boolean toggleMessageLogs = true;
    @Entry public static boolean toggleTradeLogs = true;
    @Entry() public static boolean staffCheck = false;
    @Entry(name = "Add staff UUID's here") public static List<String> extraStaffNames = List.of();

    //@Entry public static TestEnum testEnum = TestEnum.FABRIC;   // Example for an enum option
    //public enum TestEnum {                               // Enums allow the user to cycle through predefined options
    //    QUILT, FABRIC, FORGE
    //}
    //@Entry(min=69,max=420) public static int hello = 420;   // - The entered number has to be larger than 69 and smaller than 420
    //@Entry(width = 7, min = 7, isColor = true, name = "I am a color!") public static String titleColor = "#ffffff"; // The isColor property adds a preview box for a hexadecimal color
    //@Entry(name = "I am an array list!") public static List<String> arrayList = List.of("String1", "String2"); // Array String Lists are also supported
    //@Entry(name = "I am an int slider.",isSlider = true, min = 0, max = 100) public static int intSlider = 35; // Int fields can also be displayed as a Slider
    //@Entry(name = "I am a float slider!", isSlider = true, min = 0f, max = 1f, precision = 1000) public static float floatSlider = 0.24f; // And so can floats! Precision defines the amount of decimal places
    // The name field can be used to specify a custom translation string or plain text

    //public static int imposter = 16777215; // - Entries without an @Entry or @Comment annotation are ignored

    /*
    The .json language file for your config class could look similar to this:
    {
	    "modid.midnightconfig.title":"I am a title",        // "*.midnightconfig.title" defines the title of the screen
	    "modid.midnightconfig.text1":"I am a comment *u*",  // Translation for the comment "text1" defined in the example config
	    "modid.midnightconfig.text2":"I am a centered comment (╯°□°）╯︵ ┻━┻",
	    "modid.midnightconfig.name":"I am a string!",             // Translation for the field "name" defined in the example config
	    "modid.midnightconfig.name.tooltip":"I am a tooltip uwu \nI am a new line",
	    // When hovering over the option "showInfo",
	    // this text will appear as a tooltip.
	    // "\n" inserts a line break.
	    "modid.midnightconfig.fabric":"I am an int",
	    "modid.midnightconfig.world":"I am a double",
	    "modid.midnightconfig.showInfo":"I am a boolean",
	    "modid.midnightconfig.hello":"I am a limited int!",
	    "modid.midnightconfig.testEnum":"I am an enum!",
	    "modid.midnightconfig.enum.TestEnum.FORGE":"Slow",
	    "modid.midnightconfig.enum.TestEnum.FABRIC":"Fancy",
	    "modid.midnightconfig.enum.TestEnum.QUILT":"Fabulous"
    }
    To initialize the config you have to call "MidnightConfig.init("modid", MidnightConfigExample.class)" in your ModInitializer
    To get an instance of the config screen you have to call "MidnightConfig.getScreen(parent, "modid");"
    If you don't use the whole library and therefore not the automatic ModMenu integration, the code in your ModMenu integration class would look something like this:
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, "modid");
    }
    */
}

