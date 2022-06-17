package ru.redguy.notenoughevents.events;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.EntityEvent;

public class RawEntityRepositionEvent extends EntityEvent {

    private final Vector3d from;
    private final Vector3d to;

    public RawEntityRepositionEvent(Entity entity, Vector3d from, Vector3d to) {
        super(entity);
        this.from = from;
        this.to = to;
    }

    public Vector3d getFrom() {
        return from;
    }

    public Vector3d getTo() {
        return to;
    }
}
