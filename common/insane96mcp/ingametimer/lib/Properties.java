package insane96mcp.ingametimer.lib;

import insane96mcp.ingametimer.InGameTimer;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = InGameTimer.MOD_ID, category = "", name = "InGameTimer")
public class Properties {
	
	@Name("Config")
	public static final ConfigOptions config = new ConfigOptions();
	
	public static class ConfigOptions {

		@Name("General")
		public General general = new General();
		
		public static class General {
			@Name("Show Ticks")
			@Comment("Makes ticks appear after the time, in parenthesis")
			public boolean showTicks = false;
			@Name("Show Text")
			@Comment("Show/Hide the 'In-Game Timer:' text")
			public boolean showText = true;
			@Name("Show Time")
			@Comment("Show/Hide HH:MM:SS:DD time")
			public boolean showTime = true;
			@Name("Update rate")
			@Comment("Every how many ticks (1 second = 20 ticks) should the in-game-timer update?")
			@RangeInt(min = 1, max = 20)
			public int updateRate = 1;
		}
		
		@Name("Commands")
		public Commands commands = new Commands();
		
		public static class Commands {
			@Name("Start / Stop Timer via Command")
			@Comment("If enabled the timer will start when the command '/igt start' is executed instead at the start of the world and can be stopped with the command '/igt stop'")
			public boolean startViaCommand = false;
			@Name("Permit command to anyone")
			@Comment("If enabled any player will be able to run the start and stop commands without need to be opped / with cheats enabled")
			public boolean permitToAnyone = true;
		}
		
		@Name("Real Time")
		public RealTime realTime = new RealTime();
	
		public static class RealTime {
			@Name("Show Real Time")
			@Comment("Makes the string on top left corner show real life time. If this is true, the in-game-timer will no longer be visible. In 'format' you can even add day, month and year.")
			public boolean showRealTime = false;
			@Name("Real Time Format")
			@Comment("How to display the irl time. Possible pattern letters can be found here: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html")
			public String format = "hh:mm a";
		}
	}
	
	@Mod.EventBusSubscriber(modid = InGameTimer.MOD_ID)
	private static class EventHandler{
		@SubscribeEvent
	    public static void onConfigChangedEvent(OnConfigChangedEvent event)
	    {
	        if (event.getModID().equals(InGameTimer.MOD_ID))
	        {
	            ConfigManager.sync(InGameTimer.MOD_ID, Type.INSTANCE);
	        }
	    }
	}
}
