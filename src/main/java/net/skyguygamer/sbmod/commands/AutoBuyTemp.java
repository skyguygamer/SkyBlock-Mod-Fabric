package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.skyguygamer.sbmod.SbMod;

public final class AutoBuyTemp {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("autobuy").executes(AutoBuyTemp::run));
    }

    private static int run(CommandContext<FabricClientCommandSource> source) {
        if(SbMod.autoBuy) {
            SbMod.autoBuy = false;
            source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Auto lottery buy has been disabled!"));
        } else {
            SbMod.autoBuy = true;
            SbMod.autoBuyTime = 0;
            source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Auto lottery buy has been enabled!"));
            source.getSource().getPlayer().sendCommand("lottery buy 2");
        }
        return Command.SINGLE_SUCCESS;
    }


}
