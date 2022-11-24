package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.realms.dto.PlayerInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.skyguygamer.sbmod.SbMod;
import net.skyguygamer.sbmod.commands.AutoAdvert;
import net.skyguygamer.sbmod.commands.AutoBuyTemp;
import net.skyguygamer.sbmod.commands.AutoPrivate;
import net.skyguygamer.sbmod.commands.AutoSpawnMob;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.skyguygamer.sbmod.SbMod.*;

public class ClientTickHandler implements ClientTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftClient client) {

        ClientPlayerEntity lp = MinecraftClient.getInstance().player;
        //List<? extends PlayerEntity> onlinePlayers = lp.getWorld().getPlayers();
        //if(playerCheckTime >= 20)
        //for(PlayerEntity p : MinecraftClient.getInstance().player.getWorld().getPlayers()) {
        //    String playerName = p.getUuidAsString();
         //   if()
        //} else {
        //    playerCheckTime++;
        //}
        //Login message
        if (loggedInToWorld) {
            if (!loggedOn && !(MinecraftClient.getInstance().player == null)) {
                if (!welcomeMsg) {
                    if (welcomeMessageTime >= 100) {
                        String boarder = "";
                        for (int i = 0; i < 20; i++) {
                            boarder += "§a-";
                            boarder += "§2=";
                        }
                        lp.sendMessage((Text.literal(boarder + "§a-")));
                        lp.sendMessage((Text.literal("§7Skyblock Mod for fabric 1.19.2")));
                        lp.sendMessage((Text.literal("§7Updated version 3.0.2 §cBETA")));
                        lp.sendMessage((Text.literal("§7Type /shelp for list of commands")));
                        lp.sendMessage((Text.literal(boarder + "§a-")));
                        welcomeMsg = true;

                    }
                }
                List<String> commands = new ArrayList<String>();
                try {
                    BufferedReader jclist = new BufferedReader(new FileReader("joincommands.txt"));
                    String line;
                    while ((line = jclist.readLine()) != null) {
                        commands.add(line);
                    }
                    jclist.close();
                } catch (Exception e) {
                    PrintWriter writer;
                    try {
                        writer = new PrintWriter("joincommands.txt", "UTF-8");
                    } catch (FileNotFoundException | UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
                for (int i = 0; i < commands.size(); i++) {
                    if (welcomeMessageTime == (i*100)+100) {
                        //lp.sendChatMessage(commands.get(i), Text.literal(""));
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
        //AutoPrivate
        if (printMsg) {
            if (printMsgTimer >= 10) {
                if (autoPrivate) {
                    convertText(AutoPrivate.text);
                }
                printMsg = false;
                printMsgTimer = 0;
            }
            else {
                printMsgTimer ++;
            }
        }
        //AutoSpawnMob
        if (spawnMobs) {
            if (spawnTime >= 620) {
                lp.sendCommand(AutoSpawnMob.command);
                spawnTime = 0;
            }
            spawnTime++;
        }


        //ClientConnection d = MinecraftClient.getInstance().player.networkHandler.getConnection();


        //AutoFix
        if (autoFix && !coolDown) {

            ItemStack item = MinecraftClient.getInstance().player.getMainHandStack();
            float percent = 100*(((float) item.getMaxDamage() - (float) item.getDamage())/ (float) item.getMaxDamage());
            try {
                if (percent < 25 && autoFix && item.isDamageable() && !coolDown) {
                    lp.sendCommand("fix all", Text.literal(""));
                    coolDown = true;
                }
            } catch (Exception e) {}
        }
        //Cool down for AutoFix
        if (coolDown) {
            if (coolDownCounter >= 24020) {
                coolDown = false;
                coolDownCounter = 0;
            }
            coolDownCounter ++;
        }
        //AutoBuyTEMP
        if (autoBuy) {
            if (autoBuyTime >= 36000) {
                lp.sendCommand("lottery buy " + ticketAmount);
                autoBuyTime = 0;
            }
            autoBuyTime++;
        }
        //AutoAdvert
        if (AutoAdvert.sendingMessages) {
            if (advertTimer >= AutoAdvert.interval) {

                lp.sendChatMessage(AutoAdvert.message, Text.literal(""));
                advertTimer = 0;
            }
            advertTimer++;
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
            }
            else if (pressTime == 15 && enchantSword) {
                lp.sendCommand("enchant smite 5");
            }
            else if (pressTime == 30 && enchantSword) {
                lp.sendCommand("enchant baneofarthropods 5");
            }
            else if (pressTime == 45 && enchantSword) {
                lp.sendCommand("enchant fireaspect 2");
            }
            else if (pressTime == 60 && enchantSword) {
                lp.sendCommand("enchant looting 3");
            }
            else if (pressTime == 75 && enchantSword) {
                lp.sendCommand("enchant knockback 2");
            }
            else if (pressTime == 90 && enchantSword) {
                lp.sendCommand("enchant sweepingedge 3");
            }
            else if (pressTime == 105 && enchantSword) {
                lp.sendCommand("enchant unbreaking 3");
            }
            else if (pressTime == 120 && enchantSword) {
                lp.sendCommand("enchant mending 1");
                enchantSword = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantTool) {
                lp.sendCommand("enchant efficiency 5");
            }
            else if (pressTime == 15 && enchantTool) {
                lp.sendCommand("enchant unbreaking  3");
            }
            else if (pressTime == 30 && enchantTool) {
                lp.sendCommand("enchant mending 1");
            }
            else if (pressTime == 45 && enchantTool) {
                lp.sendCommand("enchant fortune 3");
                enchantTool = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantChest) {
                lp.sendCommand("enchant protection 4");
            }
            else if (pressTime == 15 && enchantChest) {
                lp.sendCommand("enchant fireprotection 4");
            }
            else if (pressTime == 30 && enchantChest) {
                lp.sendCommand("enchant blastprotection 4");
            }
            else if (pressTime == 45 && enchantChest) {
                lp.sendCommand("enchant projectileprotection 4");
            }
            else if (pressTime == 60 && enchantChest) {
                lp.sendCommand("enchant unbreaking 3");
            }
            else if (pressTime == 75 && enchantChest) {
                lp.sendCommand("enchant thorns 3");
            }
            else if (pressTime == 90 && enchantChest) {
                lp.sendCommand("enchant mending 1");
                enchantChest = false;
                enchant = false;
            }

            if (pressTime == 0 && enchantBow) {
                lp.sendCommand("enchant power 5");
            }
            else if (pressTime == 15 && enchantBow) {
                lp.sendCommand("enchant punch 2");
            }
            else if (pressTime == 30 && enchantBow) {
                lp.sendCommand("enchant unbreaking 3");
            }
            else if (pressTime == 45 && enchantBow) {
                lp.sendCommand("enchant flame 1");
            }
            else if (pressTime == 60 && enchantBow) {
                lp.sendCommand("enchant mending 1");
            }
            else if (pressTime == 75 && enchantBow) {
                lp.sendCommand("enchant infinity 1");
                enchantBow = false;
                enchant = false;
            }

            if (pressTime == 0 && enchantHelmet) {
                lp.sendCommand("enchant protection 4");
            }
            else if (pressTime == 15 && enchantHelmet) {
                lp.sendCommand("enchant fireprotection 4");
            }
            else if (pressTime == 30 && enchantHelmet) {
                lp.sendCommand("enchant blastprotection 4");
            }
            else if (pressTime == 45 && enchantHelmet) {
                lp.sendCommand("enchant projectileprotection 4");
            }
            else if (pressTime == 60 && enchantHelmet) {
                lp.sendCommand("enchant unbreaking 3");
            }
            else if (pressTime == 75 && enchantHelmet) {
                lp.sendCommand("enchant thorns 3");
            }
            else if (pressTime == 90 && enchantHelmet) {
                lp.sendCommand("enchant mending 1");
            }
            else if (pressTime == 105 && enchantHelmet) {
                lp.sendCommand("enchant respiration 3");
            }
            else if (pressTime == 120 && enchantHelmet) {
                lp.sendCommand("enchant aquaaffinity 1");
                enchantHelmet = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantBoots) {
                lp.sendCommand("enchant protection 4");
            }
            else if (pressTime == 15 && enchantBoots) {
                lp.sendCommand("enchant fireprotection 4");
            }
            else if (pressTime == 30 && enchantBoots) {
                lp.sendCommand("enchant blastprotection 4");
            }
            else if (pressTime == 45 && enchantBoots) {
                lp.sendCommand("enchant projectileprotection 4");
            }
            else if (pressTime == 60 && enchantBoots) {
                lp.sendCommand("enchant unbreaking 3");
            }
            else if (pressTime == 75 && enchantBoots) {
                lp.sendCommand("enchant thorns 3");
            }
            else if (pressTime == 90 && enchantBoots) {
                lp.sendCommand("enchant mending 1");
            }
            else if (pressTime == 105 && enchantBoots) {
                lp.sendCommand("enchant depth_strider 3");
            }
            else if (pressTime == 120 && enchantBoots) {
                lp.sendCommand("enchant featherfalling 4");
                enchantBoots = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantRod) {
                lp.sendCommand("enchant lure 3");
            }
            else if (pressTime == 15 && enchantRod) {
                lp.sendCommand("enchant luck 3");
            }
            else if (pressTime == 30 && enchantRod) {
                lp.sendCommand("enchant unbreaking 3");
            }
            else if (pressTime == 45 && enchantRod) {
                lp.sendCommand("enchant mending 1");
                enchantRod = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantOther) {
                lp.sendCommand("enchant unbreaking 3");
            }
            else if (pressTime == 15 && enchantOther) {
                lp.sendCommand("enchant mending 1");
                enchantOther = false;
                enchant = false;
            }

            if (enchant) {
                pressTime++;
            }
        } catch (Exception e) {}

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
            }
            else if (pressTime == 15 && unEnchantSword) {
                lp.sendCommand("enchant smite 0");
            }
            else if (pressTime == 30 && unEnchantSword) {
                lp.sendCommand("enchant baneofarthropods 0");
            }
            else if (pressTime == 45 && unEnchantSword) {
                lp.sendCommand("enchant fireaspect 0");
            }
            else if (pressTime == 60 && unEnchantSword) {
                lp.sendCommand("enchant looting 0");
            }
            else if (pressTime == 75 && unEnchantSword) {
                lp.sendCommand("enchant knockback 0");
            }
            else if (pressTime == 90 && unEnchantSword) {
                lp.sendCommand("enchant sweepingedge 0");
            }
            else if (pressTime == 105 && unEnchantSword) {
                lp.sendCommand("enchant unbreaking 0");
            }
            else if (pressTime == 120 && unEnchantSword) {
                lp.sendCommand("enchant mending 0");
                unEnchantSword = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantTool) {
                lp.sendCommand("enchant efficiency 0");
            }
            else if (pressTime == 15 && unEnchantTool) {
                lp.sendCommand("enchant unbreaking  0");
            }
            else if (pressTime == 30 && unEnchantTool) {
                lp.sendCommand("enchant mending 0");
            }
            else if (pressTime == 45 && unEnchantTool) {
                lp.sendCommand("enchant fortune 0");
                unEnchantTool = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantChest) {
                lp.sendCommand("enchant protection 0");
            }
            else if (pressTime == 15 && unEnchantChest) {
                lp.sendCommand("enchant fireprotection 0");
            }
            else if (pressTime == 30 && unEnchantChest) {
                lp.sendCommand("enchant blastprotection 0");
            }
            else if (pressTime == 45 && unEnchantChest) {
                lp.sendCommand("enchant projectileprotection 0");
            }
            else if (pressTime == 60 && unEnchantChest) {
                lp.sendCommand("enchant unbreaking 0");
            }
            else if (pressTime == 75 && unEnchantChest) {
                lp.sendCommand("enchant thorns 0");
            }
            else if (pressTime == 90 && unEnchantChest) {
                lp.sendCommand("enchant mending 0");
                unEnchantChest = false;
                unEnchant = false;
            }

            if (pressTime == 0 && unEnchantBow) {
                lp.sendCommand("enchant power 0");
            }
            else if (pressTime == 15 && unEnchantBow) {
                lp.sendCommand("enchant punch 0");
            }
            else if (pressTime == 30 && unEnchantBow) {
                lp.sendCommand("enchant unbreaking 0");
            }
            else if (pressTime == 45 && unEnchantBow) {
                lp.sendCommand("enchant flame 0");
            }
            else if (pressTime == 60 && unEnchantBow) {
                lp.sendCommand("enchant mending 0");
            }
            else if (pressTime == 75 && unEnchantBow) {
                lp.sendCommand("enchant infinity 0");
                unEnchantBow = false;
                unEnchant = false;
            }

            if (pressTime == 0 && unEnchantHelmet) {
                lp.sendCommand("enchant protection 0");
            }
            else if (pressTime == 15 && unEnchantHelmet) {
                lp.sendCommand("enchant fireprotection 0");
            }
            else if (pressTime == 30 && unEnchantHelmet) {
                lp.sendCommand("enchant blastprotection 0");
            }
            else if (pressTime == 45 && unEnchantHelmet) {
                lp.sendCommand("enchant projectileprotection 0");
            }
            else if (pressTime == 60 && unEnchantHelmet) {
                lp.sendCommand("enchant unbreaking 0");
            }
            else if (pressTime == 75 && unEnchantHelmet) {
                lp.sendCommand("enchant thorns 0");
            }
            else if (pressTime == 90 && unEnchantHelmet) {
                lp.sendCommand("enchant mending 0");
            }
            else if (pressTime == 105 && unEnchantHelmet) {
                lp.sendCommand("enchant respiration 0");
            }
            else if (pressTime == 120 && unEnchantHelmet) {
                lp.sendCommand("enchant aquaaffinity 0");
                unEnchantHelmet = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantBoots) {
                lp.sendCommand("enchant protection 0");
            }
            else if (pressTime == 15 && unEnchantBoots) {
                lp.sendCommand("enchant fireprotection 0");
            }
            else if (pressTime == 30 && unEnchantBoots) {
                lp.sendCommand("enchant blastprotection 0");
            }
            else if (pressTime == 45 && unEnchantBoots) {
                lp.sendCommand("enchant projectileprotection 0");
            }
            else if (pressTime == 60 && unEnchantBoots) {
                lp.sendCommand("enchant unbreaking 0");
            }
            else if (pressTime == 75 && unEnchantBoots) {
                lp.sendCommand("enchant thorns 0");
            }
            else if (pressTime == 90 && unEnchantBoots) {
                lp.sendCommand("enchant mending 0");
            }
            else if (pressTime == 105 && unEnchantBoots) {
                lp.sendCommand("enchant depth_strider 0");
            }
            else if (pressTime == 120 && unEnchantBoots) {
                lp.sendCommand("enchant featherfalling 0");
                unEnchantBoots = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantRod) {
                lp.sendCommand("enchant lure 0");
            }
            else if (pressTime == 15 && unEnchantRod) {
                lp.sendCommand("enchant luck 0");
            }
            else if (pressTime == 30 && unEnchantRod) {
                lp.sendCommand("enchant unbreaking 0");
            }
            else if (pressTime == 45 && unEnchantRod) {
                lp.sendCommand("enchant mending 0");
                unEnchantRod = false;
                unEnchant = false;
            }
            if (pressTime == 0 && unEnchantOther) {
                lp.sendCommand("enchant unbreaking 0");
            }
            else if (pressTime == 15 && unEnchantOther) {
                lp.sendCommand("enchant mending 0");
                unEnchantOther = false;
                unEnchant = false;
            }

            if (unEnchant) {
                pressTime++;
            }
        } catch (Exception e) {}
    }
}
