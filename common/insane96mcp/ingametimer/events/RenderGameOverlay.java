package insane96mcp.ingametimer.events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import insane96mcp.ingametimer.InGameTimer;
import insane96mcp.ingametimer.lib.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = InGameTimer.MOD_ID)
public class RenderGameOverlay {
	public static long time;
	public static long timeOffset;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {

		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.player;
		
		String finalString = "";
		if (Properties.config.realTime.showRealTime) {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat(Properties.config.realTime.format);
			finalString = format.format(date);
		}
		else {
			//long actualTime = time - timeOffset;
			if (Properties.config.general.showText)
				finalString += "In-Game Timer: ";
			
			if (Properties.config.general.showTime)
				finalString += FormatTime(time);
			
			if (Properties.config.general.showTicks)
				finalString += String.format("(%d)", player.world.getTotalWorldTime());
		}
		event.getLeft().add(finalString);
	}
	
	private static String FormatTime(long time) {
		int hours = (int) (time / 72000);
		int minutes = (int) (time / 1200 - (hours * 60));
		int seconds = (int) (time / 20 - (hours * 3600 + minutes * 60));
		int ticks = (int) (time - (hours * 72000 + minutes * 1200 + seconds * 20)) * 5;
		
		
		return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, ticks);
	}
}
