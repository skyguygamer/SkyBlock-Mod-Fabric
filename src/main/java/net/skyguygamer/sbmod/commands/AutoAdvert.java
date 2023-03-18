package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import static net.skyguygamer.sbmod.SbMod.advertTimer;
import static net.skyguygamer.sbmod.config.Config.interval;


public final class AutoAdvert
{
    public static Boolean sendingMessages = false;
    public static String message = "";
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(ClientCommandManager.literal("autoadvert")
                .then(ClientCommandManager.literal("message").then(ClientCommandManager.argument("messagetosend", StringArgumentType.greedyString()).executes(AutoAdvert -> advert(AutoAdvert.getSource(),
                        StringArgumentType.getString(AutoAdvert, "messagetosend")))))
                .then(ClientCommandManager.literal("time").then(ClientCommandManager.argument("amountofminutes", IntegerArgumentType.integer(5)).executes(AutoAdvert -> time(AutoAdvert.getSource(),
                        IntegerArgumentType.getInteger(AutoAdvert, "amountofminutes"))
                )))
                .then(ClientCommandManager.literal("info").executes(AutoAdvert -> info(AutoAdvert.getSource())))
                .executes(AutoAdvert -> stop(AutoAdvert.getSource())));
        dispatcher.register(ClientCommandManager.literal("advert")
                .then(ClientCommandManager.literal("message").then(ClientCommandManager.argument("messagetosend", StringArgumentType.greedyString()).executes(AutoAdvert -> advert(AutoAdvert.getSource(),
                        StringArgumentType.getString(AutoAdvert, "messagetosend")))))
                .then(ClientCommandManager.literal("time").then(ClientCommandManager.argument("amountofminutes", IntegerArgumentType.integer(5)).executes(AutoAdvert -> time(AutoAdvert.getSource(),
                        IntegerArgumentType.getInteger(AutoAdvert, "amountofminutes"))
                )))
                .then(ClientCommandManager.literal("info").executes(AutoAdvert -> info(AutoAdvert.getSource())))
                .executes(AutoAdvert -> stop(AutoAdvert.getSource())));

    }
    private static int stop(FabricClientCommandSource source) {
        sendingMessages = false;
    
        source.sendFeedback(Text.literal("§aYou have stopped sending adverts!"));

        return Command.SINGLE_SUCCESS;
    }
    private static int info(FabricClientCommandSource source) {
        int time = interval-advertTimer;
        int timeRemaining = (time/20)/60;
        int seconds = (time/20)%60;
        if(sendingMessages) {
            source.sendFeedback(Text.literal("§aYou are currently sending §f" + message));
            source.sendFeedback(Text.literal("§aYou are sending messages every §f" + interval/1200 + " §aminutes"));
            source.sendFeedback(Text.literal("§aTime remaining§f: " + timeRemaining + " §aminutes, §f" + seconds + " §aseconds"));
        } else {
            source.sendFeedback(Text.literal("§cYou are not sending any messages right now!"));
        }
        return Command.SINGLE_SUCCESS;
    }
    public static int advert(FabricClientCommandSource source, String messagetosend) {
        message = messagetosend;
        sendingMessages = true;
        advertTimer = 0;
        ClientPlayerEntity lp = (ClientPlayerEntity) source.getEntity();

        source.sendFeedback(Text.literal( "§f" + messagetosend + " §awill now be sent every §f" + interval/1200+ " §aminutes!"));

        lp.sendChatMessage(message, Text.literal(""));
        return Command.SINGLE_SUCCESS;
    }

    private static int time(FabricClientCommandSource source, int amountofminutes) {
        interval = amountofminutes*1200;

        source.sendFeedback(Text.literal("§aYour message will now be sent every §f" + amountofminutes + " §aminutes!"));

        return Command.SINGLE_SUCCESS;
    }
}