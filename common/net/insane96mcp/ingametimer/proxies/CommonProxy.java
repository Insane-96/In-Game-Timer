package net.insane96mcp.ingametimer.proxies;

import net.insane96mcp.ingametimer.events.LivingUpdate;
import net.insane96mcp.ingametimer.events.RenderGameOverlay;
import net.insane96mcp.ingametimer.lib.Config;
import net.insane96mcp.ingametimer.lib.Properties;
import net.insane96mcp.ingametimer.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void PreInit(FMLPreInitializationEvent event) {
		Config.config = new Configuration(event.getSuggestedConfigurationFile());
		Config.SyncConfig();
		Properties.Init();
		PacketHandler.Init();
	}
	
	public void Init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(RenderGameOverlay.class);
		MinecraftForge.EVENT_BUS.register(LivingUpdate.class);
	}
	
	public void PostInit(FMLPostInitializationEvent event) {
		Config.SaveConfig();
	}
}
