package insane.ingametimer.setup;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ModConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec SPEC;

	public static void init(Path file) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(file)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();

		configData.load();
		SPEC.setConfig(configData);
	}

	public static ForgeConfigSpec.ConfigValue<Boolean> showText;
	public static ForgeConfigSpec.ConfigValue<Integer> updateRate;

	public static void init() {
		showText = BUILDER
				.comment("Show/hide the 'In-Game Time: ' string")
				.define("show_text", true);

		updateRate = BUILDER
				.comment("Each how many ticks should the timer be updated? (20 ticks = 1 second)")
				.define("update_rate", 1);
	}

	static {
		init();

		SPEC = BUILDER.build();
	}
}
