package net.insane96mcp.ingametimer.lib;

public class Properties {
	
	public static void Init() {
		General.Init();
		RealTime.Init();
	}
	
	public static class General{
		public static boolean showTicks;
		public static boolean showText;
		public static boolean showTime;
		public static int updateRate;
		
		public static void Init() {
			showTicks = Config.LoadBoolProperty("general", "show_ticks", "Makes ticks appear after the time, in parenthesis", false);
			showText = Config.LoadBoolProperty("general", "show_text", "Shows/Hide the 'In-Game Timer:' text", true);
			showTime = Config.LoadBoolProperty("general", "show_time", "Shows/Hide HH:MM:SS:DD time", true);
			updateRate = Config.LoadIntProperty("general", "update_rate", "Every how much ticks (1 second = 20 ticks) should the in-game-timer update?", 1); 
		}
	}
	
	public static class RealTime{
		public static boolean showRealTime;
		public static String format;
		
		public static void Init() {
			showRealTime = Config.LoadBoolProperty("real_time", "show_real_time", "Makes the string on top left corner show real life time. If this is true, the in-game-timer will no longer be visible. In 'format' you can even add day, month and year.", false);
			format = Config.LoadStringProperty("real_time", "format", "How to display the irl time. Possible pattern letters can be found here: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html", "hh:mm a");
		}
	}
}
