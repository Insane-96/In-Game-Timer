package net.insane96mcp.ingametimer.events;

import net.insane96mcp.ingametimer.lib.Properties;
import net.insane96mcp.ingametimer.network.PacketHandler;
import net.insane96mcp.ingametimer.network.WorldTimeMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingUpdate {

	@SubscribeEvent
	public static void EventLivingUpdate(LivingUpdateEvent event) {
		if (event.getEntityLiving().ticksExisted % Properties.General.updateRate != 0)
			return;
		
		if (event.getEntityLiving() instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
			PacketHandler.SendToClient(new WorldTimeMessage(player.world.getTotalWorldTime()), player);
		}
	}
}
