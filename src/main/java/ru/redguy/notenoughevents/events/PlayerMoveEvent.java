package ru.redguy.notenoughevents.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.fml.LogicalSide;

@Cancelable
public class PlayerMoveEvent extends PlayerEvent {

    private final LogicalSide logicalSide;
    private final Vector3d movementVector;

    public PlayerMoveEvent(PlayerEntity player, LogicalSide logicalSide, Vector3d movementVector) {
        super(player);
        this.logicalSide = logicalSide;
        this.movementVector = movementVector;
    }

    public Vector3d getMovementVector() {
        return movementVector;
    }

    public LogicalSide getLogicalSide() {
        return logicalSide;
    }
}
