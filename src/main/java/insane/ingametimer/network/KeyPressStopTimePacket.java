package insane.ingametimer.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class KeyPressStopTimePacket {

	public KeyPressStopTimePacket() {

	}

	public static void encode(KeyPressStopTimePacket packet, PacketBuffer buf) {

	}

	public static KeyPressStopTimePacket decode(PacketBuffer buf) {
		return new KeyPressStopTimePacket();
	}

	public static class Handler {
		public static void handle(final KeyPressStopTimePacket packet, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				for (ServerPlayerEntity player : ctx.get().getSender().getServerWorld().getPlayers()){
					PacketHandler.sendTo(new StopTimePacket(), player);
				}

			});
			ctx.get().setPacketHandled(true);
		}
	}
}
