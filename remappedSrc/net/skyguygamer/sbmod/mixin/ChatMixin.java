package net.skyguygamer.sbmod.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.entity.MovementType;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.skyguygamer.sbmod.sounds.ModSounds;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static net.skyguygamer.sbmod.SbMod.*;
import static net.skyguygamer.sbmod.config.Config.*;

@Mixin(ChatHud.class)
public class ChatMixin {
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"), cancellable = true)
    private void sbmod$onChatRecieved(Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) throws IOException {
        String incMessage = message.getString();
        //Anti afk
        if (toggleChatAfk && incMessage.contains("You ") && incMessage.endsWith("move!") && incMessage.contains("You cannot chat until you move!")) {
            Vec3d movement = MinecraftClient.getInstance().player.getRotationVector().multiply(1.0, 0.0, 1.0).normalize();
            MinecraftClient.getInstance().player.move(MovementType.PLAYER, new Vec3d(movement.x, 0.0, movement.z));
            LOGGER.info("[SBMOD] Anti afk walking one block forward. Also resetting advert timer");
            advertTimer = 100000;
        }
        //MOTD IN CHAT WHEN VISITING
        if (toggleMotds && incMessage.startsWith("+ ")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Skyblock tips
        if (toggleTips && incMessage.contains("[Skyblock]") && incMessage.startsWith("[S")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Advancements
        if (toggleAdvancements && incMessage.contains(" has made the advancement ") && incMessage.endsWith("]")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //New player welcome message
        if (toggleNewPlayerWelcome && incMessage.startsWith("Welcome") && incMessage.contains("to Skyblock!")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Player voted
        if (togglePlayerVoted && incMessage.contains("voted at vote.skyblock.net for") && incMessage.endsWith("/vote")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Voting rewards
        if (toggleVoterRewards && (incMessage.contains("was lucky and received an extra") || incMessage.contains("was lucky and received a")) && incMessage.endsWith("!")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Friend joins
        if (toggleFriendJoin && incMessage.startsWith("[Friends] ") && incMessage.contains(" has joined ")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Perish message
        if (togglePlayersPerishedInVoid && incMessage.endsWith(" players have perished in the void today.")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Vote party rewards
        if (toggleVPRewards && incMessage.contains("[VoteParty] Your vote rewards have been applied!")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Clear lag
        if (toggleClag && (incMessage.contains("WARNING Ground items will be removed in") || incMessage.contains("[SB] Removed "))) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Toggle off the hover game
        if (toggleHoverGame && incMessage.startsWith("[") && (incMessage.endsWith(" Hover for the word to type!") || incMessage.endsWith(" Hover for the word to unscramble!"))) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //AutoBuy
        if (autoBuy && incMessage.startsWith("[SBLottery] Congratulations go to ") && incMessage.contains(" for winning ") && incMessage.contains(" Grass with ") && incMessage.endsWith(" tickets")) {
            MinecraftClient.getInstance().player.sendCommand("lottery buy " + lotteryTickets);
        }
        //Toggle off lottery
        if (toggleLottery && incMessage.startsWith("[SBLottery]")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Toggle bans
        if (toggleBans && incMessage.startsWith("[SkyblockBans]")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
        }
        //Toggle crates
        if (toggleCrates && incMessage.contains(" has just opened a ")) {
            ci.cancel();
            LOGGER.info("[SBMOD] I have blocked: " + incMessage);
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


        if (incMessage.contains("unscrambled the word") || incMessage.contains("typed the word") || incMessage.contains("The word was")) {
            String WordToSave = "";
            // Trouve les mot qui ont ete taper dans les 'Hover for the word'
            if (incMessage.contains("unscrambled the word")) {
                WordToSave = StringUtils.substringBetween(incMessage, "unscrambled the word ", " in");
            }
            else if (incMessage.contains("typed the word")) {
                WordToSave = StringUtils.substringBetween(incMessage, "typed the word ", " in");
            }
            else if (incMessage.contains("The word was")){
                WordToSave = StringUtils.substringAfter(incMessage, "The word was ");
            }
            // Enregistre le mot s'il n'a pas deja ete enregistre
            if (!motListe.contains(WordToSave)) {
                motListe.add(WordToSave); // Ajoute le mot a la liste pour ne pas a avoir a repartir minecraft
                char[] input = WordToSave.toCharArray(); // Separation du mot en caratere
                Arrays.sort(input); // Triage du mot en ordre alphabetique
                String sorted = new String(input); // Mot qui est trie en ordre alphabetique
                mapwords.put(sorted, WordToSave); // mise dans le hashmap du mot trie (Key) et du mot normal (Value)
                // Trouve le fichier dans le quel ecrire
                myObjWord = new BufferedWriter(new FileWriter("sbmod/skychatwords.txt", true));
                myObjWord.write(WordToSave + ""); // Ecris dans le fichier
                myObjWord.newLine(); // Saute une ligne
                myObjWord.flush(); // Ferme le fichier
            }
        }
    }

    @ModifyVariable(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"))
    private Text changeVariable(Text message) {
        String incMessage = message.getString();
        //Toggle notify
        if (toggleNotify && (incMessage.toLowerCase().contains(MinecraftClient.getInstance().player.getName().getString().toLowerCase()) || incMessage.toLowerCase().contains(extraNotifyWord))) {
            //LOGGER.info("[SBMOD] Name is " + MinecraftClient.getInstance().player.getName().getString().toLowerCase());
            MinecraftClient.getInstance().player.playSound(ModSounds.NOTIFY_SOUND,1f,1f);
            //message = Text.literal("Sound is being played");
        }
        if(hoverHack) {
            if (incMessage.startsWith("[") && incMessage.endsWith(" Hover for the word to type!")) {
                Text hoverMsg = message.getStyle().getHoverEvent().getValue(HoverEvent.Action.SHOW_TEXT);
                Style style = Style.EMPTY;
                style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, hoverMsg.getString()));
                message = Text.literal(Formatting.AQUA + "[✎] Type the word " + Formatting.GREEN + hoverMsg.getString()).setStyle(style);
            } else if (incMessage.startsWith("[") && incMessage.endsWith(" Hover for the word to unscramble!")) {
                Text hoverMsg = message.getStyle().getHoverEvent().getValue(HoverEvent.Action.SHOW_TEXT);
                String hoverMsgString = hoverMsg.getString();


                char[] str = hoverMsgString.toCharArray(); // Separation du mot en caratere
                Arrays.sort(str); // Triage du mot en ordre alphabetique
                String sorted = new String(str); // Mot qui est trie en ordre alphabetique
                if (mapwords.get(sorted) == null) {
                    MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.RED + "The word isn't know, adding now!"));
                } else {
                    String word = (mapwords.get(sorted));
                    Style style = Style.EMPTY;
                    style = style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, word));
                    message = Text.literal(Formatting.AQUA + "[✎] Unscramble the word " + Formatting.GREEN + word).setStyle(style);
                }
            }
        }
        if(timeChat) {
            Date msgDate = new Date();
            SimpleDateFormat ft2 = new SimpleDateFormat("HH:mm:ss");
            MutableText text = (Text.literal(Formatting.GRAY + "[" + ft2.format(msgDate) + "] "));
            text.append(message);
            message = text;
        }
        return message;
    }

}
