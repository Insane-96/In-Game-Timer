package insane.ingametimer.network;

import insane.ingametimer.events.RenderGameOverlay;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ResetTimePacket {
	public ResetTimePacket() {

	}

	public static void encode(ResetTimePacket packet, PacketBuffer buf) {

	}

	public static ResetTimePacket decode(PacketBuffer buf) {
		return new ResetTimePacket();
	}

	public static class Handler {
		public static void handle(final ResetTimePacket packet, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				RenderGameOverlay.resetTime();
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
