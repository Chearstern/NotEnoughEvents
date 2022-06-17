package ru.redguy.notenoughevents.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.redguy.notenoughevents.events.RawEntityRepositionEvent;

@Mixin(value = Entity.class)
public class EntityMixin {
    @Shadow
    private Vector3d positionVec;

    @Inject(method = "setRawPosition", at = @At(value = "NEW", target = "Lnet/minecraft/util/math/vector/Vector3d;<init>(DDD)V", shift = At.Shift.BEFORE))
    public void setRawPositionHook(double x, double y, double z, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new RawEntityRepositionEvent((Entity) ((Object) this), positionVec, new Vector3d(x, y, z)));
    }
}
