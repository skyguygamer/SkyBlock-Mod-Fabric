package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

import java.io.File;

public final class SBFolder {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher){
        dispatcher.register(ClientCommandManager.literal("sbmodfolda").executes(SBFolder::folder)
                .then(ClientCommandManager.literal("msglogs").executes(SBFolder::latestMessage))
                .then(ClientCommandManager.literal("tradelogs").executes(SBFolder::latestTrades))
                .then(ClientCommandManager.literal("log").executes(SBFolder::log)));
    }
    private static int folder(CommandContext<FabricClientCommandSource> source) {
        Style style = Style.EMPTY;
        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, "sbmod"));
        source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Opening the sbmod folder! If it did not open try clicking here!").setStyle(style));
        Util.getOperatingSystem().open(new File(FabricLoader.getInstance().getGameDir().toFile() + File.separator + "sbmod"));

        return Command.SINGLE_SUCCESS;
    }
    private static int latestMessage(CommandContext<FabricClientCommandSource> source) {
        Style style = Style.EMPTY;
        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, "sbmod/messagelogs"));
        source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Opening the latest message log! If it did not open try clicking here!").setStyle(style));

        long lastModified = Long.MIN_VALUE;
        File[] files = new File("sbmod/messagelogs").listFiles();
        File lastModifiedFile = null;
        for (File file : files) {
            if (file.lastModified() > lastModified) {
                lastModified = file.lastModified();
                lastModifiedFile = file;
            }
        }
        Util.getOperatingSystem().open(lastModifiedFile);
        return Command.SINGLE_SUCCESS;
    }
    private static int latestTrades(CommandContext<FabricClientCommandSource> source) {
        Style style = Style.EMPTY;
        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, "sbmod/tradelogs"));
        source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Opening the latest trade log! If it did not open try clicking here!").setStyle(style));

        long lastModified = Long.MIN_VALUE;
        File[] files = new File("sbmod/tradelogs").listFiles();
        File lastModifiedFile = null;
        for (File file : files) {
            if (file.lastModified() > lastModified) {
                lastModified = file.lastModified();
                lastModifiedFile = file;
            }
        }
        Util.getOperatingSystem().open(lastModifiedFile);
        return Command.SINGLE_SUCCESS;
    }
    private static int log(CommandContext<FabricClientCommandSource> source) {
        Style style = Style.EMPTY;
        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, "logs"));
        source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Opening the latest full log! If it did not open try clicking here!").setStyle(style));
        Util.getOperatingSystem().open(new File(FabricLoader.getInstance().getGameDir().toFile() + File.separator + "logs" + File.separator + "latest.log"));
        Util.getOperatingSystem().open(new File("logs/latest.log"));

        return Command.SINGLE_SUCCESS;
    }
}
