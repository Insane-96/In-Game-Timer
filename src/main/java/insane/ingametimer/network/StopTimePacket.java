package insane.ingametimer.network;

import insane.ingametimer.events.RenderGameOverlay;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class StopTimePacket {

	public StopTimePacket() {

	}

	public static void encode(StopTimePacket packet, PacketBuffer buf) {

	}

	public static StopTimePacket decode(PacketBuffer buf) {
		return new StopTimePacket();
	}

	public static class Handler {
		public static void handle(final StopTimePacket packet, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				RenderGameOverlay.stopTime();

			});
			ctx.get().setPacketHandled(true);
		}
	}
}
