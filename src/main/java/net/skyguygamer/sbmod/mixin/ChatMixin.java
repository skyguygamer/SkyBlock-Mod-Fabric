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
            if (fileName == "") {
                Date dateNow = new Date(); // On trouve la date
                SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy'-'HH.mm.ss");

                fileName = "sbmod/messagelogs/" + MinecraftClient.getInstance().player.getGameProfile().getName() + "_" + formatDate.format(dateNow) + ".txt";

                myObj = new BufferedWriter(new FileWriter(fileName));
            }
            Date msgDate = new Date();
            SimpleDateFormat ft2 = new SimpleDateFormat("dd'-'HH:mm:ss");
            myObj.write("[" + ft2.format(msgDate) + "] " + incMessage);
            myObj.newLine();
            myObj.flush();
        }
    }
    @ModifyVariable (method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"))
    private Text changeVariable(Text message) {
        if (message.getString().toLowerCase().contains("gay")) {
            message = Text.literal("no u");
            return message;
        }
        return message;
    }
}
