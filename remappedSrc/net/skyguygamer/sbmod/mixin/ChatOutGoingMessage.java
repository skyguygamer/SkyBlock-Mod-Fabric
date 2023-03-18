package net.skyguygamer.sbmod.mixin;

import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ChatOutGoingMessage {
    @Inject(at = @At("HEAD"), method = "sendPacket(Lnet/minecraft/network/Packet;)V")
    private void onSendPacket(Packet<?> packet, CallbackInfo info) {
        if (packet instanceof ChatMessageC2SPacket chatPacket) {
            String message = "";
            try {
                // Get the message field using reflection
                java.lang.reflect.Field messageField = ChatMessageC2SPacket.class.getDeclaredField("message");
                messageField.setAccessible(true);
                message = (String) messageField.get(chatPacket);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (message.startsWith("/")) {
                // Do something with command messages
                System.out.println("Command message sent: " + message);
            } else {
                // Modify the chat message here
                String modifiedMessage = "Modified: " + message;

                // Do something with regular chat messages
                System.out.println("Chat message sent: " + modifiedMessage);

                // Create a new packet with the modified chat message
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                buf.writeString(modifiedMessage);
                ChatMessageC2SPacket modifiedPacket = new ChatMessageC2SPacket(buf);

                // Send the modified packet instead of the original packet
                MinecraftClient.getInstance().getNetworkHandler().sendPacket(modifiedPacket);
            }
        }
    }
}