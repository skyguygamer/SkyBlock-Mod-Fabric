package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

import java.io.File;

public final class SBFolder {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher){
        dispatcher.register(ClientCommandManager.literal("sbmodfolda").executes(SBFolder::run));
    }
    private static int run(CommandContext<FabricClientCommandSource> source) {
        Style style = Style.EMPTY;
        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, "sbmod"));
        source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Opening the sbmod folder! If it did not open try clicking here!").setStyle(style));
        Util.getOperatingSystem().open(new File("sbmod"));
        return Command.SINGLE_SUCCESS;
    }
}
