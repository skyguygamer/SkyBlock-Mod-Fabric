package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.skyguygamer.sbmod.SbMod;

public class AutoSpawnMob {
    public static String command = "";
    public static boolean spawningMobs = false;
    public static int amountSpawning = 0;
    public static String PlayerEntitySpawnedOn = "N/A";
    public static String mobSpawned = "";

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("autospawnmob").then(ClientCommandManager.argument("mob", StringArgumentType.string())
                .then(ClientCommandManager.argument("amount", IntegerArgumentType.integer(1, 2)).then(ClientCommandManager.argument("PlayerEntity", StringArgumentType.string()).executes(AutoSpawnMob -> all(AutoSpawnMob.getSource(),
                        StringArgumentType.getString(AutoSpawnMob, "mob"),
                        IntegerArgumentType.getInteger(AutoSpawnMob, "amount"),
                        StringArgumentType.getString(AutoSpawnMob, "PlayerEntity"))))
                        .executes(AutoSpawnMob -> noPlayerEntity(AutoSpawnMob.getSource(),
                                StringArgumentType.getString(AutoSpawnMob, "mob"),
                                IntegerArgumentType.getInteger(AutoSpawnMob, "amount")))))
                        .then(ClientCommandManager.literal("info").executes(AutoSpawnMob -> info(AutoSpawnMob.getSource())))
                .executes(AutoSpawnMob -> stop(AutoSpawnMob.getSource())));
        dispatcher.register(ClientCommandManager.literal("asm").then(ClientCommandManager.argument("mob", StringArgumentType.string())
                        .then(ClientCommandManager.argument("amount", IntegerArgumentType.integer(1, 2)).then(ClientCommandManager.argument("PlayerEntity", StringArgumentType.string()).executes(AutoSpawnMob -> all(AutoSpawnMob.getSource(),
                                        StringArgumentType.getString(AutoSpawnMob, "mob"),
                                        IntegerArgumentType.getInteger(AutoSpawnMob, "amount"),
                                        StringArgumentType.getString(AutoSpawnMob, "PlayerEntity"))))
                                .executes(AutoSpawnMob -> noPlayerEntity(AutoSpawnMob.getSource(),
                                        StringArgumentType.getString(AutoSpawnMob, "mob"),
                                        IntegerArgumentType.getInteger(AutoSpawnMob, "amount")))))
                .then(ClientCommandManager.literal("info").executes(AutoSpawnMob -> info(AutoSpawnMob.getSource())))
                .executes(AutoSpawnMob -> stop(AutoSpawnMob.getSource())));
    }
    //To spawn mobs with 3rd argument
    public static int all(FabricClientCommandSource source, String mob, int amount, String PlayerEntity) {
        command = ("spawnmob " + mob + " " + amount + " " + PlayerEntity);
        SbMod.spawnMobs = true;
        SbMod.spawnTime = 3000;
        amountSpawning = amount;
        PlayerEntitySpawnedOn = PlayerEntity;
        mobSpawned = mob;
        source.sendFeedback(Text.literal(Formatting.GREEN + "Now spawning mobs!"));
        return Command.SINGLE_SUCCESS;
    }
    //To spawn mobs without 3rd argument
    public static int noPlayerEntity(FabricClientCommandSource source, String mob, int amount) {
        command = ("spawnmob " + mob + " " + amount);
        SbMod.spawnMobs = true;
        SbMod.spawnTime = 3000;
        amountSpawning = amount;
        mobSpawned = mob;
        source.sendFeedback(Text.literal(Formatting.GREEN + "Now spawning mobs!"));
        return Command.SINGLE_SUCCESS;
    }
    public static int info(FabricClientCommandSource source) {
        if(SbMod.spawnMobs) {
            source.sendFeedback(Text.literal("§aYou currently are spawning §f" + amountSpawning + " " + mobSpawned + " §aon §f" + PlayerEntitySpawnedOn) );
        } else {
            source.sendFeedback(Text.literal(Formatting.RED + "You are not spawning mobs right now!"));
        }
        return Command.SINGLE_SUCCESS;
    }
    //Stops spawning mobs
    public static int stop(FabricClientCommandSource source) {
        if(SbMod.spawnMobs) {
            SbMod.spawnMobs = false;
            spawningMobs = false;
            source.sendFeedback(Text.literal(Formatting.GREEN + "You have stopped spawning mobs"));
        } else {
            source.sendFeedback(Text.literal(Formatting.RED + "You currently are not spawning any mobs"));
        }
        return Command.SINGLE_SUCCESS;
    }

}
