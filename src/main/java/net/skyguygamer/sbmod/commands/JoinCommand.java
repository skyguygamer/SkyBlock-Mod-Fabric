package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public final class JoinCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {

        List<String> aliases = new ArrayList<String>();
        aliases.add("jc");
        aliases.add("joincommand");

        boolean success = false;
        ArrayList<String> commands = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("joincommands.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                commands.add(line);
            }
            in.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
        dispatcher.register(ClientCommandManager.literal("joincommand")
                .then(ClientCommandManager.literal("add").then(ClientCommandManager.argument("command", StringArgumentType.greedyString()).executes(JoinCommand -> add(JoinCommand.getSource(),
                                StringArgumentType.getString(JoinCommand, "command"), commands, success

                        )))
                ).then(ClientCommandManager.literal("delete").then(ClientCommandManager.argument("numinlist", IntegerArgumentType.integer(0)).executes(JoinCommand -> delete(JoinCommand.getSource(),
                        IntegerArgumentType.getInteger(JoinCommand, "numinlist"), commands, success))))
                .then(ClientCommandManager.literal("list").executes(JoinCommand -> listJc(JoinCommand.getSource(), commands))));




        dispatcher.register(ClientCommandManager.literal("jc")
                .then(ClientCommandManager.literal("add").then(ClientCommandManager.argument("command", StringArgumentType.greedyString()).executes(JoinCommand -> add(JoinCommand.getSource(),
                                StringArgumentType.getString(JoinCommand, "command"), commands, success

                        )))
                ).then(ClientCommandManager.literal("delete").then(ClientCommandManager.argument("numinlist", IntegerArgumentType.integer(0)).executes(JoinCommand -> delete(JoinCommand.getSource(),
                        IntegerArgumentType.getInteger(JoinCommand, "numinlist"), commands, success))))
                .then(ClientCommandManager.literal("list").executes(JoinCommand -> listJc(JoinCommand.getSource(), commands))));



    }

    private static int add(FabricClientCommandSource source, String command, ArrayList<String> commands, Boolean success) {
        if (command.startsWith("/")) {
            command = command.substring(1);
        }
        if (command.equals("")) {
            source.sendFeedback(Text.literal("Please enter a command").formatted(Formatting.RED));
        }
        else {
            String addtolist = "";
            for (int i = 1; i < 2; i++) {
                addtolist += command;
            }
            commands.add(addtolist);
            success = true;
            source.sendFeedback(Text.literal(addtolist+" will be executed whenever you join").formatted(Formatting.DARK_AQUA));
            }
        if (success) {
            PrintWriter writer;
            try {
                writer = new PrintWriter("joincommands.txt", "UTF-8");
                for (int i = 0; i < commands.size(); i++) {
                    writer.println(commands.get(i));
                }
                source.sendFeedback(Text.literal("Successfully updated").formatted(Formatting.GREEN));
                writer.close();
                success = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                success = false;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                success = false;
            }
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int delete(FabricClientCommandSource source, int numinlist, ArrayList<String> commands, Boolean success) {
        try {
            commands.remove(numinlist);
            success = true;
        } catch (Exception e) {
            source.sendFeedback(Text.literal("Invalid, please check your index # (/joincommand list)").formatted(Formatting.RED));
        }
        if (success) {
            PrintWriter writer;
            try {
                writer = new PrintWriter("joincommands.txt", "UTF-8");
                for (int i = 0; i < commands.size(); i++) {
                    writer.println(commands.get(i));
                }
                source.sendFeedback(Text.literal("Successfully updated").formatted(Formatting.GREEN));
                writer.close();
                success = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                success = false;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                success = false;
            }
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int listJc(FabricClientCommandSource source, ArrayList<String> commands) {
        source.sendFeedback(Text.literal("****************************************").formatted(Formatting.GREEN));
        if (commands.size()==0) {
            source.sendFeedback(Text.literal("Kinda lonely here :("));
        }
        for (int i = 0; i < commands.size(); i++) {
            source.sendFeedback(Text.literal(String.valueOf(i)+": "+commands.get(i)));
        }
        source.sendFeedback(Text.literal("****************************************").formatted(Formatting.GREEN));



        return Command.SINGLE_SUCCESS;
    }


}
