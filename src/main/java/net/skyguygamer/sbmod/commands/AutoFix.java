package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;


import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.skyguygamer.sbmod.SbMod;

import static net.skyguygamer.sbmod.SbMod.*;

public class AutoFix {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("autofix").executes(Autofix -> run(Autofix.getSource()))
                .then(ClientCommandManager.literal("time").executes(AutoFix -> time(AutoFix.getSource()))));
        dispatcher.register(ClientCommandManager.literal("autorepair").executes(Autofix -> run(Autofix.getSource()))
                .then(ClientCommandManager.literal("time").executes(AutoFix -> time(AutoFix.getSource()))));
    }
    public static int run(FabricClientCommandSource source) {
        if (SbMod.autoFix) {
            SbMod.autoFix = false;
            source.sendFeedback(Text.literal(Formatting.GREEN + "Auto Fix Disabled"));
        } else if (!SbMod.autoFix) {
            SbMod.autoFix = true;
            source.sendFeedback(Text.literal(Formatting.GREEN + "Auto Fix Enabled"));
        }
        return Command.SINGLE_SUCCESS;
    }
    public static int time(FabricClientCommandSource source) {
        int time = 24000-coolDownCounter;
        int timeRemaining = (time/20)/60;
        int seconds = (time/20)%60;
        if (autoFix) {
            source.sendFeedback(Text.literal("§aTime remaining before next fix try§f: " + timeRemaining + " §aminutes, §f" + seconds + " §aseconds"));
        } else {
            source.sendFeedback(Text.literal("§cAuto fix is not enabled!"));
        }
        return Command.SINGLE_SUCCESS;
    }
}

