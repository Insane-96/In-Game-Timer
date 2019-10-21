package insane.ingametimer.events;

import insane.ingametimer.InGameTimer;
import insane.ingametimer.setup.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InGameTimer.MOD_ID)
public class RenderGameOverlay {
	public static long time;
	private static long timeStop = 0;

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
		Minecraft mc = Minecraft.getInstance();
		ClientPlayerEntity player = mc.player;

		String finalString = "";

		if (time < 200 && timeStop != 0)
			event.getLeft().add("Press 'o' to stop the timer");

		if (timeStop != 0)
			event.getLeft().add("Time stopped");

		if (ModConfig.showText.get())
			finalString += "In-Game Time: ";

		if (timeStop == 0)
			finalString += formatTime(time);
		else
			finalString += formatTime(timeStop);

		event.getLeft().add(finalString);
	}

	private static String formatTime(long time) {
		int hours = (int) (time / 72000);
		int minutes = (int) (time / 1200 - (hours * 60));
		int seconds = (int) (time / 20 - (hours * 3600 + minutes * 60));
		int ticks = (int) (time - (hours * 72000 + minutes * 1200 + seconds * 20)) * 5;


		return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, ticks);
	}

	public static void stopTime() {
		if (timeStop == 0) {
			timeStop = time;
		}
	}

	public static boolean isTimeStopped() {
		return timeStop > 0;
	}

	public static void resetTime() {
		time = 0;
		timeStop = 0;
	}
}
