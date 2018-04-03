package net.insane96mcp.ingametimer.network;

import net.insane96mcp.ingametimer.events.RenderGameOverlay;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class WorldTimeMessageHandler implements IMessageHandler<WorldTimeMessage, IMessage> {

	@Override
	public IMessage onMessage(WorldTimeMessage message, MessageContext ctx) {
		long time = message.time;
		RenderGameOverlay.realTime = time;
		return null;
	}

}
