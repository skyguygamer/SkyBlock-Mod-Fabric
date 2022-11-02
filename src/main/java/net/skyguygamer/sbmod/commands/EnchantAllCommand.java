package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.skyguygamer.sbmod.SbMod;

public class EnchantAllCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("enchantall").executes(EnchantAllCommand -> run(EnchantAllCommand.getSource())));
        dispatcher.register(ClientCommandManager.literal("ea").executes(EnchantAllCommand -> run(EnchantAllCommand.getSource())));
    }

    public static int run(FabricClientCommandSource source) {

        Item item = MinecraftClient.getInstance().player.getMainHandStack().getItem();
        if (!SbMod.enchant) {
            if (item == Items.NETHERITE_PICKAXE || item == Items.NETHERITE_SHOVEL || item == Items.DIAMOND_PICKAXE || item == Items.DIAMOND_SHOVEL || item == Items.IRON_PICKAXE || item == Items.IRON_SHOVEL || item == Items.STONE_PICKAXE || item == Items.STONE_SHOVEL || item == Items.GOLDEN_PICKAXE || item == Items.GOLDEN_SHOVEL || item == Items.WOODEN_PICKAXE || item == Items.WOODEN_SHOVEL|| item == Items.SHEARS) {
                SbMod.pressTime = 0;
                SbMod.enchantTool = true;
                SbMod.enchant = true;
            }
            else if (item == Items.NETHERITE_AXE || item == Items.DIAMOND_AXE || item == Items.IRON_AXE || item == Items.STONE_AXE || item == Items.GOLDEN_AXE || item == Items.WOODEN_AXE) {
                SbMod.pressTime = 0;
                SbMod.enchantAxe = true;
                SbMod.enchant = true;
            }
            else if (item == Items.NETHERITE_SWORD || item == Items.DIAMOND_SWORD || item == Items.IRON_SWORD || item == Items.STONE_SWORD || item == Items.GOLDEN_SWORD || item == Items.WOODEN_SWORD) {
                SbMod.pressTime = 0;
                SbMod.enchantSword = true;
                SbMod.enchant = true;
            }
            else if (item == Items.BOW) {
                SbMod.pressTime = 0;
                SbMod.enchantBow = true;
                SbMod.enchant = true;
            }
            else if (item == Items.NETHERITE_CHESTPLATE || item == Items.NETHERITE_LEGGINGS || item == Items.DIAMOND_CHESTPLATE || item == Items.DIAMOND_LEGGINGS || item == Items.IRON_CHESTPLATE || item == Items.IRON_LEGGINGS || item == Items.GOLDEN_CHESTPLATE || item == Items.GOLDEN_LEGGINGS || item == Items.LEATHER_CHESTPLATE || item == Items.LEATHER_LEGGINGS || item == Items.CHAINMAIL_CHESTPLATE || item == Items.CHAINMAIL_LEGGINGS ) {
                SbMod.enchantChest = true;
                SbMod.pressTime = 0;
                SbMod.enchant = true;
            }
            else if (item == Items.NETHERITE_HELMET || item == Items.DIAMOND_HELMET || item == Items.IRON_HELMET || item == Items.GOLDEN_HELMET || item == Items.CHAINMAIL_HELMET || item == Items.LEATHER_HELMET) {
                SbMod.enchantHelmet = true;
                SbMod.pressTime = 0;
                SbMod.enchant = true;
            }
            else if (item == Items.NETHERITE_BOOTS || item == Items.DIAMOND_BOOTS || item == Items.IRON_BOOTS || item == Items.GOLDEN_BOOTS || item == Items.CHAINMAIL_BOOTS || item == Items.LEATHER_BOOTS) {
                SbMod.enchantBoots = true;
                SbMod.pressTime = 0;
                SbMod.enchant = true;
            }
            else if (item == Items.FISHING_ROD) {
                SbMod.enchantRod = true;
                SbMod.pressTime = 0;
                SbMod.enchant = true;
            }
            else if (item == Items.FLINT_AND_STEEL || item == Items.ELYTRA || item == Items.WOODEN_HOE || item == Items.STONE_HOE || item == Items.IRON_HOE || item == Items.GOLDEN_HOE || item == Items.DIAMOND_HOE || item == Items.SHIELD) {
                SbMod.enchantOther = true;
                SbMod.pressTime = 0;
                SbMod.enchant = true;
            } else {source.sendFeedback(Text.literal(Formatting.RED+"Please enchant an item that can be enchanted!"));}
        } else {
            source.sendFeedback(Text.literal(Formatting.RED+"Wait until current item has finished enchanting!"));
        }

        return Command.SINGLE_SUCCESS;
    }
}
