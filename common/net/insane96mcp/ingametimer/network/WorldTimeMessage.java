package net.insane96mcp.ingametimer.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
		buf.writeLong(time);
	}

}
