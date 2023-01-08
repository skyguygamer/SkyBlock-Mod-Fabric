package net.skyguygamer.sbmod.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.entity.Entity;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.skyguygamer.sbmod.SbMod.*;
import static net.skyguygamer.sbmod.config.Config.*;

@Mixin(ChatHud.class)
public class ChatMixin {
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"), cancellable = true)
    private void sbmod$onChatRecieved(Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) throws IOException {
        String incMessage = message.getString();
        if (toggleTips && incMessage.contains("[Skyblock]") && incMessage.startsWith("[S")) {
            ci.cancel();
            LOGGER.info("I have blocked: " + incMessage);
        }
        if (toggleAdvancements && incMessage.contains("has made the advancement") && incMessage.endsWith("]")) {
            ci.cancel();
            LOGGER.info("I have blocked: " + incMessage);
        }
        if (toggleNewPlayerWelcome && incMessage.startsWith("Welcome") && incMessage.contains("to Skyblock!")) {
            ci.cancel();
            LOGGER.info("I have blocked: " + incMessage);
        }
        if (togglePlayerVoted && incMessage.contains("voted at vote.skyblock.net for") && incMessage.endsWith("/vote")) {
            ci.cancel();
            LOGGER.info("I have blocked: " + incMessage);
        }
        if (toggleVoterRewards && incMessage.contains("was lucky and received an extra") && incMessage.endsWith("!")) {
            ci.cancel();
            LOGGER.info("I have blocked: " + incMessage);
        }
        if (toggleFriendJoin && incMessage.startsWith("[Friends] ") && incMessage.contains(" has joined ")) {
            ci.cancel();
            LOGGER.info("I have blocked: " + incMessage);
        }
        if (togglePlayersPerishedInVoid && incMessage.endsWith(" player have perished in the void today.")) {
            ci.cancel();
            LOGGER.info("I have blocked: " + incMessage);
        }
        if (autoBuy && incMessage.startsWith("[SBLottery] Congratulations go to ") && incMessage.contains(" for winning ") && incMessage.contains(" Grass with ") && incMessage.endsWith(" tickets")) {
            autoBuyTime = 100000;
        }
        //Message logger credits to MagikIsAMush
        if (toggleMessageLogs && (incMessage.contains("[me -> ") || (incMessage.contains("[") && incMessage.contains(" -> ")))) {
            if (fileNameOfMessageLogs == "") {
                Date dateNow = new Date(); // On trouve la date
                SimpleDateFormat formatDate = new SimpleDateFormat("MM.dd.yyyy'-'HH.mm.ss");

                fileNameOfMessageLogs = "sbmod/messagelogs/" + MinecraftClient.getInstance().player.getGameProfile().getName() + "_" + formatDate.format(dateNow) + ".txt";

                myObjMessageLogs = new BufferedWriter(new FileWriter(fileNameOfMessageLogs));
            }
            Date msgDate = new Date();
            SimpleDateFormat ft2 = new SimpleDateFormat("dd'-'HH:mm:ss");
            myObjMessageLogs.write("[" + ft2.format(msgDate) + "] " + incMessage);
            myObjMessageLogs.newLine();
            myObjMessageLogs.flush();
        }
        //Trade logger credits to MagikIsAMush
        if (toggleTradeLogs && ((incMessage.contains(" bought ") && incMessage.contains(" from you for ")) || (incMessage.contains("You bought ") && incMessage.contains(" from ") && incMessage.contains(" for "))
                || (incMessage.contains(" sold ") && incMessage.contains(" to you for ")) || (incMessage.contains("You sold ") && incMessage.contains(" to ") && incMessage.contains(" for "))
                || (incMessage.contains(" bartered ") && incMessage.contains(" of their ") && incMessage.contains(" for ") && incMessage.contains(" of your ")) || (incMessage.contains("You bartered ") && incMessage.contains(" of your ") && incMessage.contains(" for ") && incMessage.contains(" of ")))) {
            if (fileNameOfTradeLogs == "") {
                Date dateNow = new Date(); // On trouve la date
                SimpleDateFormat formatDate = new SimpleDateFormat("MM.dd.yyyy'-'HH.mm.ss");

                fileNameOfTradeLogs = "sbmod/tradelogs/" + MinecraftClient.getInstance().player.getGameProfile().getName() + "_" + formatDate.format(dateNow) + ".txt";

                myObjTradeLogs = new BufferedWriter(new FileWriter(fileNameOfTradeLogs));
            }
            Date msgDate = new Date();
            SimpleDateFormat ft2 = new SimpleDateFormat("dd'-'HH:mm:ss");
            myObjTradeLogs.write("[" + ft2.format(msgDate) + "] " + incMessage);
            myObjTradeLogs.newLine();
            myObjTradeLogs.flush();
        }
    }
    @ModifyVariable(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"))
    private Text changeVariable(Text message) {
        String incMessage = message.getString();
        /*if(hoverHack) {
            if (incMessage.startsWith("[") && incMessage.endsWith(" Hover for the word to type!")) {
                Text hoverMsg = message.getStyle().getHoverEvent().getValue(HoverEvent.Action.SHOW_TEXT);
                //hoverMsg.getContent().toString()
                message = Text.literal(Formatting.AQUA + "[✎] Type the word " + Formatting.GREEN + hoverMsg.getString());
                return message;
            } else if (incMessage.startsWith("[") && incMessage.endsWith(" Hover for the word to unscramble!")) {
                Text hoverMsg = message.getStyle().getHoverEvent().getValue(HoverEvent.Action.SHOW_TEXT);
                message = Text.literal(Formatting.AQUA + "[✎] Unscramble the word " + Formatting.GREEN + hoverMsg.getString());
                return message;
            }
        }*/
        return message;
    }

}
