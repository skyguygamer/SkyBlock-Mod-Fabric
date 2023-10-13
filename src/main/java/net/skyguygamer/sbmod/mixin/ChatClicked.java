/*package net.skyguygamer.sbmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.skyguygamer.sbmod.config.Config.copyChat;

@Mixin(ChatHud.class)
public class ChatClicked {

    private boolean yourVariable = false;

    @Inject(method = "onClick", at = @At("HEAD"))
    private void onChatMessageClicked(int x, int y, CallbackInfo ci) {
        if (copyChat) {
            MinecraftClient.getInstance().player.sendMessage(Text.literal("Copied!"), true);
        }
    }
}

 */
