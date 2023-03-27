package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public final class CalcCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("calc").then(ClientCommandManager.argument("int1", IntegerArgumentType.integer())
                .then(ClientCommandManager.literal("+").then(ClientCommandManager.argument("int2", IntegerArgumentType.integer())
                .executes(CalcCommand -> runAdd(CalcCommand.getSource(),
                        IntegerArgumentType.getInteger(CalcCommand, "int1"),
                        IntegerArgumentType.getInteger(CalcCommand, "int2"))
                )))
                .then(ClientCommandManager.literal("-").then(ClientCommandManager.argument("int2", IntegerArgumentType.integer())
                        .executes(CalcCommand -> runSubtract(CalcCommand.getSource(),
                                IntegerArgumentType.getInteger(CalcCommand, "int1"),
                                IntegerArgumentType.getInteger(CalcCommand, "int2"))
                        )))
                .then(ClientCommandManager.literal("*").then(ClientCommandManager.argument("int2", IntegerArgumentType.integer())
                        .executes(CalcCommand -> runMultiply(CalcCommand.getSource(),
                                IntegerArgumentType.getInteger(CalcCommand, "int1"),
                                IntegerArgumentType.getInteger(CalcCommand, "int2"))
                        )))
                .then(ClientCommandManager.literal("/").then(ClientCommandManager.argument("int2", IntegerArgumentType.integer())
                        .executes(CalcCommand -> runDivide(CalcCommand.getSource(),
                                IntegerArgumentType.getInteger(CalcCommand, "int1"),
                                IntegerArgumentType.getInteger(CalcCommand, "int2"))
                        )))));
    }
    //Addition
    private static int runAdd(FabricClientCommandSource source, int int1, int int2) {
        int total = int1+int2;
        source.sendFeedback(Text.literal(Formatting.GREEN + (int1 + " + " + int2 + " = " + Formatting.DARK_GREEN + total)));
        return Command.SINGLE_SUCCESS;

    }
    //Subtract
    private static int runSubtract(FabricClientCommandSource source, int int1, int int2) {
        int total = int1-int2;
        source.sendFeedback(Text.literal(Formatting.GREEN + (int1 + " - " + int2 + " = " + Formatting.DARK_GREEN + total)));
        return Command.SINGLE_SUCCESS;
    }
    //Multiply
    private static int runMultiply(FabricClientCommandSource source, int int1, int int2) {
        int total = int1*int2;
        source.sendFeedback(Text.literal(Formatting.GREEN + (int1 + " * " + int2 + " = " + Formatting.DARK_GREEN + total)));
        return Command.SINGLE_SUCCESS;
    }
    //Divide
    private static int runDivide(FabricClientCommandSource source, int int1, int int2) {
        int total = int1/int2;
        source.sendFeedback(Text.literal(Formatting.GREEN + (int1 + " / " + int2 + " = " + Formatting.DARK_GREEN + total)));
        return Command.SINGLE_SUCCESS;
    }
}