package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.skyguygamer.sbmod.commands.AutoAdvert;
import net.skyguygamer.sbmod.commands.AutoPrivate;
import net.skyguygamer.sbmod.commands.AutoSpawnMob;
import net.skyguygamer.sbmod.config.ModConfigs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static net.skyguygamer.sbmod.SbMod.*;

public class ClientTickHandler implements ClientTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftClient client) {
        ClientPlayerEntity lp = MinecraftClient.getInstance().player;
        //Login message
        if (loggedInToWorld) {
            if (!loggedOn) {
                if (!welcomeMsg) {
                    if (welcomeMessageTime >= 100) {
                        String boarder = "";
                        for (int i = 0; i < 20; i++) {
                            boarder += "§a-";
                            boarder += "§2=";
                        }
                        lp.sendMessage((Text.literal(boarder + "§a-")), false);
                        lp.sendMessage((Text.literal("§7Skyblock Mod for forge 1.19.2")), false);
                        lp.sendMessage((Text.literal("§7Updated version 3.0 §cBETA")), false);
                        lp.sendMessage((Text.literal("§7Type /shelp for list of commands")), false);
                        lp.sendMessage((Text.literal(boarder + "§a-")), false);
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
                System.out.println(commands);
                System.out.println(commands.size());
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
        try {
            if (spawnMobs) {
                if (spawnTime >= 1500) {
                    lp.sendChatMessage(AutoSpawnMob.command, Text.literal(""));
                    spawnTime = 0;
                }
                spawnTime++;
            }
        } catch (Exception e) {
            ;
        }
        //AutoFix
        if (autoFix && !coolDown) {

            ItemStack item = MinecraftClient.getInstance().player.getMainHandStack();
            float percent = 100*(((float) item.getMaxDamage() - (float) item.getDamage())/ (float) item.getMaxDamage());
            try {
                if (percent < 25 && autoFix && item.isDamageable() && !coolDown) {
                    lp.sendChatMessage("/fix all", Text.literal(""));
                    coolDown = true;
                }
            } catch (Exception e) {}
        }
        //Cool down for AutoFix
        if (coolDown) {
            if (coolDownCounter >= 48040) {
                coolDown = false;
                coolDownCounter = 0;
            }
            coolDownCounter ++;
        }
        //AutoAdvert
        if (AutoAdvert.sendingmessages) {
            if (advertTimer >= AutoAdvert.interval) {

                lp.sendChatMessage(AutoAdvert.message, Text.literal(""));
                advertTimer = 0;
            }
            advertTimer++;
        }
        //AutoEnchanting
        try {
            if (pressTime == 0 && enchantAxe) {
                lp.sendChatMessage("/enchant sharpness 5", Text.literal(""));
            } else if (pressTime == 40 && enchantAxe) {
                lp.sendChatMessage("/enchant smite 5", Text.literal(""));
            } else if (pressTime == 80 && enchantAxe) {
                lp.sendChatMessage("/enchant baneofarthropods 5", Text.literal(""));
            } else if (pressTime == 120 && enchantAxe) {
                lp.sendChatMessage("/enchant efficiency 5", Text.literal(""));
            } else if (pressTime == 160 && enchantAxe) {
                lp.sendChatMessage("/enchant unbreaking 3", Text.literal(""));
            } else if (pressTime == 200 && enchantAxe) {
                lp.sendChatMessage("/enchant fortune 3", Text.literal(""));
            } else if (pressTime == 240 && enchantAxe) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
                enchantAxe = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantSword) {
                lp.sendChatMessage("/enchant sharpness 5", Text.literal(""));
            }
            else if (pressTime == 40 && enchantSword) {
                lp.sendChatMessage("/enchant smite 5", Text.literal(""));
            }
            else if (pressTime == 80 && enchantSword) {
                lp.sendChatMessage("/enchant baneofarthropods 5", Text.literal(""));
            }
            else if (pressTime == 120 && enchantSword) {
                lp.sendChatMessage("/enchant fireaspect 2", Text.literal(""));
            }
            else if (pressTime == 160 && enchantSword) {
                lp.sendChatMessage("/enchant looting 3", Text.literal(""));
            }
            else if (pressTime == 200 && enchantSword) {
                lp.sendChatMessage("/enchant knockback 2", Text.literal(""));
            }
            else if (pressTime == 240 && enchantSword) {
                lp.sendChatMessage("/enchant sweepingedge 3", Text.literal(""));
            }
            else if (pressTime == 280 && enchantSword) {
                lp.sendChatMessage("/enchant unbreaking 3", Text.literal(""));
            }
            else if (pressTime == 320 && enchantSword) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
                enchantSword = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantTool) {
                lp.sendChatMessage("/enchant efficiency 5", Text.literal(""));
            }
            else if (pressTime == 40 && enchantTool) {
                lp.sendChatMessage("/enchant unbreaking  3", Text.literal(""));
            }
            else if (pressTime == 80 && enchantTool) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
            }
            else if (pressTime == 120 && enchantTool) {
                lp.sendChatMessage("/enchant fortune 3", Text.literal(""));
                enchantTool = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantChest) {
                lp.sendChatMessage("/enchant protection 4", Text.literal(""));
            }
            else if (pressTime == 40 && enchantChest) {
                lp.sendChatMessage("/enchant fireprotection 4", Text.literal(""));
            }
            else if (pressTime == 80 && enchantChest) {
                lp.sendChatMessage("/enchant blastprotection 4", Text.literal(""));
            }
            else if (pressTime == 120 && enchantChest) {
                lp.sendChatMessage("/enchant projectileprotection 4", Text.literal(""));
            }
            else if (pressTime == 160 && enchantChest) {
                lp.sendChatMessage("/enchant unbreaking 3", Text.literal(""));
            }
            else if (pressTime == 200 && enchantChest) {
                lp.sendChatMessage("/enchant thorns 3", Text.literal(""));
            }
            else if (pressTime == 240 && enchantChest) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
                enchantChest = false;
                enchant = false;
            }

            if (pressTime == 0 && enchantBow) {
                lp.sendChatMessage("/enchant power 5", Text.literal(""));
            }
            else if (pressTime == 40 && enchantBow) {
                lp.sendChatMessage("/enchant punch 2", Text.literal(""));
            }
            else if (pressTime == 80 && enchantBow) {
                lp.sendChatMessage("/enchant unbreaking 3", Text.literal(""));
            }
            else if (pressTime == 120 && enchantBow) {
                lp.sendChatMessage("/enchant flame 1", Text.literal(""));
            }
            else if (pressTime == 160 && enchantBow) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
            }
            else if (pressTime == 200 && enchantBow) {
                lp.sendChatMessage("/enchant infinity 1", Text.literal(""));
                enchantBow = false;
                enchant = false;
            }

            if (pressTime == 0 && enchantHelmet) {
                lp.sendChatMessage("/enchant protection 4", Text.literal(""));
            }
            else if (pressTime == 40 && enchantHelmet) {
                lp.sendChatMessage("/enchant fireprotection 4", Text.literal(""));
            }
            else if (pressTime == 80 && enchantHelmet) {
                lp.sendChatMessage("/enchant blastprotection 4", Text.literal(""));
            }
            else if (pressTime == 120 && enchantHelmet) {
                lp.sendChatMessage("/enchant projectileprotection 4", Text.literal(""));
            }
            else if (pressTime == 160 && enchantHelmet) {
                lp.sendChatMessage("/enchant unbreaking 3", Text.literal(""));
            }
            else if (pressTime == 200 && enchantHelmet) {
                lp.sendChatMessage("/enchant thorns 3", Text.literal(""));
            }
            else if (pressTime == 240 && enchantHelmet) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
            }
            else if (pressTime == 280 && enchantHelmet) {
                lp.sendChatMessage("/enchant respiration 3", Text.literal(""));
            }
            else if (pressTime == 320 && enchantHelmet) {
                lp.sendChatMessage("/enchant aquaaffinity 1", Text.literal(""));
                enchantHelmet = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantBoots) {
                lp.sendChatMessage("/enchant protection 4", Text.literal(""));
            }
            else if (pressTime == 40 && enchantBoots) {
                lp.sendChatMessage("/enchant fireprotection 4", Text.literal(""));
            }
            else if (pressTime == 80 && enchantBoots) {
                lp.sendChatMessage("/enchant blastprotection 4", Text.literal(""));
            }
            else if (pressTime == 120 && enchantBoots) {
                lp.sendChatMessage("/enchant projectileprotection 4", Text.literal(""));
            }
            else if (pressTime == 160 && enchantBoots) {
                lp.sendChatMessage("/enchant unbreaking 3", Text.literal(""));
            }
            else if (pressTime == 200 && enchantBoots) {
                lp.sendChatMessage("/enchant thorns 3", Text.literal(""));
            }
            else if (pressTime == 240 && enchantBoots) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
            }
            else if (pressTime == 280 && enchantBoots) {
                lp.sendChatMessage("/enchant depth_strider 3", Text.literal(""));
            }
            else if (pressTime == 320 && enchantBoots) {
                lp.sendChatMessage("/enchant featherfalling 4", Text.literal(""));
                enchantBoots = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantRod) {
                lp.sendChatMessage("/enchant lure 3", Text.literal(""));
            }
            else if (pressTime == 40 && enchantRod) {
                lp.sendChatMessage("/enchant luck 3", Text.literal(""));
            }
            else if (pressTime == 80 && enchantRod) {
                lp.sendChatMessage("/enchant unbreaking 3", Text.literal(""));
            }
            else if (pressTime == 120 && enchantRod) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
                enchantRod = false;
                enchant = false;
            }
            if (pressTime == 0 && enchantOther) {
                lp.sendChatMessage("/enchant unbreaking 3", Text.literal(""));
            }
            else if (pressTime == 40 && enchantOther) {
                lp.sendChatMessage("/enchant mending 1", Text.literal(""));
                enchantOther = false;
                enchant = false;
            }

            if (enchant) {
                pressTime++;
            }
        } catch (Exception e) {}
        //return ActionResult.PASS;
    }

}
