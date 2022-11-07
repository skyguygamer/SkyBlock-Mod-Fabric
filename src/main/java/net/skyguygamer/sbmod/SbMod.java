package net.skyguygamer.sbmod;

import com.google.common.eventbus.Subscribe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.skyguygamer.sbmod.commands.*;
import net.skyguygamer.sbmod.config.ModConfigs;
import net.skyguygamer.sbmod.event.BlockPlaceHandler;
import net.skyguygamer.sbmod.event.ClientTickHandler;
import net.skyguygamer.sbmod.event.LogInHandler;
import net.skyguygamer.sbmod.event.LogOutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.EventHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SbMod implements ModInitializer {
	public static final String SBMOD_ID = "sbmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(SBMOD_ID);

	public static Boolean loggingIn = false;
	public static int pressTime = 0;
	public static int advertTimer = 0;
	public static Boolean loggedOn = false;
	public static int time = 0;
	public static int coolDownCounter = 0;
	public static boolean enchantSword = false;
	public static boolean playsound = false;
	public static boolean enchantTool = false;
	public static boolean enchantChest = false;
	public static boolean enchantBow = false;
	public static boolean enchantHelmet = false;
	public static boolean enchantBoots = false;
	public static boolean enchantRod = false;
	public static boolean enchantOther = false;
	public static boolean enchantAxe = false;
	public static boolean enchant = false;
	public static boolean autoFix = false;
	public static boolean autoPrivate = false;
	public static boolean coolDown = false;
	public static boolean printMsg = false;
	public static int printMsgTimer = 0;
	public static boolean spawnMobs = false;
	public static int spawnTime = 0;
	public static int welcomeMessageTime = 0;
	public static boolean loggedInToWorld = false;
	public static boolean welcomeMsg = false;
	public static int playerCheckTime = 0;
	public static ArrayList<String> onlineUuids = new ArrayList<>(Arrays.asList());
	public static ArrayList<String> modNames = new ArrayList<>(Arrays.asList("SkyGuyGamerz", "Meggann"));

	public static void convertText(String text) {
		for (int i = 0; i < text.length(); i ++) {
			String character = String.valueOf(text.charAt(i));

			try {
				//if(!GraphicsEnvironment.isHeadless()) {
				Robot robot = new Robot();
				robot.keyRelease(KeyEvent.VK_SHIFT);
				switch (character) {
					case "a" -> {
						robot.keyPress(KeyEvent.VK_A);
						robot.keyRelease(KeyEvent.VK_A);
					}
					case "b" -> {
						robot.keyPress(KeyEvent.VK_B);
						robot.keyRelease(KeyEvent.VK_B);
					}
					case "c" -> {
						robot.keyPress(KeyEvent.VK_C);
						robot.keyRelease(KeyEvent.VK_C);
					}
					case "d" -> {
						robot.keyPress(KeyEvent.VK_D);
						robot.keyRelease(KeyEvent.VK_D);
					}
					case "e" -> {
						robot.keyPress(KeyEvent.VK_E);
						robot.keyRelease(KeyEvent.VK_E);
					}
					case "f" -> {
						robot.keyPress(KeyEvent.VK_F);
						robot.keyRelease(KeyEvent.VK_F);
					}
					case "g" -> {
						robot.keyPress(KeyEvent.VK_G);
						robot.keyRelease(KeyEvent.VK_G);
					}
					case "h" -> {
						robot.keyPress(KeyEvent.VK_H);
						robot.keyRelease(KeyEvent.VK_H);
					}
					case "i" -> {
						robot.keyPress(KeyEvent.VK_I);
						robot.keyRelease(KeyEvent.VK_I);
					}
					case "j" -> {
						robot.keyPress(KeyEvent.VK_J);
						robot.keyRelease(KeyEvent.VK_J);
					}
					case "k" -> {
						robot.keyPress(KeyEvent.VK_K);
						robot.keyRelease(KeyEvent.VK_K);
					}
					case "l" -> {
						robot.keyPress(KeyEvent.VK_L);
						robot.keyRelease(KeyEvent.VK_L);
					}
					case "m" -> {
						robot.keyPress(KeyEvent.VK_M);
						robot.keyRelease(KeyEvent.VK_M);
					}
					case "n" -> {
						robot.keyPress(KeyEvent.VK_N);
						robot.keyRelease(KeyEvent.VK_N);
					}
					case "o" -> {
						robot.keyPress(KeyEvent.VK_O);
						robot.keyRelease(KeyEvent.VK_O);
					}
					case "p" -> {
						robot.keyPress(KeyEvent.VK_P);
						robot.keyRelease(KeyEvent.VK_P);
					}
					case "q" -> {
						robot.keyPress(KeyEvent.VK_Q);
						robot.keyRelease(KeyEvent.VK_Q);
					}
					case "r" -> {
						robot.keyPress(KeyEvent.VK_R);
						robot.keyRelease(KeyEvent.VK_R);
					}
					case "s" -> {
						robot.keyPress(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_S);
					}
					case "t" -> {
						robot.keyPress(KeyEvent.VK_T);
						robot.keyRelease(KeyEvent.VK_T);
					}
					case "u" -> {
						robot.keyPress(KeyEvent.VK_U);
						robot.keyRelease(KeyEvent.VK_U);
					}
					case "v" -> {
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
					}
					case "w" -> {
						robot.keyPress(KeyEvent.VK_W);
						robot.keyRelease(KeyEvent.VK_W);
					}
					case "x" -> {
						robot.keyPress(KeyEvent.VK_X);
						robot.keyRelease(KeyEvent.VK_X);
					}
					case "y" -> {
						robot.keyPress(KeyEvent.VK_Y);
						robot.keyRelease(KeyEvent.VK_Y);
					}
					case "z" -> {
						robot.keyPress(KeyEvent.VK_Z);
						robot.keyRelease(KeyEvent.VK_Z);
					}
					case "A" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_A);
						robot.keyRelease(KeyEvent.VK_A);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "B" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_B);
						robot.keyRelease(KeyEvent.VK_B);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "C" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_C);
						robot.keyRelease(KeyEvent.VK_C);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "D" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_D);
						robot.keyRelease(KeyEvent.VK_D);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "E" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_E);
						robot.keyRelease(KeyEvent.VK_E);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "F" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_F);
						robot.keyRelease(KeyEvent.VK_F);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "G" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_G);
						robot.keyRelease(KeyEvent.VK_G);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "H" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_H);
						robot.keyRelease(KeyEvent.VK_H);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "I" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_I);
						robot.keyRelease(KeyEvent.VK_I);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "J" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_J);
						robot.keyRelease(KeyEvent.VK_J);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "K" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_K);
						robot.keyRelease(KeyEvent.VK_K);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "L" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_L);
						robot.keyRelease(KeyEvent.VK_L);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "M" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_M);
						robot.keyRelease(KeyEvent.VK_M);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "N" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_N);
						robot.keyRelease(KeyEvent.VK_N);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "O" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_O);
						robot.keyRelease(KeyEvent.VK_O);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "P" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_P);
						robot.keyRelease(KeyEvent.VK_P);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "Q" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_Q);
						robot.keyRelease(KeyEvent.VK_Q);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "R" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_R);
						robot.keyRelease(KeyEvent.VK_R);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "S" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "T" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_T);
						robot.keyRelease(KeyEvent.VK_T);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "U" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_U);
						robot.keyRelease(KeyEvent.VK_U);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "V" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "W" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_W);
						robot.keyRelease(KeyEvent.VK_W);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "X" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_X);
						robot.keyRelease(KeyEvent.VK_X);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "Y" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_Y);
						robot.keyRelease(KeyEvent.VK_Y);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "Z" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_Z);
						robot.keyRelease(KeyEvent.VK_Z);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "0" -> {
						robot.keyPress(KeyEvent.VK_0);
						robot.keyRelease(KeyEvent.VK_0);
					}
					case "1" -> {
						robot.keyPress(KeyEvent.VK_1);
						robot.keyRelease(KeyEvent.VK_1);
					}
					case "2" -> {
						robot.keyPress(KeyEvent.VK_2);
						robot.keyRelease(KeyEvent.VK_2);
					}
					case "3" -> {
						robot.keyPress(KeyEvent.VK_3);
						robot.keyRelease(KeyEvent.VK_3);
					}
					case "4" -> {
						robot.keyPress(KeyEvent.VK_4);
						robot.keyRelease(KeyEvent.VK_4);
					}
					case "5" -> {
						robot.keyPress(KeyEvent.VK_5);
						robot.keyRelease(KeyEvent.VK_5);
					}
					case "6" -> {
						robot.keyPress(KeyEvent.VK_6);
						robot.keyRelease(KeyEvent.VK_6);
					}
					case "7" -> {
						robot.keyPress(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_7);
					}
					case "8" -> {
						robot.keyPress(KeyEvent.VK_8);
						robot.keyRelease(KeyEvent.VK_8);
					}
					case "9" -> {
						robot.keyPress(KeyEvent.VK_9);
						robot.keyRelease(KeyEvent.VK_9);
					}
					case "&" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "[" -> {
						robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
						robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
					}
					case "]" -> {
						robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
						robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
					}
					case "_" -> {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_SUBTRACT);
						robot.keyRelease(KeyEvent.VK_SUBTRACT);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
					case "\n" -> {
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
					}
					case "�" -> {
						robot.keyPress(KeyEvent.VK_ESCAPE);
						robot.keyRelease(KeyEvent.VK_ESCAPE);
					}
				}
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();

		//ClientTickEvents.START_CLIENT_TICK.register();
		UseBlockCallback.EVENT.register(new BlockPlaceHandler());
		ClientTickEvents.START_CLIENT_TICK.register(new ClientTickHandler());
		ClientPlayConnectionEvents.DISCONNECT.register(new LogOutHandler());
		ClientPlayConnectionEvents.JOIN.register(new LogInHandler());

		//Registers commands
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			HelloCommand.register(dispatcher);
			CalcCommand.register(dispatcher);
			Divide64.register(dispatcher);
			JoinCommand.register(dispatcher);
			HelpCommand.register(dispatcher);
			AutoAdvert.register(dispatcher);
			SetPrefix.register(dispatcher);
			EnchantAllCommand.register((dispatcher));
			AutoFix.register(dispatcher);
			CommandAliases.register((dispatcher));
			AutoPrivate.register(dispatcher);
			AutoSpawnMob.register(dispatcher);
			FakeHelpCommand.register(dispatcher);
		});

	}

}
