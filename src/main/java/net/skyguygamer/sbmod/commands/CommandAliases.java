package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public class CommandAliases {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("saliases").executes(CommandAliases::run));
    }
    private static int run(CommandContext<FabricClientCommandSource> context) {

        String boarder = "";
        for (int i = 0; i < 20; i++) {
            boarder += "§a*";
            boarder += "§f*";
        }
        Style test = Style.EMPTY;
        test = test.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/hello"))
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("What? You found a secret message?").formatted(Formatting.GREEN)));


        context.getSource().sendFeedback(Text.literal(boarder));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/enchantall" + Formatting.WHITE + ": /ea").setStyle(test));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/unenchantall" + Formatting.WHITE + ": /uea"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/joincommand" + Formatting.WHITE + ": /jc"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/autoadvert" + Formatting.WHITE + ": /advert"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/autofix" + Formatting.WHITE + ": /autorepair"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "/autospawnmob" + Formatting.WHITE + ": /asm"));
        context.getSource().sendFeedback(Text.literal(boarder));
        return Command.SINGLE_SUCCESS;
    }
}
