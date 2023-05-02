/*package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.skyguygamer.sbmod.config.Config.autoBuy;
import static net.skyguygamer.sbmod.config.Config.lotteryTickets;

public final class AutoBuyTemp {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("autobuy").executes(AutoBuyTemp::run)
                .then(ClientCommandManager.argument("tickets", IntegerArgumentType.integer(1, 5)).executes(AutoBuyTemp -> customAmount(AutoBuyTemp.getSource(),
                        IntegerArgumentType.getInteger(AutoBuyTemp, "tickets")))));
    }

    private static int run(CommandContext<FabricClientCommandSource> source) {

        if(autoBuy) {
            autoBuy = false;
            source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Auto lottery buy has been disabled!"));
        } else {
            autoBuy = true;
            source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Auto lottery buy has been enabled!"));
            source.getSource().getPlayer().sendCommand("lottery buy " + lotteryTickets);
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int customAmount(FabricClientCommandSource source, int tickets) {
        autoBuy = true;
        lotteryTickets = tickets;
        source.sendFeedback(Text.literal(Formatting.GREEN + "Auto lottery buy has been enabled!"));
        source.getPlayer().sendCommand("lottery buy " + lotteryTickets);
        return Command.SINGLE_SUCCESS;
    }

}
 */
