package net.insane96mcp.ingametimer;

import org.apache.logging.log4j.Logger;

import net.insane96mcp.ingametimer.proxies.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = InGameTimer.MOD_ID, name = InGameTimer.MOD_NAME, version = InGameTimer.VERSION, acceptableRemoteVersions = "*", acceptedMinecraftVersions = InGameTimer.MINECRAFT_VERSIONS)
public class InGameTimer {
	
	public static final String MOD_ID = "ingametimer";
	public static final String MOD_NAME = "In Game Timer";
	public static final String VERSION = "0.1.0";
	public static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ":";
	public static final String MINECRAFT_VERSIONS = "[1.12,1.12.2]";
	
	public static Logger logger;
	
	@Instance(MOD_ID)
	public static InGameTimer instance;
	
	@SidedProxy(clientSide = "net.insane96mcp.ingametimer.proxies.ClientProxy", serverSide = "net.insane96mcp.ingametimer.proxies.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		proxy.PreInit(event);
		logger = event.getModLog();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		proxy.Init(event);
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		proxy.PostInit(event);
	}
}