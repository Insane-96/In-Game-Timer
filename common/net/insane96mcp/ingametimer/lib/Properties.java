package net.insane96mcp.ingametimer.lib;

public class Properties {
	
	public static void Init() {
		General.Init();
	}
	
	public static class General{
		public static boolean showTicks;
		public static boolean showText;
		public static boolean showTime;
		public static void Init() {
			showTicks = Config.LoadBoolProperty("general", "show_ticks", "Makes ticks appear after the time in parenthesis", false);
			showText = Config.LoadBoolProperty("general", "show_text", "Shows/Hide the 'In-Game Timer:' text", true);
			showTime = Config.LoadBoolProperty("general", "show_time", "Shows/Hide HH:MM:SS:DD time", true);
		}
	}
}
