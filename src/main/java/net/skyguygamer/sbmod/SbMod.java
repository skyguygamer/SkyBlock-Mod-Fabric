//NOTEs
//PER UPDATE YOU MUST UPDATE THE VERSIONS
//HERE, WELCOME MESSAGE, FABRIC.MOD.JSON and GRADLE.PROPERTIES!!
//AND OF COURSE YOUR SITE
package net.skyguygamer.sbmod;


import com.google.gson.*;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.skyguygamer.sbmod.commands.*;
import net.skyguygamer.sbmod.config.Config;
import net.skyguygamer.sbmod.event.*;
import net.skyguygamer.sbmod.sounds.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class SbMod implements ModInitializer {
	public static final String SBMOD_ID = "sbmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(SBMOD_ID);

	//NOTE
	//PER UPDATE YOU MUST UPDATE THE VERSIONS
	//HERE, WELCOME MESSAGE, FABRIC.MOD.JSON and GRADLE.PROPERTIES!!
	public static String versionNumber;
	public static String version = "v3.0.5-1.19.2";
	public static boolean latestVersion;
	public static String sponsors = "";

	public static ArrayList<String> motListe = new ArrayList<>();
	public static HashMap<String, String> mapwords;
	public static BufferedWriter myObjWord;
	public static BufferedReader myList;

	public static Boolean loggingIn = false;
	public static Boolean loggedOn = false;
	//public static boolean autoBuy = false;
	public static boolean autoFix = false;
	public static boolean autoPrivate = false;
	public static boolean autoSell = false;
	public static boolean coolDown = false;
	public static boolean enchant = false;
	public static boolean enchantAxe = false;
	public static boolean enchantBoots = false;
	public static boolean enchantBow = false;
	public static boolean enchantChest = false;
	public static boolean enchantCrossbow = false;
	public static boolean enchantHelmet = false;
	public static boolean enchantInHand = false;
	public static boolean enchantOther = false;
	public static boolean enchantRod = false;
	public static boolean enchantSword = false;
	public static boolean enchantTool = false;
	public static boolean enchantTrident = false;
	public static boolean loggedInToWorld = false;
	public static boolean playsound = false;
	public static boolean printMsg = false;
	public static boolean spawnMobs = false;
	public static boolean unEnchant = false;
	public static boolean unEnchantAxe = false;
	public static boolean unEnchantBoots = false;
	public static boolean unEnchantBow = false;
	public static boolean unEnchantChest = false;
	public static boolean unEnchantCrossbow = false;
	public static boolean unEnchantHelmet = false;
	public static boolean unEnchantOther = false;
	public static boolean unEnchantRod = false;
	public static boolean unEnchantSword = false;
	public static boolean unEnchantTool = false;
	public static boolean unEnchantTrident = false;
	public static boolean welcomeMsg = false;
	public static boolean deathHealthCooldown = false;
	public static boolean firstMailSent = false;
	public static int eIHTimer = 0;
	public static int advertTimer = 0;
	public static int autoBuyTime = 0;
	public static int autoSellTime = 0;
	public static int coolDownCounter = 0;
	public static int playerCheckTime = 0;
	public static int pressTime = 0;
	public static int printMsgTimer = 0;
	public static int spawnTime = 0;
	public static int time = 0;
	public static int welcomeMessageTime = 0;
	public static int ticketAmount = 2;
	public static int updateStaffList = 0;
	public static int announcementTick = 0;
	public static int deathTime = 0;
	public static boolean announcementSent = false;


	//Message logs
	public static String fileNameOfMessageLogs = "";
	public static BufferedWriter myObjMessageLogs;

	//Trade logs
	public static String fileNameOfTradeLogs = "";
	public static BufferedWriter myObjTradeLogs;

	//Staff checks
	public static Map<String, String> onlineStaffUuids = new HashMap<>();
	public static ArrayList<String> onlinePlayers = new ArrayList<>(List.of());
	public static ArrayList<String> modNames = new ArrayList<>(List.of());
	/*public static ArrayList<String> modNames = new ArrayList<>(Arrays.asList(
			//Helpers
			"82df7471-8ad2-4f16-a3d8-31dd09628b8f", "6df79345-a001-4ccc-9104-e1c2df361c70", "22206b45-7d3e-429c-b339-e0c1629110db", "bd94c577-dc7d-4bfc-bf36-0262cf821441",
			//Mods
			"baf7d328-2859-4544-932e-8a4be2a5b3cf", "6652961c-c798-4eff-a441-8b9f99e3c219", "591ea57f-960f-4d53-95ee-ee648c3310b2", "83e6e7e1-1538-4abb-a8c2-67edf15d8e66", "4eb2eeb5-25c1-44c0-adfe-f66765e571fc", "b638643f-59bb-4b9c-97e1-18c7053554f6", "1e62cb3a-ac61-45b2-bcd1-8c3f3f46d5bf", "4ead00c5-a4a6-4612-9260-797331ab70ef", "767b5628-0796-4bab-9cfa-c983e840f3dc", "751b17e1-51ac-46f1-853a-b79cc388078b",
			//SuperMods/Dev
			"afccf682-3351-4ada-a6be-275bed81bd81", "6f34f308-99f6-423f-aca8-d6d47aa3b031", "dc94537e-8e48-468c-ae82-7c394a1da358", "b6521802-d0b6-4a9d-b052-105a949f93f5",
			//Staff manager and Owner
			"0051de0c-a908-4eeb-ac30-502dbd8a9694", "1ba2d16f-3d11-4a1f-b214-09e83906e6b5",
			//Admin (JustMatt)
			"5aaf78c9-07f9-4d55-b9ea-ab5be34c0bee"));
	 */

	//Name
	public static String username;
	@Override
	public void onInitialize() {
		//Sounds
		ModSounds.registerSounds();
		//Username
		username = MinecraftClient.getInstance().getName().toLowerCase();
		LOGGER.info("Ign: " + username);


		/*try	{
			if (motListe.isEmpty()){
				String Line;
				mapwords = new HashMap<>();
				myList = new BufferedReader(new FileReader("sbmod/skychatwords.txt"));
				Line = myList.readLine();
				while (Line != null){
					motListe.add(Line);
					Line = myList.readLine();
				}
				for (int i = 0; i < motListe.size(); i++) {
					String unsorted = motListe.get(i);
					char[] str = unsorted.toCharArray();
					Arrays.sort(str);
					String sorted = new String(str);
					if (motListe != null) {
						mapwords.put(sorted, unsorted);
					}
				}
			}
		} catch (Exception ignored){}
		 */



		modNames = getListFromSite("https://valid-climber-350022.web.app/sbmodstafflist.json");
		LOGGER.info(String.valueOf(modNames));
		versionNumber = getStringFromSite("https://valid-climber-350022.web.app/sbmodversion.txt");
		if(Objects.equals(versionNumber, version)) {
			LOGGER.info("Version is " + versionNumber);
			LOGGER.info("Latest version");
			latestVersion = true;
		} else {
			LOGGER.warn("New version available https://github.com/skyguygamer/SkyBlock-Mod-Fabric/releases");
			latestVersion = false;
		}

		//Sbmod folder
		try {
			Files.createDirectories(Path.of("sbmod"));
			//Joincommand txt
			new File("sbmod/joincommands.txt");

			//Trade logs
			Files.createDirectories(Path.of("sbmod/tradelogs"));

			//Message logs
			Files.createDirectories(Path.of("sbmod/messagelogs"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		MidnightConfig.init("sbmod", Config.class);

		UseBlockCallback.EVENT.register(new BlockPlaceHandler());
		ClientTickEvents.START_CLIENT_TICK.register(new ClientTickHandler());
		ClientPlayConnectionEvents.DISCONNECT.register(new LogOutHandler());
		ClientPlayConnectionEvents.JOIN.register(new LogInHandler());

		//Registers commands
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			AutoAdvert.register(dispatcher);
			AutoBuyTemp.register(dispatcher);
			AutoEnchantInHand.register(dispatcher);
			AutoFix.register(dispatcher);
			//AutoPrivate.register(dispatcher);
			AutoSell.register(dispatcher);
			AutoSpawnMob.register(dispatcher);
			CalcCommand.register(dispatcher);
			CommandAliases.register((dispatcher));
			DiscordLink.register(dispatcher);
			Divide64.register(dispatcher);
			EnchantAllCommand.register((dispatcher));
			//EnchantAllInInventory.register(dispatcher);
			FakeHelpCommand.register(dispatcher);
			HelloCommand.register(dispatcher);
			HelpCommand.register(dispatcher);
			//HoverHelper.register(dispatcher);
			JoinCommand.register(dispatcher);
			SBFolder.register(dispatcher);
			Sponsor.register(dispatcher);
			RefreshTimers.register(dispatcher);
			StaffNotifications.register(dispatcher);
			ToggleChats.register(dispatcher);
			UnEnchantAllCommand.register(dispatcher);
			//SetPrefix.register(dispatcher);
		});
	}

	//Gets version number
	public static String getStringFromSite(String urlString) {
		try {
			// Create a URL object with the URL of the text file
			URL url = new URL(urlString);

			// Open a connection to the web server
			URLConnection conn = url.openConnection();

			// Obtain an InputStream from the connection
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			// Use a BufferedReader to read the InputStream
			BufferedReader reader = new BufferedReader(in);

			// Read the first line of the text file (the version number)
			String string = reader.readLine();

			// Close the reader and return the version number
			reader.close();
			return string;
		} catch (Exception e) {
			// If any errors occurred, print an error message and return null
			System.err.println("An error occurred while downloading the version number: " + e.getMessage());
			return null;
		}
	}
	//Staff list
	public static ArrayList<String> getListFromSite(String urlString) {
		try {
			// Retrieve the JSON file from the online source
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			reader.close();
			connection.disconnect();
			String json = stringBuilder.toString();

			// Parse the JSON file
			JsonObject root = JsonParser.parseString(json).getAsJsonObject();

			// Retrieve the data you want and store it in an ArrayList<String>
			JsonArray array = root.getAsJsonArray("modnames");
			ArrayList<String> itemList = new ArrayList<>();
			for (int i = 0; i < array.size(); i++) {
				JsonObject item = array.get(i).getAsJsonObject();
				String itemName = item.get("name").getAsString();
				itemList.add(itemName);
			}
			return itemList;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

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
}
