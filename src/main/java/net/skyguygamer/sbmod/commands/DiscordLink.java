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

public final class DiscordLink {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(ClientCommandManager.literal("sdiscord").executes(DiscordLink::run));
    }

    private static int run(CommandContext<FabricClientCommandSource> context)
    {
        Style discordLink = Style.EMPTY;
        discordLink = discordLink.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/WAw6xC29E8"));

        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Join the support and update discord here for this mod here!" + Formatting.BLUE + " https://discord.gg/WAw6xC29E8").setStyle(discordLink));
        return Command.SINGLE_SUCCESS;
    }
}