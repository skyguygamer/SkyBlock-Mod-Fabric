package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ChatRecievedHandler implements ServerMessageEvents.AllowChatMessage {


    @Override
    public boolean allowChatMessage(SignedMessage message, ServerPlayerEntity sender, MessageType.Parameters params) {
        if (message.getContent().contains(Text.literal("d"))) {
            return false;
        } else {
            return true;
        }
    }
}
