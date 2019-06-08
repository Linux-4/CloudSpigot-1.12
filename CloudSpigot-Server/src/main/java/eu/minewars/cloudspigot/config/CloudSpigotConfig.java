package eu.minewars.cloudspigot.config;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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

		readConfig(CloudSpigotConfig.class, null);
		
		try {
			config.save(CONFIG_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	static void readConfig(Class<?> clazz, Object instance) {
		for (Method method : clazz.getDeclaredMethods()) {
			if (Modifier.isPrivate(method.getModifiers())) {
				if (method.getParameterTypes().length == 0 && method.getReturnType() == Void.TYPE) {
					try {
						method.setAccessible(true);
						method.invoke(instance);
					} catch (InvocationTargetException ex) {
						throw Throwables.propagate(ex.getCause());
					} catch (Exception ex) {
						Bukkit.getLogger().log(Level.SEVERE, "Error invoking " + method, ex);
					}
				}
			}
		}

		try {
			config.save(CONFIG_FILE);
		} catch (IOException ex) {
			Bukkit.getLogger().log(Level.SEVERE, "Could not save " + CONFIG_FILE, ex);
		}
	}

	public static boolean animateExplosions;

	@SuppressWarnings("unused")
	private static void animateExplosions() {
		animateExplosions = config.getBoolean("settings.animate-explosions", false);
	}

}
