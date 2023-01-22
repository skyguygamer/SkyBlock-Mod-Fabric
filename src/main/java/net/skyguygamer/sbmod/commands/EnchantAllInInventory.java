package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.skyguygamer.sbmod.SbMod.*;

public class EnchantAllInInventory {
    public static boolean finished = false;
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(ClientCommandManager.literal("enchantinventory").executes(EnchantAllInInventory::enchantItems));
    }


    private static int run(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Enchanting your inventory"));
        PlayerInventory playerInventory = MinecraftClient.getInstance().player.getInventory();

        //ticksAfterRunningCommand = 0;
        //enchantInventory = true;
        //context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Your inventory has been enchanted"));
        return Command.SINGLE_SUCCESS;
    }
    public static int enchantItems(CommandContext<FabricClientCommandSource> context) {
        PlayerInventory player = MinecraftClient.getInstance().player.getInventory();
        long timeSinceLastCommand = System.currentTimeMillis();
        for (int i = 0; i < player.size(); i++) {
            ItemStack itemstack = player.getStack( i);
            if (itemstack.isEnchantable() && EnchantmentHelper.get(itemstack).isEmpty()) {
                player.offerOrDrop(itemstack);
                player.selectedSlot = player.getSlotWithStack(itemstack);
                //player.setStack(4, itemstack);
                for (int j = 0; j < 5; j++) {
                    if (System.currentTimeMillis() - timeSinceLastCommand > 5000) {
                        // send command here
                        timeSinceLastCommand = System.currentTimeMillis();
                    }
                }
                MinecraftClient.getInstance().player.sendMessage(Text.literal("Woop"));
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
