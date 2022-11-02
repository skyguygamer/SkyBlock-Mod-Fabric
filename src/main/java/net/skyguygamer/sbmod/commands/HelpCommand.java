package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public final class HelpCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(ClientCommandManager.literal("shelp").executes(HelpCommand::run));
    }

    static int run(CommandContext<FabricClientCommandSource> context) {
        PlayerEntity PlayerEntity = (PlayerEntity) context.getSource().getEntity();

        String boarder = "";
        for (int i = 0; i < 20; i++) {
            boarder += "§a*";
            boarder += "§f*";
        }
        context.getSource().sendFeedback(Text.literal(boarder));
        context.getSource().sendFeedback(Text.literal("§a/shelp§f: Lists the Skyblock Mods Commands"));
        context.getSource().sendFeedback(Text.literal("§a/joincommand §7[add,delete,list,help]§f: Allows you to set commands/messages that are sent when you join the game"));
        context.getSource().sendFeedback(Text.literal("§a/autoadvert §7[info,message,time]§f: Allows you to send a message every interval (Leave empty to stop adverts)"));
        context.getSource().sendFeedback(Text.literal("§a/64 §7[number]§f: Divides a number by 64 to find how many stacks it is"));
        context.getSource().sendFeedback(Text.literal("§a/calc §7[add,subtract,multiply,divide]§f: Allows you to use simple math methods"));
        context.getSource().sendFeedback(Text.literal("§a/enchantall§f: Will enchant your item with all the possible enchants for that item "));
        context.getSource().sendFeedback(Text.literal("§a/autofix§f: Will autofix your tool every 20 minutes when your tool is below 25%"));
        context.getSource().sendFeedback(Text.literal("§a/autospawnmob §7[info]§f: Will auto spawn a mob of your choosing that you have access to"));
        context.getSource().sendFeedback(Text.literal("§a/hello§f: Sends you a nice message :)"));
        //context.getSource().sendFeedback(Text.literal(""));
        context.getSource().sendFeedback(Text.literal(boarder));
        return Command.SINGLE_SUCCESS;
    }
}
