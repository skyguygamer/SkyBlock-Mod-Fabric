package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;


public final class CalcCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("calc")
                .then(ClientCommandManager.literal("add").then(ClientCommandManager.argument("amount", IntegerArgumentType.integer()).then(ClientCommandManager.argument("amount2", IntegerArgumentType.integer())
                .executes(CalcCommand -> runAdd(CalcCommand.getSource(),
                        IntegerArgumentType.getInteger(CalcCommand, "amount"),
                        IntegerArgumentType.getInteger(CalcCommand, "amount2"))
                ))))
                .then(ClientCommandManager.literal("subtract").then(ClientCommandManager.argument("amount", IntegerArgumentType.integer()).then(ClientCommandManager.argument("amount2", IntegerArgumentType.integer())
                        .executes(CalcCommand -> runSubtract(CalcCommand.getSource(),
                                IntegerArgumentType.getInteger(CalcCommand, "amount"),
                                IntegerArgumentType.getInteger(CalcCommand, "amount2"))
                        ))))
                .then(ClientCommandManager.literal("multiply").then(ClientCommandManager.argument("amount", IntegerArgumentType.integer()).then(ClientCommandManager.argument("amount2", IntegerArgumentType.integer())
                        .executes(CalcCommand -> runMultiply(CalcCommand.getSource(),
                                IntegerArgumentType.getInteger(CalcCommand, "amount"),
                                IntegerArgumentType.getInteger(CalcCommand, "amount2"))
                        ))))
                .then(ClientCommandManager.literal("divide").then(ClientCommandManager.argument("amount", IntegerArgumentType.integer()).then(ClientCommandManager.argument("amount2", IntegerArgumentType.integer())
                        .executes(CalcCommand -> runDivide(CalcCommand.getSource(),
                                IntegerArgumentType.getInteger(CalcCommand, "amount"),
                                IntegerArgumentType.getInteger(CalcCommand, "amount2"))
                        )))));
    }
    //Addition
    private static int runAdd(FabricClientCommandSource source, int amount, int amount2) {
        int total = amount+amount2;
        source.sendFeedback(Text.translatable(amount + " + " + amount2 + " = " + total));
        return Command.SINGLE_SUCCESS;

    }
    //Subtract
    private static int runSubtract(FabricClientCommandSource source, int amount, int amount2) {
        int total = amount-amount2;
        source.sendFeedback(Text.literal(amount + " - " + amount2 + " = " + total));
        return Command.SINGLE_SUCCESS;
    }
    //Multiply
    private static int runMultiply(FabricClientCommandSource source, int amount, int amount2) {
        int total = amount*amount2;
        source.sendFeedback(Text.literal(amount + " * " + amount2 + " = " + total));
        return Command.SINGLE_SUCCESS;
    }
    //Divide
    private static int runDivide(FabricClientCommandSource source, int amount, int amount2) {
        int total = amount/amount2;
        source.sendFeedback(Text.literal(amount + " / " + amount2 + " = " + total));
        return Command.SINGLE_SUCCESS;
    }
}