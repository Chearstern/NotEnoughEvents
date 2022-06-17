package ru.redguy.notenoughevents.listeners;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import ru.redguy.notenoughevents.events.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber
public class PlayerEvents {

    private static final HashMap<UUID, Vector3d> previousPositions = new HashMap<>();

    @SubscribeEvent
    public static void onServerTick(TickEvent.@NotNull PlayerTickEvent event) {
        if (!previousPositions.containsKey(event.player.getUniqueID()))
            previousPositions.put(event.player.getUniqueID(), event.player.getPositionVec());

        Vector3d previousPosition = previousPositions.get(event.player.getUniqueID());
        if (previousPosition.equals(event.player.getPositionVec())) return;
        Vector3d pos = event.player.getPositionVec();

        boolean canceled = MinecraftForge.EVENT_BUS.post(new PlayerMoveEvent(event.player, event.side, pos.subtract(previousPosition)));
        if (canceled)
            event.player.setRawPosition(previousPosition.x, previousPosition.y, previousPosition.z);
        else
            previousPositions.put(event.player.getUniqueID(), pos);
    }

    @SubscribeEvent
    public static void onPlayerLeave(@NotNull EntityLeaveWorldEvent event) {
        if (event.getEntity() instanceof ServerPlayerEntity) {
            previousPositions.remove(event.getEntity().getUniqueID());
        }
    }
}
