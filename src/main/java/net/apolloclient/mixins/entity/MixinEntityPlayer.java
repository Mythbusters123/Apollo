package net.apolloclient.mixins.entity;

import net.apolloclient.events.impl.player.AttackEntityEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin Event
 * @author MatthewTGM | MatthewTGM#4058
 */
@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {

    /** posts a {@link AttackEntityEvent}. **/
    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("RETURN")) public void attackTargetEntityWithCurrentItem(Entity targetEntity, CallbackInfo ci) {
        if(targetEntity != null) {
            AttackEntityEvent event = new AttackEntityEvent(Minecraft.getMinecraft().thePlayer, targetEntity);
        }
    }
}
