package insane96mcp.ingametimer.network;

import insane96mcp.ingametimer.events.RenderGameOverlay;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class WorldTimeMessage implements IMessage {

	public WorldTimeMessage() { }
	
	public long time;
	public long timeOffset;
	
	public WorldTimeMessage(long time, long timeOffset) {
		this.time = time;
		this.timeOffset = timeOffset;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.time = buf.readLong();
		this.timeOffset = buf.readLong();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.time);
		buf.writeLong(this.timeOffset);
	}

	public static class Handler implements IMessageHandler<WorldTimeMessage, IMessage>{
		@Override
		public IMessage onMessage(WorldTimeMessage message, MessageContext ctx) {
			RenderGameOverlay.time = message.time;
			RenderGameOverlay.timeOffset = message.timeOffset;
			return null;
		}

	}
}
