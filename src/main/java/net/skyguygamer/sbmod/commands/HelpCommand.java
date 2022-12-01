package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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
        Style style = Style.EMPTY;
        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/shelp"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/shelp" + Formatting.WHITE + ": Lists the Skyblock Mods Commands").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/saliases"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/saliases" + Formatting.WHITE + ": Lists the available shortcuts to commands in this mod").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/joincommand"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/joincommand " + Formatting.GRAY + "[add,delete,list,help]" + Formatting.WHITE + ": Allows you to set commands/messages that are sent when you join the game").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/autoadvert"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/autoadvert " + Formatting.GRAY + "[info,message,time]" + Formatting.WHITE + ": Allows you to send a message every interval (Leave empty to stop adverts)").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/64"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/64 " + Formatting.GRAY + "[number]" + Formatting.WHITE + ": Divides a number by 64 to find how many stacks it is").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/calc"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/calc " + Formatting.GRAY + "[add,subtract,multiply,divide]" + Formatting.WHITE + ": Allows you to use simple math methods").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/enchantall"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/enchantall" + Formatting.WHITE + ": Will enchant your item with all the possible enchants for that item (not silk)").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/unenchantall"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/unenchantall" + Formatting.WHITE + ": Gives you the ability to unenchant everything (not silk)").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/autofix"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/autofix" + Formatting.WHITE + ": Will auto fix your tool every 20 minutes when your tool is below 25%").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/autospawnmob"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/autospawnmob " + Formatting.GRAY + "[info]" + Formatting.WHITE + ": Will auto spawn a mob of your choosing that you have access to").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/hello"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/hello" + Formatting.WHITE + ": Sends you a nice message :)").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sdiscord"));
                context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/sdiscord" + Formatting.WHITE + ": Sends you the link to the support discord!").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/autobuy"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/autobuy" + Formatting.WHITE + ": Will try to buy 2 lottery tickets every 30 minutes").setStyle(style));

        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/autosell"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/autosell " + Formatting.GRAY + "[economy]" + Formatting.WHITE + ": Will auto sell everything in your inventory every 40 seconds").setStyle(style));
        //style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sdiscord"));
        //context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "" + Formatting.WHITE + ": ").setStyle(style));
        context.getSource().sendFeedback(Text.literal(boarder));
        return Command.SINGLE_SUCCESS;
    }
}
