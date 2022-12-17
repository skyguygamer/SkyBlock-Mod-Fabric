package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.skyguygamer.sbmod.SbMod.*;
import static net.skyguygamer.sbmod.config.Config.*;

public final class ToggleChats {
    public static boolean toggleAllOn = false;


    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("toggle").executes(ToggleChats::run)
                .then(ClientCommandManager.literal("tips").executes(ToggleChats::tips))
                .then(ClientCommandManager.literal("advancements").executes(ToggleChats::advancements))
                .then(ClientCommandManager.literal("newplayerwelcome").executes(ToggleChats::newPlayerWelcome))
                .then(ClientCommandManager.literal("playervoted").executes(ToggleChats::playerVoted))
                .then(ClientCommandManager.literal("extravotingrewards").executes(ToggleChats::votingRewards)));
    }

    private static int run(CommandContext<FabricClientCommandSource> context) {
        if (!toggleAllOn) {
            toggleAllOn = true;
            toggleTips = true;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled all toggles to be true"));
        } else {
            toggleAllOn = false;
            toggleTips = false;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled all toggles to be false"));
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int tips(CommandContext<FabricClientCommandSource> context) {
        if (!toggleTips) {
            toggleTips = true;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for tips to not be sent"));
        } else {
            toggleTips = false;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for tips to be sent"));
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int advancements(CommandContext<FabricClientCommandSource> context) {
        if (!toggleAdvancments) {
            toggleAdvancments = true;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for advancements to not be sent"));
        } else {
            toggleAdvancments = false;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for advancements to be sent"));
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int newPlayerWelcome(CommandContext<FabricClientCommandSource> context) {
        if (!toggleNewPlayerWelcome) {
            toggleNewPlayerWelcome = true;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for new player welcomes to not be sent"));
        } else {
            toggleNewPlayerWelcome = false;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for new player welcomes to be sent"));
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int playerVoted(CommandContext<FabricClientCommandSource> context) {
        if (!togglePlayerVoted) {
            togglePlayerVoted = true;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for voting rewards to not be sent"));
        } else {
            togglePlayerVoted = false;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for voting rewards to be sent"));
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int votingRewards(CommandContext<FabricClientCommandSource> context) {
        if (!toggleVoterRewards) {
            toggleVoterRewards = true;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for extra voting rewards to not be sent"));
        } else {
            toggleVoterRewards = false;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "You have enabled for extra voting rewards to be sent"));
        }
        return Command.SINGLE_SUCCESS;
    }
}