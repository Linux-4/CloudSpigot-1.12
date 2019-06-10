package eu.minewars.cloudspigot.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Throwables;

public class CloudSpigotConfig {

	private static File CONFIG_FILE;
	private static final String HEADER = "This is the main configuration file for CloudSpigot.\n"
			+ "As you can see, there's tons to configure. Some options may impact gameplay, so use\n"
			+ "with caution, and make sure you know what each option does before configuring.\n" + "\n"
			+ "If you need help with the configuration or have any questions related to CloudSpigot,\n"
			+ "join us on our Discord server.\n" + "\n" + "Discord: https://discordapp.com/invite/5qp26hf\n";
	/* ======================================================================== */
	public static YamlConfiguration config;

	@SuppressWarnings("deprecation")
	public static void init(File configFile) {
		CONFIG_FILE = configFile;
		config = new YamlConfiguration();
		try {
			config.load(CONFIG_FILE);
		} catch (IOException ex) {
		} catch (InvalidConfigurationException ex) {
			Bukkit.getLogger().log(Level.SEVERE, "Could not load cloudspigot.yml, please correct your syntax errors",
					ex);
			throw Throwables.propagate(ex);
		}
		config.options().header(HEADER);
		config.options().copyDefaults(true);

		animateExplosions();
		
		try {
			config.save(CONFIG_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean animateExplosions;

	private static void animateExplosions() {
		config.addDefault("settings.animate-explosions", false);
		animateExplosions = config.getBoolean("settings.animate-explosions", false);
	}

}
