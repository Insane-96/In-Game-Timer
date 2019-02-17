package insane96mcp.ingametimer.commands;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import insane96mcp.ingametimer.events.WorldSaveData;
import insane96mcp.ingametimer.lib.Properties;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandStart extends CommandBase {

	@Override
	public String getName() {
		return "igt";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "command.igt.usage";
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		if (!Properties.config.commands.startViaCommand)
			return false;
		if (Properties.config.commands.permitToAnyone)
			return true;
		else 
			return super.checkPermission(server, sender);
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			throw new WrongUsageException(getUsage(sender), new Object[0]);
		}
		
		if (args[0].equals("start")) {
			WorldSaveData savedData = WorldSaveData.get(sender.getEntityWorld());
			long timeStart = savedData.getTimeStart();
			long timeStop = savedData.getTimeStop();
			if (timeStart == 0) {
				savedData.setTimeStart(sender.getEntityWorld().getTotalWorldTime());
				server.getPlayerList().sendMessage(new TextComponentTranslation("igt.timer_started"));
			}
			else {
				if (timeStop == 0) {
					sender.sendMessage(new TextComponentTranslation("command.igt.already_started"));
				}
				else {
					savedData.setTimeStop(0);
					server.getPlayerList().sendMessage(new TextComponentTranslation("igt.timer_restarted"));
				}
			}
		}
		else if (args[0].equals("stop")) {
			WorldSaveData savedData = WorldSaveData.get(sender.getEntityWorld());
			long timeStart = savedData.getTimeStart();
			long timeStop = savedData.getTimeStop();
			if (timeStart == 0) {
				sender.sendMessage(new TextComponentTranslation("command.igt.not_yet_started"));
			}
			else {
				if (timeStop == 0) {
					savedData.setTimeStop(sender.getEntityWorld().getTotalWorldTime());
					server.getPlayerList().sendMessage(new TextComponentTranslation("igt.timer_stopped"));
				}
				else {
					sender.sendMessage(new TextComponentTranslation("command.igt.already_stopped"));
				}
			}
		}
		else {
			throw new WrongUsageException(getUsage(sender), new Object[0]);
		}
	}

	@Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1)
        {
        	return getListOfStringsMatchingLastWord(args, new String[] {"start", "stop"});
        }
		return Collections.emptyList();
    }

}
