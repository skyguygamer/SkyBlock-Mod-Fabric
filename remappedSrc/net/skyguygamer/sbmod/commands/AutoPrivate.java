package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.skyguygamer.sbmod.SbMod;

public class AutoPrivate {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("autoprivate").then(ClientCommandManager.argument("line3", StringArgumentType.string()).then(ClientCommandManager.argument("line4", StringArgumentType.string())
                .executes(AutoPrivate -> run(AutoPrivate.getSource(),
                        StringArgumentType.getString(AutoPrivate, "line3"),
                        StringArgumentType.getString(AutoPrivate, "line4")
                )))).executes(AutoPrivate -> run2(AutoPrivate.getSource())));

    }
    public static String text;
    private static int run(FabricClientCommandSource source, String line3, String line4) {
        //text = "[Private]\n" + source.getEntity().getName() + "\n" + line3 + "\n" + line4+"�";
        if(!SbMod.autoPrivate) {
            SbMod.autoPrivate = true;
            source.sendFeedback(Text.literal(Formatting.GREEN + "AutoPrivate is now enabled"));
        } else {
            SbMod.autoPrivate = false;
            source.sendFeedback(Text.literal(Formatting.GREEN + "AutoPrivate is now disabled"));
        }
        return Command.SINGLE_SUCCESS;


    }
    private static int run2(FabricClientCommandSource source) {
        //text = "[Private]\n"+source.getEntity().getName()+"�";
        text = "d";
        if(!SbMod.autoPrivate) {
            SbMod.autoPrivate = true;
            source.sendFeedback(Text.literal(Formatting.GREEN + "AutoPrivate is now enabled"));
        } else {
            SbMod.autoPrivate = false;
            source.sendFeedback(Text.literal(Formatting.GREEN + "AutoPrivate is now disabled"));
        }
        return Command.SINGLE_SUCCESS;
    }
}
