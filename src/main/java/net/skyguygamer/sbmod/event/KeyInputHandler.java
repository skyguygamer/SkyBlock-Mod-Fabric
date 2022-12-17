package net.skyguygamer.sbmod.event;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.skyguygamer.sbmod.SbMod;
import net.skyguygamer.sbmod.config.Config;
import org.lwjgl.glfw.GLFW;


public class KeyInputHandler {
    public static final String KEY_CATEGORY_SB = "key.category.sbmod.sb";
    public static final String KEY_JUMP_COMMAND = "key.sbmod.jump.command";
    public static final String KEY_CRAFT_COMMAND = "key.sbmod.craft.command";
    public static final String KEY_ENDERCHEST_COMMAND = "key.sbmod.enderchest.command";
    public static final String KEY_HOME_COMMAND = "key.sbmod.home.command";
    public static final String KEY_BACK_COMMAND = "key.sbmod.back.command";
    public static final String KEY_EHOME_COMMAND = "key.sbmod.ehome.command";
    public static final String KEY_CONFIG = "key.sbmod.config";
    public static KeyBinding craftKey;
    public static KeyBinding ehomeKey;
    public static KeyBinding backKey;
    public static KeyBinding ecKey;
    public static KeyBinding homeKey;
    public static KeyBinding jumpKey;
    public static KeyBinding configKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(ehomeKey.wasPressed()) {
                client.player.sendCommand("ehome");
            }
            if(craftKey.wasPressed()) {
                client.player.sendCommand("craft");
            }
            if(jumpKey.wasPressed()) {
                client.player.sendCommand("jump");
            }
            if(jumpKey.wasPressed()) {
                client.player.sendCommand("ec");
            }
            if(homeKey.wasPressed()) {
                client.player.sendCommand("home");
            }
            if(backKey.wasPressed()) {
                client.player.sendCommand("back");
            }
            if(configKey.wasPressed()) {
                //MidnightConfig.getScreen(null   , "sbmod");
                MinecraftClient.getInstance().setScreen(MidnightConfig.getScreen(null   , "sbmod"));
            }
        });
    }

    public static void register() {
        jumpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_JUMP_COMMAND,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_SB
        ));
        craftKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CRAFT_COMMAND,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_SB
        ));
        ecKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ENDERCHEST_COMMAND,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_SB
        ));
        homeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_HOME_COMMAND,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_SB
        ));
        backKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_BACK_COMMAND,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_SB
        ));
        ehomeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_EHOME_COMMAND,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY_SB
        ));
        configKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CONFIG,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                KEY_CATEGORY_SB
        ));
        registerKeyInputs();
    }
}
