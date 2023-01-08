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

import static net.skyguygamer.sbmod.SbMod.getStringFromSite;
import static net.skyguygamer.sbmod.SbMod.sponsors;

public class Sponsor {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(ClientCommandManager.literal("sponsor").executes(Sponsor::run));
    }
    private static int run(CommandContext<FabricClientCommandSource> context) {
        sponsors = getStringFromSite("https://valid-climber-350022.web.app/githubsponsors.txt");
        Style style = Style.EMPTY;
        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/sponsors/skyguygamer"));
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN+"§aSupport the mod by clicking here! If you donate with a specific amount you will be listed below! ").setStyle(style));
        context.getSource().sendFeedback(Text.literal(Formatting.DARK_GREEN + sponsors).setStyle(style));
        return Command.SINGLE_SUCCESS;
    }
}
