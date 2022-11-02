package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;


import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.skyguygamer.sbmod.SbMod;

public class AutoFix {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("autofix").executes(EnchantAllCommand -> run(EnchantAllCommand.getSource())));
        dispatcher.register(ClientCommandManager.literal("autorepair").executes(EnchantAllCommand -> run(EnchantAllCommand.getSource())));
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
}

