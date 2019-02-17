package insane96mcp.ingametimer.events;

import insane96mcp.ingametimer.InGameTimer;
import insane96mcp.ingametimer.lib.Properties;
import insane96mcp.ingametimer.network.PacketHandler;
import insane96mcp.ingametimer.network.WorldTimeMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = InGameTimer.MOD_ID)
public class LivingUpdate {

	@SubscribeEvent
	public static void EventLivingUpdate(LivingUpdateEvent event) {
		if (event.getEntityLiving().ticksExisted % Properties.config.general.updateRate != 0)
			return;
		
		if (event.getEntityLiving() instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
			long worldTime = player.world.getTotalWorldTime();
			long offset = 0;
			if (Properties.config.general.startViaCommand) {
				long timeOffset = WorldSaveData.get(player.world).getTimeOffset();
				offset = timeOffset == 0 ? worldTime : timeOffset;
			}
			PacketHandler.SendToClient(new WorldTimeMessage(worldTime, offset), player);
		}
	}
}
