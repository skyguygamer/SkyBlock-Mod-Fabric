package net.skyguygamer.sbmod.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static net.skyguygamer.sbmod.SbMod.*;
import static net.skyguygamer.sbmod.config.Config.*;

@Mixin(ChatHud.class)
public class ChatMixin {
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"), cancellable = true)
    private void sbmod$onChatRecieved(Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) throws IOException {
        String incMessage = message.getString();
        if (message.getString().toLowerCase().contains("hello")) {
            ci.cancel();
            LOGGER.info("I have blocked a message!");
        }
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
        //Message logger credits to MagikIsAMush
        if (toggleMessageLogs && (incMessage.startsWith("[me -> ") || (incMessage.startsWith("[") && incMessage.contains(" -> ")))) {
            if (fileNameOfMessageLogs == "") {
                Date dateNow = new Date(); // On trouve la date
                SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy'-'HH.mm.ss");

                fileNameOfMessageLogs = "sbmod/messagelogs/" + MinecraftClient.getInstance().player.getGameProfile().getName() + "_" + formatDate.format(dateNow) + ".txt";

                myObjMessageLogs = new BufferedWriter(new FileWriter(fileNameOfMessageLogs));
            }
            Date msgDate = new Date();
            SimpleDateFormat ft2 = new SimpleDateFormat("dd'-'HH:mm:ss");
            myObjMessageLogs.write("[" + ft2.format(msgDate) + "] " + incMessage);
            myObjMessageLogs.newLine();
            myObjMessageLogs.flush();
        }
        //Trade logger
        if (toggleTradeLogs && ((incMessage.contains(" bought ") && incMessage.contains(" from you for ")) || (incMessage.contains("You bought ") && incMessage.contains(" from ") && incMessage.contains(" for ")))) {
            if (fileNameOfTradeLogs == "") {
                Date dateNow = new Date(); // On trouve la date
                SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy'-'HH.mm.ss");

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
    /*@ModifyVariable (method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"))
    private Text changeVariable(Text message) {
        if (message.getString().toLowerCase().contains("gay")) {
            message = Text.literal("no u");
            return message;
        }
        return message;
    }
     */
}
