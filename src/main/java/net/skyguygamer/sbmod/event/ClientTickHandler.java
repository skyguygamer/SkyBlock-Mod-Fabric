package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.skyguygamer.sbmod.commands.AutoAdvert;
import net.skyguygamer.sbmod.commands.AutoPrivate;
import net.skyguygamer.sbmod.commands.AutoSpawnMob;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.skyguygamer.sbmod.SbMod.*;
import static net.skyguygamer.sbmod.config.Config.*;

public class ClientTickHandler implements ClientTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftClient client) {
        ClientPlayerEntity lp = MinecraftClient.getInstance().player;
        //Login message
        if (loggedInToWorld) {
            if (!loggedOn && !(MinecraftClient.getInstance().player == null)) {
                if (!welcomeMsg && !toggleWelcomeMessage) {
                    if (welcomeMessageTime >= 100) {
                        StringBuilder boarder = new StringBuilder();
                        for (int i = 0; i < 20; i++) {
                            boarder.append("§a-");
                            boarder.append("§2=");
                        }
                        LOGGER.info(modNames.toString());
                        Style style2 = Style.EMPTY;
                       // style2 = style2.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "d"));
                        Style style = Style.EMPTY;
                        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://valid-climber-350022.web.app/sbmod.html"));
                        lp.sendMessage((Text.literal(boarder + "§a-")));
                        lp.sendMessage((Text.literal("§7Skyblock Mod for fabric 1.19.2")));
                        lp.sendMessage((Text.literal("§7Updated version 3.2")));
                        lp.sendMessage((Text.literal("§7Type /shelp for list of commands")));
                        lp.sendMessage((Text.literal("§7Click here for website")).fillStyle(style));
                        if (!latestVersion) {
                            style = style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/skyguygamer/SkyBlock-Mod-Fabric/releases"));
                            lp.sendMessage(Text.literal(Formatting.RED + "A new version is available! " + Formatting.DARK_RED + "Click here").setStyle(style));
                            LOGGER.warn("[SBMOD] New version available https://github.com/skyguygamer/SkyBlock-Mod-Fabric/releases");
                        }
                        lp.sendMessage((Text.literal(boarder + "§a-")));
                        welcomeMsg = true;
                    }
                }

                //Announcement
                if(announcements && announcementTick >= 200) {
                    String announcement = getStringFromSite("https://valid-climber-350022.web.app/announcement.txt");
                    LOGGER.info("[SBMOD] Announcement is: " + announcement);
                    if (!Objects.equals(announcement, "N/A")) {
                        if (announcementTick >= 200 && !announcementSent) {
                            LOGGER.info("[SBMOD] Sending announcement");
                            lp.sendMessage(Text.literal(Formatting.GREEN + "[SBMOD]: " + Formatting.WHITE + announcement), true);
                            announcementSent = true;
                            announcementTick = 0;
                        }
                    }
                } else if (!announcementSent && announcements) {
                    announcementTick++;
                }

                if (joinCommands) {
                    List<String> commands = new ArrayList<>();
                    try {
                        BufferedReader jclist = new BufferedReader(new FileReader("sbmod/joincommands.txt"));
                        String line;
                        while ((line = jclist.readLine()) != null) {
                            commands.add(line);
                        }
                        jclist.close();
                    } catch (Exception ignored) {
                    }
                    for (int i = 0; i < commands.size(); i++) {
                        if (welcomeMessageTime == (i * 100) + 100) {
                            lp.sendCommand(commands.get(i));
                            loggedOn = true;
                        } else {

                            loggedOn = false;
                        }
                    }
                    if (!loggedOn) {
                        welcomeMessageTime++;
                    }
                }
            }
        }
        //Update Staff List Every 5 Minutes
        if (staffCheck && updateStaffList > 6000) {
            modNames = getUUIDsFromURL();
            updateStaffList = 0;
        } else {
            updateStaffList++;
        }
        //Staffcheck
        if (loggedInToWorld && staffCheck) {
            if (playerCheckTime == 100) {
                onlinePlayers = new ArrayList<>(List.of());
                for (PlayerListEntry player : MinecraftClient.getInstance().getNetworkHandler().getPlayerList()) {
                    String playerUuid = player.getProfile().getId().toString();
                    String name = player.getProfile().getName();
                    onlinePlayers.add(playerUuid);
                    if ((modNames.contains(playerUuid) || extraStaffNames.contains(playerUuid)) && !onlineStaffUuids.containsKey(playerUuid)) {
                        MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GREEN + player.getProfile().getName() + Formatting.DARK_GREEN + " has joined the server!"));
                        onlineStaffUuids.put(playerUuid, name);
                    }
                    playerCheckTime = 0;
                }
                //Check offline staff
                if (!onlineStaffUuids.isEmpty()) {
                    ArrayList<String> tempList = new ArrayList<>();
                    for (String staffUuid : onlineStaffUuids.keySet()) {
                        if (!onlinePlayers.contains(staffUuid)) {
                            String name = onlineStaffUuids.get(staffUuid);
                            MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GREEN + name + Formatting.DARK_GREEN + " is no longer online!"));
                            tempList.add(staffUuid);
                        }
                    }
                    onlineStaffUuids.keySet().removeAll(tempList);
                }
            }
            playerCheckTime++;
        }
        //Safe from certain death (Checks every half a second)
        if (saveDeath && loggedInToWorld) {
            if (deathTime > 5) {
                if (lp.getY() < -66) {
                    LOGGER.info("[SBMOD] Saving you from certain death!");
                    lp.sendCommand("is");
                    deathTime = 0;
                }
                if (lp.isInLava()) {
                    LOGGER.info("[SBMOD] Saving you from certain death!");
                    lp.sendCommand("is");
                    deathTime = 0;
                }
                if (!deathHealthCooldown && (lp.getHealth() < 4)) {
                    LOGGER.info("[SBMOD] Saving you from certain death!");
                    deathHealthCooldown = true;
                    lp.sendCommand("is");
                    deathTime = 0;
                } else if (lp.getHealth() > 4) {
                    deathHealthCooldown = false;
                }
            } else {
                deathTime++;
            }
        }
        //AutoSell
        if (autoSell) {
            if (autoSellTime >= 910) {
                LOGGER.info("[SBMOD] Selling");
                lp.sendCommand("sell all");
                autoSellTime = 0;
            }
            autoSellTime++;
        }
        //AutoPrivate
        if (printMsg) {
            if (printMsgTimer >= 10) {
                if (autoPrivate) {
                    convertText(AutoPrivate.text);
                }
                printMsg = false;
                printMsgTimer = 0;
            } else {
                printMsgTimer++;
            }
        }
        //AutoSpawnMob
        if (spawnMobs) {
            if (spawnTime >= 620) {
                LOGGER.info("[SBMOD] Spawning mob");
                lp.sendCommand(AutoSpawnMob.command);
                spawnTime = 0;
            }
            spawnTime++;
        }
        //AutoFix
        if (autoFix && !coolDown) {

            ItemStack item = MinecraftClient.getInstance().player.getMainHandStack();
            float percent = 100 * (((float) item.getMaxDamage() - (float) item.getDamage()) / (float) item.getMaxDamage());
            try {
                if (percent < 25 && autoFix && item.isDamageable() && !coolDown) {
                    LOGGER.info("[SBMOD] Fixing");
                    lp.sendCommand("fix all");
                    coolDown = true;
                }
            } catch (Exception e) {
            }
        }
        //Cool down for AutoFix
        if (coolDown) {
            if (coolDownCounter >= 24020) {
                coolDown = false;
                coolDownCounter = 0;
            }
            coolDownCounter++;
        }

        //AutoAdvert
        if (AutoAdvert.sendingMessages) {
            if (advertTimer >= interval) {
                LOGGER.info("[SBMOD] Adverting");
                lp.sendChatMessage(AutoAdvert.message, Text.literal(""));
                advertTimer = 0;
            }
            advertTimer++;
        }
        //AutoEnchant in hand
        if (enchantInHand) {
            ItemStack item = MinecraftClient.getInstance().player.getMainHandStack();
            if (item.isEnchantable() && EnchantmentHelper.get(item).isEmpty() && !enchant) {
                LOGGER.info("[SBMOD] AutoEnchanting cause item in da hand");
                if (eIHTimer >= 2) {
                    eIHTimer = 0;
                    MinecraftClient.getInstance().player.sendCommand("enchantall");
                } else {
                    eIHTimer++;
                }
            }
        }
        //AutoEnchanting
        try {
            if (pressTime == 0 && enchantAxe) {
                lp.sendCommand("enchant sharpness 5");
            } else if (pressTime == 15 && enchantAxe) {
                lp.sendCommand("enchant smite 5");
            } else if (pressTime == 30 && enchantAxe) {
                lp.sendCommand("enchant baneofarthropods 5");
            } else if (pressTime == 45 && enchantAxe) {
                lp.sendCommand("enchant efficiency 5");
            } else if (pressTime == 60 && enchantAxe) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 75 && enchantAxe) {
                lp.sendCommand("enchant fortune 3");
            } else if (pressTime == 90 && enchantAxe) {
                lp.sendCommand("enchant mending 1");
                enchantAxe = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantSword) {
                lp.sendCommand("enchant sharpness 5");
            } else if (pressTime == 15 && enchantSword) {
                lp.sendCommand("enchant smite 5");
            } else if (pressTime == 30 && enchantSword) {
                lp.sendCommand("enchant baneofarthropods 5");
            } else if (pressTime == 45 && enchantSword) {
                lp.sendCommand("enchant fireaspect 2");
            } else if (pressTime == 60 && enchantSword) {
                lp.sendCommand("enchant looting 3");
            } else if (pressTime == 75 && enchantSword) {
                lp.sendCommand("enchant knockback 2");
            } else if (pressTime == 90 && enchantSword) {
                lp.sendCommand("enchant sweepingedge 3");
            } else if (pressTime == 105 && enchantSword) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 120 && enchantSword) {
                lp.sendCommand("enchant mending 1");
                enchantSword = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantTool) {
                lp.sendCommand("enchant efficiency 5");
            } else if (pressTime == 15 && enchantTool) {
                lp.sendCommand("enchant unbreaking  3");
            } else if (pressTime == 30 && enchantTool) {
                lp.sendCommand("enchant mending 1");
            } else if (pressTime == 45 && enchantTool) {
                lp.sendCommand("enchant fortune 3");
                enchantTool = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantLeggings) {
                lp.sendCommand("enchant protection 4");
            } else if (pressTime == 15 && enchantLeggings) {
                lp.sendCommand("enchant fireprotection 4");
            } else if (pressTime == 30 && enchantLeggings) {
                lp.sendCommand("enchant blastprotection 4");
            } else if (pressTime == 45 && enchantLeggings) {
                lp.sendCommand("enchant projectileprotection 4");
            } else if (pressTime == 60 && enchantLeggings) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 75 && enchantLeggings) {
                lp.sendCommand("enchant thorns 3");
            } else if (pressTime == 90 && enchantLeggings) {
                lp.sendCommand("enchant mending 1");
            } else if (pressTime == 105 && enchantLeggings) {
                lp.sendCommand("enchant soulspeed 3");
                enchantLeggings = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantChest) {
                lp.sendCommand("enchant protection 4");
            } else if (pressTime == 15 && enchantChest) {
                lp.sendCommand("enchant fireprotection 4");
            } else if (pressTime == 30 && enchantChest) {
                lp.sendCommand("enchant blastprotection 4");
            } else if (pressTime == 45 && enchantChest) {
                lp.sendCommand("enchant projectileprotection 4");
            } else if (pressTime == 60 && enchantChest) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 75 && enchantChest) {
                lp.sendCommand("enchant thorns 3");
            } else if (pressTime == 90 && enchantChest) {
                lp.sendCommand("enchant mending 1");
                enchantChest = false;
                enchant = false;
            }

            if (pressTime == 0 && enchantBow) {
                lp.sendCommand("enchant power 5");
            } else if (pressTime == 15 && enchantBow) {
                lp.sendCommand("enchant punch 2");
            } else if (pressTime == 30 && enchantBow) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 45 && enchantBow) {
                lp.sendCommand("enchant flame 1");
            } else if (pressTime == 60 && enchantBow) {
                lp.sendCommand("enchant mending 1");
            } else if (pressTime == 75 && enchantBow) {
                lp.sendCommand("enchant infinity 1");
                enchantBow = false;
                enchant = false;
            }

            if (pressTime == 0 && enchantHelmet) {
                lp.sendCommand("enchant protection 4");
            } else if (pressTime == 15 && enchantHelmet) {
                lp.sendCommand("enchant fireprotection 4");
            } else if (pressTime == 30 && enchantHelmet) {
                lp.sendCommand("enchant blastprotection 4");
            } else if (pressTime == 45 && enchantHelmet) {
                lp.sendCommand("enchant projectileprotection 4");
            } else if (pressTime == 60 && enchantHelmet) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 75 && enchantHelmet) {
                lp.sendCommand("enchant thorns 3");
            } else if (pressTime == 90 && enchantHelmet) {
                lp.sendCommand("enchant mending 1");
            } else if (pressTime == 105 && enchantHelmet) {
                lp.sendCommand("enchant respiration 3");
            } else if (pressTime == 120 && enchantHelmet) {
                lp.sendCommand("enchant aquaaffinity 1");
                enchantHelmet = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantBoots) {
                lp.sendCommand("enchant protection 4");
            } else if (pressTime == 15 && enchantBoots) {
                lp.sendCommand("enchant fireprotection 4");
            } else if (pressTime == 30 && enchantBoots) {
                lp.sendCommand("enchant blastprotection 4");
            } else if (pressTime == 45 && enchantBoots) {
                lp.sendCommand("enchant projectileprotection 4");
            } else if (pressTime == 60 && enchantBoots) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 75 && enchantBoots) {
                lp.sendCommand("enchant thorns 3");
            } else if (pressTime == 90 && enchantBoots) {
                lp.sendCommand("enchant mending 1");
            } else if (pressTime == 105 && enchantBoots) {
                lp.sendCommand("enchant depth_strider 3");
            } else if (pressTime == 120 && enchantBoots) {
                lp.sendCommand("enchant featherfalling 4");
                enchantBoots = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantRod) {
                lp.sendCommand("enchant lure 3");
            } else if (pressTime == 15 && enchantRod) {
                lp.sendCommand("enchant luck 3");
            } else if (pressTime == 30 && enchantRod) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 45 && enchantRod) {
                lp.sendCommand("enchant mending 1");
                enchantRod = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantTrident) {
                lp.sendCommand("enchant impaling 5");
            } else if (pressTime == 15 && enchantTrident) {
                lp.sendCommand("enchant loyalty 3");
            } else if (pressTime == 30 && enchantTrident) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 45 && enchantTrident) {
                lp.sendCommand("enchant mending");
            } else if (pressTime == 60 && enchantTrident) {
                lp.sendCommand("enchant channelling");
            } else if (pressTime == 75 && enchantTrident) {
                lp.sendCommand("enchant riptide 3");
                enchantTrident = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantCrossbow) {
                lp.sendCommand("enchant multishot 1");
            } else if (pressTime == 15 && enchantCrossbow) {
                lp.sendCommand("enchant quickcharge 3");
            } else if (pressTime == 30 && enchantCrossbow) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 45 && enchantCrossbow) {
                lp.sendCommand("enchant mending");
            } else if (pressTime == 60 && enchantCrossbow) {
                lp.sendCommand("enchant piercing 3");
                enchantCrossbow = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantHoe) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 15 && enchantHoe) {
                lp.sendCommand("enchant mending 1");
            } else if (pressTime == 30 && enchantHoe) {
                lp.sendCommand("enchant fortune 3");
                enchantHoe = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantOther) {
                lp.sendCommand("enchant unbreaking 3");
            } else if (pressTime == 15 && enchantOther) {
                lp.sendCommand("enchant mending 1");
                enchantOther = false;
                enchant = false;
            }

            if (enchant) {
                pressTime++;
            }
        } catch (Exception e) {
        }

        //Auto Unenchant
        try {
            if (pressTime == 0 && unEnchantAxe) {
                lp.sendCommand("enchant sharpness 0");
            } else if (pressTime == 15 && unEnchantAxe) {
                lp.sendCommand("enchant smite 0");
            } else if (pressTime == 30 && unEnchantAxe) {
                lp.sendCommand("enchant baneofarthropods 0");
            } else if (pressTime == 45 && unEnchantAxe) {
                lp.sendCommand("enchant efficiency 0");
            } else if (pressTime == 60 && unEnchantAxe) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 75 && unEnchantAxe) {
                lp.sendCommand("enchant fortune 0");
            } else if (pressTime == 90 && unEnchantAxe) {
                lp.sendCommand("enchant mending 0");
                unEnchantAxe = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantSword) {
                lp.sendCommand("enchant sharpness 0");
            } else if (pressTime == 15 && unEnchantSword) {
                lp.sendCommand("enchant smite 0");
            } else if (pressTime == 30 && unEnchantSword) {
                lp.sendCommand("enchant baneofarthropods 0");
            } else if (pressTime == 45 && unEnchantSword) {
                lp.sendCommand("enchant fireaspect 0");
            } else if (pressTime == 60 && unEnchantSword) {
                lp.sendCommand("enchant looting 0");
            } else if (pressTime == 75 && unEnchantSword) {
                lp.sendCommand("enchant knockback 0");
            } else if (pressTime == 90 && unEnchantSword) {
                lp.sendCommand("enchant sweepingedge 0");
            } else if (pressTime == 105 && unEnchantSword) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 120 && unEnchantSword) {
                lp.sendCommand("enchant mending 0");
                unEnchantSword = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantTool) {
                lp.sendCommand("enchant efficiency 0");
            } else if (pressTime == 15 && unEnchantTool) {
                lp.sendCommand("enchant unbreaking  0");
            } else if (pressTime == 30 && unEnchantTool) {
                lp.sendCommand("enchant mending 0");
            } else if (pressTime == 45 && unEnchantTool) {
                lp.sendCommand("enchant fortune 0");
                unEnchantTool = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantLeggings) {
                lp.sendCommand("enchant protection 0");
            } else if (pressTime == 15 && unEnchantLeggings) {
                lp.sendCommand("enchant fireprotection 0");
            } else if (pressTime == 30 && unEnchantLeggings) {
                lp.sendCommand("enchant blastprotection 0");
            } else if (pressTime == 45 && unEnchantLeggings) {
                lp.sendCommand("enchant projectileprotection 0");
            } else if (pressTime == 60 && unEnchantLeggings) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 75 && unEnchantLeggings) {
                lp.sendCommand("enchant thorns 0");
            } else if (pressTime == 90 && unEnchantLeggings) {
                lp.sendCommand("enchant mending 0");
            } else if (pressTime == 105 && unEnchantLeggings) {
                lp.sendCommand("enchant soulspeed 0");
                unEnchantLeggings = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantChest) {
                lp.sendCommand("enchant protection 0");
            } else if (pressTime == 15 && unEnchantChest) {
                lp.sendCommand("enchant fireprotection 0");
            } else if (pressTime == 30 && unEnchantChest) {
                lp.sendCommand("enchant blastprotection 0");
            } else if (pressTime == 45 && unEnchantChest) {
                lp.sendCommand("enchant projectileprotection 0");
            } else if (pressTime == 60 && unEnchantChest) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 75 && unEnchantChest) {
                lp.sendCommand("enchant thorns 0");
            } else if (pressTime == 90 && unEnchantChest) {
                lp.sendCommand("enchant mending 0");
                unEnchantChest = false;
                unEnchant = false;
            }

            if (pressTime == 0 && unEnchantBow) {
                lp.sendCommand("enchant power 0");
            } else if (pressTime == 15 && unEnchantBow) {
                lp.sendCommand("enchant punch 0");
            } else if (pressTime == 30 && unEnchantBow) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 45 && unEnchantBow) {
                lp.sendCommand("enchant flame 0");
            } else if (pressTime == 60 && unEnchantBow) {
                lp.sendCommand("enchant mending 0");
            } else if (pressTime == 75 && unEnchantBow) {
                lp.sendCommand("enchant infinity 0");
                unEnchantBow = false;
                unEnchant = false;
            }

            if (pressTime == 0 && unEnchantHelmet) {
                lp.sendCommand("enchant protection 0");
            } else if (pressTime == 15 && unEnchantHelmet) {
                lp.sendCommand("enchant fireprotection 0");
            } else if (pressTime == 30 && unEnchantHelmet) {
                lp.sendCommand("enchant blastprotection 0");
            } else if (pressTime == 45 && unEnchantHelmet) {
                lp.sendCommand("enchant projectileprotection 0");
            } else if (pressTime == 60 && unEnchantHelmet) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 75 && unEnchantHelmet) {
                lp.sendCommand("enchant thorns 0");
            } else if (pressTime == 90 && unEnchantHelmet) {
                lp.sendCommand("enchant mending 0");
            } else if (pressTime == 105 && unEnchantHelmet) {
                lp.sendCommand("enchant respiration 0");
            } else if (pressTime == 120 && unEnchantHelmet) {
                lp.sendCommand("enchant aquaaffinity 0");
                unEnchantHelmet = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantBoots) {
                lp.sendCommand("enchant protection 0");
            } else if (pressTime == 15 && unEnchantBoots) {
                lp.sendCommand("enchant fireprotection 0");
            } else if (pressTime == 30 && unEnchantBoots) {
                lp.sendCommand("enchant blastprotection 0");
            } else if (pressTime == 45 && unEnchantBoots) {
                lp.sendCommand("enchant projectileprotection 0");
            } else if (pressTime == 60 && unEnchantBoots) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 75 && unEnchantBoots) {
                lp.sendCommand("enchant thorns 0");
            } else if (pressTime == 90 && unEnchantBoots) {
                lp.sendCommand("enchant mending 0");
            } else if (pressTime == 105 && unEnchantBoots) {
                lp.sendCommand("enchant depth_strider 0");
            } else if (pressTime == 120 && unEnchantBoots) {
                lp.sendCommand("enchant featherfalling 0");
            } else if (pressTime == 135 && unEnchantBoots) {
                lp.sendCommand("enchant soulspeed 0");
                unEnchantBoots = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantRod) {
                lp.sendCommand("enchant lure 0");
            } else if (pressTime == 15 && unEnchantRod) {
                lp.sendCommand("enchant luck 0");
            } else if (pressTime == 30 && unEnchantRod) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 45 && unEnchantRod) {
                lp.sendCommand("enchant mending 0");
                unEnchantRod = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantTrident) {
                lp.sendCommand("enchant impaling 0");
            } else if (pressTime == 15 && unEnchantTrident) {
                lp.sendCommand("enchant loyalty 0");
            } else if (pressTime == 30 && unEnchantTrident) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 45 && unEnchantTrident) {
                lp.sendCommand("enchant mending 0");
            } else if (pressTime == 60 && unEnchantTrident) {
                lp.sendCommand("enchant channelling 0");
            } else if (pressTime == 75 && unEnchantTrident) {
                lp.sendCommand("enchant riptide 0");
                unEnchantTrident = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantCrossbow) {
                lp.sendCommand("enchant multishot 0");
            } else if (pressTime == 15 && unEnchantCrossbow) {
                lp.sendCommand("enchant quickcharge 0");
            } else if (pressTime == 30 && unEnchantCrossbow) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 45 && unEnchantCrossbow) {
                lp.sendCommand("enchant mending 0");
            } else if (pressTime == 60 && unEnchantCrossbow) {
                lp.sendCommand("enchant piercing 0");
                unEnchantCrossbow = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantHoes) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 15 && unEnchantHoes) {
                lp.sendCommand("enchant mending 0");
            } else if (pressTime == 30 && unEnchantHoes) {
                lp.sendCommand("enchant fortune 0");
                unEnchantHoes = false;
                unEnchant = false;
            }

            if (pressTime == 0 && unEnchantOther) {
                lp.sendCommand("enchant unbreaking 0");
            } else if (pressTime == 15 && unEnchantOther) {
                lp.sendCommand("enchant mending 0");
                unEnchantOther = false;
                unEnchant = false;
            }

            if (unEnchant) {
                pressTime++;
            }
        } catch (Exception e) {
        }
    }
}

