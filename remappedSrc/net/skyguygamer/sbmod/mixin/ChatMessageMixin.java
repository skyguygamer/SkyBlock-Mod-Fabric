package net.skyguygamer.sbmod.mixin;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.StringVisitable;

import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


/*@Mixin(ClientPlayNetworkHandler.class)
public abstract class ChatMessageMixin {
    @Final
    private final MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at = @At("HEAD"), method = "onChatMessage")
    private void onChatMessage(ChatMessageS2CPacket packet, CallbackInfo ci) {
        //Text originalMessage = packet.getChatMessage();
        UUID senderUUID = packet.message().signedHeader().sender();
        PlayerEntity sender = client.world.getPlayerByUuid(senderUUID);
        String senderName = sender.getName().toString();
        //Text newMessage = new LiteralText(senderName + ": ")
         //       .formatted(Formatting.GRAY)
        //        .append(originalMessage);

        //client.inGameHud.addChatMessage(MessageType.CHAT, newMessage, senderUUID);
        System.out.println(senderName);
    }
}
*/
@Mixin(NarratorM)
public abstract class {
    @Final
    private MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at = @At("RETURN"), method = "onGameMessage")

}