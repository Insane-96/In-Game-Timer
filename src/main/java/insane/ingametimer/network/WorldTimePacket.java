package insane.ingametimer.network;

import insane.ingametimer.events.RenderGameOverlay;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class WorldTimePacket {

	public long time;

	public WorldTimePacket(long time) {
		this.time = time;
	}

	public static void encode(WorldTimePacket packet, PacketBuffer buf) {
		buf.writeLong(packet.time);
	}

	public static WorldTimePacket decode(PacketBuffer buf) {
		return new WorldTimePacket(buf.readLong());
	}

	public static class Handler {
		public static void handle(final WorldTimePacket packet, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				RenderGameOverlay.time = packet.time;

			});
			ctx.get().setPacketHandled(true);
		}
	}
}
