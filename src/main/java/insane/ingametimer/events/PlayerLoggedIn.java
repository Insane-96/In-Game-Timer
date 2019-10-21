package insane.ingametimer.events;

import insane.ingametimer.InGameTimer;
import insane.ingametimer.network.PacketHandler;
import insane.ingametimer.network.ResetTimePacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InGameTimer.MOD_ID)
public class PlayerLoggedIn {

	@SubscribeEvent
	public static void eventPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getPlayer() instanceof ServerPlayerEntity)
			PacketHandler.sendTo(new ResetTimePacket(), (ServerPlayerEntity) event.getPlayer());
	}
}
