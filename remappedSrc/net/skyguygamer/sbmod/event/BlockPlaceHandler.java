package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import static net.skyguygamer.sbmod.SbMod.printMsg;

public class BlockPlaceHandler implements UseBlockCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        Item item = player.getMainHandStack().getItem();
        if(!printMsg && item == Items.ACACIA_SIGN || item == Items.SPRUCE_SIGN || item == Items.BIRCH_SIGN || item == Items.CRIMSON_SIGN || item == Items.JUNGLE_SIGN || item == Items.DARK_OAK_SIGN || item == Items.WARPED_SIGN || item == Items.OAK_SIGN || item == Items.MANGROVE_SIGN) {
            printMsg = true;
        }
        return ActionResult.PASS;
    }
}
