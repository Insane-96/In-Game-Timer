package insane96mcp.ingametimer.proxies;

import insane96mcp.ingametimer.network.PacketHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void PreInit(FMLPreInitializationEvent event) {
		PacketHandler.Init();
	}
	
	public void Init(FMLInitializationEvent event) {
		
	}
	
	public void PostInit(FMLPostInitializationEvent event) {
		
	}
}
