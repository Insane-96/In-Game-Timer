package insane96mcp.ingametimer.commands;

import insane96mcp.ingametimer.events.WorldSaveData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandStart extends CommandBase {

	@Override
	public String getName() {
		return "igt:start";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.igt:start.usage.help";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		WorldSaveData.get(sender.getEntityWorld()).setTimeOffset(sender.getEntityWorld().getTotalWorldTime());
		sender.sendMessage(new TextComponentString("[In-Game Timer] Timer Started"));
	}

}
