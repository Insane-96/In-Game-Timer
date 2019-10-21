package insane.ingametimer.events;

import insane.ingametimer.InGameTimer;
import insane.ingametimer.network.PacketHandler;
import insane.ingametimer.network.WorldTimePacket;
import insane.ingametimer.setup.ModConfig;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InGameTimer.MOD_ID)
public class LivingUpdate {

	@SubscribeEvent
	public static void eventLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if (!(event.getEntityLiving() instanceof ServerPlayerEntity))
			return;

		if (event.getEntityLiving().ticksExisted % ModConfig.updateRate.get() != 0)
			return;

		ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
		long time = player.world.getGameTime();

		PacketHandler.sendTo(new WorldTimePacket(time), player);

	}
}
