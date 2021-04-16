package io.shaded.placeholder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

// %experience_formatted%
public final class ExperiencePlaceholderPlugin extends JavaPlugin {

	private static final Logger LOGGER = Logger.getLogger(ExperiencePlaceholderPlugin.class.getName());

	@Override
	public void onEnable() {

		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
			LOGGER.log(Level.SEVERE, "Could not find plugin ``PlaceholderAPI`` please verify that it's installed onto the server.");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

		LOGGER.log(Level.INFO, "Found ``PlaceholderAPI`` loading placeholder.");
		new ExperiencePlaceholderExpansion().register();
	}

}
