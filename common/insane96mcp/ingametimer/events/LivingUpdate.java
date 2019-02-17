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
			long time;
			if (Properties.config.commands.startViaCommand) {
				long timeStart = WorldSaveData.get(player.world).getTimeStart();
				long timeStop = WorldSaveData.get(player.world).getTimeStop();
				if (timeStart == 0) {
					time = 0;
				}
				else {
					if (timeStop == 0)
						time = player.world.getTotalWorldTime() - timeStart;
					else 
						time = timeStop - timeStart;
				}
			}
			else {
				time = player.world.getTotalWorldTime();
			}
			PacketHandler.SendToClient(new WorldTimeMessage(time), player);
		}
	}
}
