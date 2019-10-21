package insane.ingametimer;

import insane.ingametimer.network.PacketHandler;
import insane.ingametimer.setup.ModConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.stream.Collectors;

@Mod("ingametimer")
public class InGameTimer
{
	public static final String MOD_ID = "ingametimer";
	public static final Logger LOGGER = LogManager.getLogger();

	public InGameTimer() {

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.SPEC);
		ModConfig.init(Paths.get("config", MOD_ID + ".toml"));

		PacketHandler.register();
	}
}
