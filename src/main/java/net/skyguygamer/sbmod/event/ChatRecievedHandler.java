package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;

public class ChatRecievedHandler implements ServerMessageEvents.ChatMessage {
    @Override
    public void onChatMessage(SignedMessage message, ServerPlayerEntity sender, MessageType.Parameters params) {
        //params.;
    }
}
