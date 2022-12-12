package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


import static net.skyguygamer.sbmod.SbMod.enchantInHand;

public final class AutoEnchantInHand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher){
        dispatcher.register(ClientCommandManager.literal("autoenchantinhand").executes(AutoEnchantInHand::run));
        dispatcher.register(ClientCommandManager.literal("aeih").executes(AutoEnchantInHand::run));
    }


    private static int run(CommandContext<FabricClientCommandSource> source) {
        if (!enchantInHand){
            enchantInHand = true;
            MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GREEN + "Auto enchant when in hand is enabled"));
        } else {
            enchantInHand = false;
            MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GREEN + "Auto enchant when in hand is disabled"));
        }
        return Command.SINGLE_SUCCESS;
    }
}
