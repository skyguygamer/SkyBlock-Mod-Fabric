package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class FakeHelpCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(ClientCommandManager.literal("thelp").executes(HelpCommand::run));
    }
    private static int run(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Text.literal(Formatting.RED+"§lWtf is thelp??"));
        return Command.SINGLE_SUCCESS;
    }
}
