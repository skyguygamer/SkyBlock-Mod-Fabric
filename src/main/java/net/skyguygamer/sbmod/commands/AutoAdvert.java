package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;



public final class AutoAdvert
{
    //public static ClientPlayerEntityEntity lp = null;
    public static int interval = 8400;
    public static Boolean sendingMessages = false;
    public static String message = "";
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(ClientCommandManager.literal("autoadvert")
                .then(ClientCommandManager.literal("message").then(ClientCommandManager.argument("messagetosend", StringArgumentType.string()).executes(AutoAdvert -> advert(AutoAdvert.getSource(),
                        StringArgumentType.getString(AutoAdvert, "messagetosend")))))
                .then(ClientCommandManager.literal("time").then(ClientCommandManager.argument("amountofminutes", IntegerArgumentType.integer(5)).executes(AutoAdvert -> time(AutoAdvert.getSource(),
                        IntegerArgumentType.getInteger(AutoAdvert, "amountofminutes"))
                )))
                .then(ClientCommandManager.literal("info").executes(AutoAdvert -> info(AutoAdvert.getSource())))
                .executes(AutoAdvert -> stop(AutoAdvert.getSource())));
        dispatcher.register(ClientCommandManager.literal("advert")
                .then(ClientCommandManager.literal("message").then(ClientCommandManager.argument("messagetosend", StringArgumentType.string()).executes(AutoAdvert -> advert(AutoAdvert.getSource(),
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
        if(sendingMessages) {
            source.sendFeedback(Text.literal("§aYou are currently sending §f" + message));
            source.sendFeedback(Text.literal("§aYou are sending messages every §f" + interval/1200 + " §aminutes"));
        } else {
            source.sendFeedback(Text.literal("§cYou are not sending any messages right now!"));
        }
        return Command.SINGLE_SUCCESS;
    }
    public static int advert(FabricClientCommandSource source, String messagetosend) {
        message = messagetosend;
        sendingMessages = true;
        ClientPlayerEntity lp = (ClientPlayerEntity) source.getEntity();
        //lp.chatSigned("d", Text.literal("dd"));

        //Timer timer = new Timer();
        //timer.scheduleAtFixedRate(sendmsg(messagetosend, source), 1000, 1000);
        source.sendFeedback(Text.literal( "§f" + messagetosend + " §awill now be sent every §f" + interval/1200+ " §aminutes!"));


        lp.sendChatMessage(message, Text.literal(""));
        return Command.SINGLE_SUCCESS;
    }

    /*private static TimerTask sendmsg(String messagetosend, FabricClientCommandSource source) {
        ClientPlayerEntityEntity lp = (ClientPlayerEntityEntity) source.getEntity();
        lp.chatSigned(messagetosend, Text.literal(""));
        return null;
    }

     */

    private static int time(FabricClientCommandSource source, int amountofminutes) {
        interval = amountofminutes*1200;

        source.sendFeedback(Text.literal("§aYour message will now be sent every §f" + amountofminutes + " §aminutes!"));

        return Command.SINGLE_SUCCESS;
    }
}