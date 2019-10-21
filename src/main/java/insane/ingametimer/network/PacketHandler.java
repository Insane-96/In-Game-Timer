package insane.ingametimer.network;

import insane.ingametimer.InGameTimer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(InGameTimer.MOD_ID, "channel"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION)
			.simpleChannel();

	public static void register() {
		int index = 0;

		HANDLER.registerMessage(index++, WorldTimePacket.class, WorldTimePacket::encode, WorldTimePacket::decode, WorldTimePacket.Handler::handle);
		HANDLER.registerMessage(index++, KeyPressStopTimePacket.class, KeyPressStopTimePacket::encode, KeyPressStopTimePacket::decode, KeyPressStopTimePacket.Handler::handle);
		HANDLER.registerMessage(index++, StopTimePacket.class, StopTimePacket::encode, StopTimePacket::decode, StopTimePacket.Handler::handle);
		HANDLER.registerMessage(index++, ResetTimePacket.class, ResetTimePacket::encode, ResetTimePacket::decode, ResetTimePacket.Handler::handle);
	}

	public static void sendToServer(Object message) {
		HANDLER.sendToServer(message);
	}

	public static void sendTo(Object message, ServerPlayerEntity player) {
		HANDLER.sendTo(message, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
}
