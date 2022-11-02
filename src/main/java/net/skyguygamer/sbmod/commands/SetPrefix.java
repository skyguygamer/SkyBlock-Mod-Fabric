package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;


public final class SetPrefix {
    public static String prefix1 = "";

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher){
        dispatcher.register(ClientCommandManager.literal("setchatprefix").then(ClientCommandManager.argument("prefix", StringArgumentType.string())
                .executes(SetPrefix -> run(SetPrefix.getSource(),
                        StringArgumentType.getString(SetPrefix, "prefix"))
                )));
    }
    public static int run(FabricClientCommandSource source, String prefix) {
        prefix1 = prefix;
        return Command.SINGLE_SUCCESS;
    }

}
