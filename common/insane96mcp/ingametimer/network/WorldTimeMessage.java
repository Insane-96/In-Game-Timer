package insane96mcp.ingametimer.network;

import insane96mcp.ingametimer.events.RenderGameOverlay;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class WorldTimeMessage implements IMessage {

	public WorldTimeMessage() { }
	
	public long time;

	public WorldTimeMessage(long time) {
		this.time = time;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.time = buf.readLong();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.time);
	}

	public static class Handler implements IMessageHandler<WorldTimeMessage, IMessage>{
		@Override
		public IMessage onMessage(WorldTimeMessage message, MessageContext ctx) {
			RenderGameOverlay.time = message.time;
			return null;
		}

	}
}
