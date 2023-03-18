package net.skyguygamer.sbmod.config;


import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class Config extends MidnightConfig {

    @Comment(centered = true)
    public static Comment features;
    @Entry
    public static boolean toggleChatAfk = false;
    @Entry
    public static boolean timeChat = false;
    @Entry
    public static boolean autoBuy = false;
    @Entry(name = "Amount of lottery tickets to buy", isSlider = true, min = 1, max = 5)
    public static int lotteryTickets = 2;
    @Entry(min = 6000)
    public static int interval = 8400;
    @Entry
    public static boolean toggleMessageLogs = true;
    @Entry
    public static boolean toggleTradeLogs = true;
    @Entry()
    public static boolean staffCheck = false;
    @Entry(name = "Add staff UUID's here")
    public static List<String> extraStaffNames = List.of();

    @Comment(centered = true)
    public static Comment toggles;
    @Entry
    public static boolean toggleMotds = false;
    @Entry
    public static boolean toggleTips = false;
    @Entry
    public static boolean toggleAdvancements = false;
    @Entry
    public static boolean toggleNewPlayerWelcome = false;
    @Entry
    public static boolean togglePlayerVoted = false;
    @Entry
    public static boolean toggleVoterRewards = false;
    @Entry
    public static boolean toggleFriendJoin = false;
    @Entry
    public static boolean togglePlayersPerishedInVoid = false;
    @Entry
    public static boolean toggleBans = false;
    @Entry
    public static boolean toggleCrates = false;
    @Entry
    public static boolean toggleVPRewards = false;
    @Entry
    public static boolean toggleClag = false;
    @Entry
    public static boolean toggleHoverGame = false;
    @Entry
    public static boolean toggleLottery = false;

    @Comment(centered = true)
    public static Comment devToggles;
    @Entry
    public static boolean joinCommands = true;
    @Entry()
    public static boolean toggleNotify = false;
    @Entry(name = "Add extra word to notify (nickname)")
    public static String extraNotifyWord = "";
    @Entry
    public static boolean toggleWelcomeMessage = false;
    /*@Entry
    public static boolean hoverHack = false;
    */
}

