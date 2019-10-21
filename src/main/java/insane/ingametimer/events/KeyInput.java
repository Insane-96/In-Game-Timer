package insane.ingametimer.events;

import insane.ingametimer.InGameTimer;
import insane.ingametimer.network.KeyPressStopTimePacket;
import insane.ingametimer.network.PacketHandler;
import insane.ingametimer.network.StopTimePacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = InGameTimer.MOD_ID)
public class KeyInput {

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void eventKeyInput(InputEvent.KeyInputEvent event) {
		if (event.getKey() == GLFW.GLFW_KEY_O && !RenderGameOverlay.isTimeStopped())
			PacketHandler.sendToServer(new KeyPressStopTimePacket());
	}
}
