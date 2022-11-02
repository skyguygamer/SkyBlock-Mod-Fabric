package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class HelloCommand
{
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(ClientCommandManager.literal("hello").executes(HelloCommand::run));
    }

    private static int run(CommandContext<FabricClientCommandSource> context)
    {
        PlayerEntity PlayerEntity = (PlayerEntity) context.getSource().getEntity();

        context.getSource().sendFeedback(Text.translatable("hellocommand", PlayerEntity.getName()).formatted(Formatting.RED));
        return Command.SINGLE_SUCCESS;
    }
}

