package net.skyguygamer.sbmod.mixin;


import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.skyguygamer.sbmod.SbMod.LOGGER;

@Mixin(ChatHud.class)
public class ChatMixin {
    String message1;


    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"), cancellable = true)
    private void sbmod$onChatRecieved(Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) {
        if (message.getString().toLowerCase().contains("hello")) {
            ci.cancel();
            LOGGER.info("I have blocked a message!");
            message1 = message.getString();
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
