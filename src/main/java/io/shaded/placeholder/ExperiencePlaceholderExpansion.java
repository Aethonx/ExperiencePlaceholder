package io.shaded.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.Locale;

// Formatter pulled from here for consistency
// https://github.com/PlaceholderAPI/Vault-Expansion/blob/ed65d8ee9befdf021083dfeaaa46bbde6299dcab/src/main/java/com/extendedclip/papi/expansion/vault/VaultEcoHook.java#L235
public final class ExperiencePlaceholderExpansion extends PlaceholderExpansion {

	private static final NumberFormat EXPERIENCE_FORMATTER = NumberFormat.getInstance(Locale.ENGLISH);

	static {
		EXPERIENCE_FORMATTER.setMaximumFractionDigits(2);
		EXPERIENCE_FORMATTER.setMinimumFractionDigits(0);
	}

	private static final String THOUSANDS_SUFFIX = "k";
	private static final String MILLIONS_SUFFIX = "M";
	private static final String BILLIONS_SUFFIX = "B";
	private static final String TRILLIONS_SUFFIX = "T";
	private static final String QUADRILLIONS = "Q";

	public ExperiencePlaceholderExpansion() {
	}

	@Override
	public String onRequest(final OfflinePlayer offlinePlayer, final String params) {
		Player player = offlinePlayer.getPlayer();

		if (player == null || !player.isOnline()) {
			return "Please contact an admin.";
		}

		// What a stupid system.... %experience_formatted%
		if (params.equals("formatted")) {
			return formatExperience(this.getTotalExperience(player));
		}
		return null;
	}

	private String formatExperience(int playerExperience) {
		if (playerExperience < 1000) {
			return EXPERIENCE_FORMATTER.format(playerExperience);
		} else if (playerExperience < 1000000L) {
			return EXPERIENCE_FORMATTER.format(playerExperience / 1000) + THOUSANDS_SUFFIX;
		} else if (playerExperience < 1000000000L) {
			return EXPERIENCE_FORMATTER.format(playerExperience / 1000000L) + MILLIONS_SUFFIX;
		} else if (playerExperience < 1000000000000L) {
			return EXPERIENCE_FORMATTER.format(playerExperience / 1000000000L) + BILLIONS_SUFFIX;
		} else if (playerExperience < 1000000000000000L) {
			return EXPERIENCE_FORMATTER.format(playerExperience / 1000000000000L) + TRILLIONS_SUFFIX;
		} else if (playerExperience < 1000000000000000000L) {
			return EXPERIENCE_FORMATTER.format(playerExperience / 1000000000000000L) + QUADRILLIONS;
		}
		return String.valueOf(playerExperience);
	}

	// https://gist.github.com/RichardB122/8958201b54d90afbc6f0
	private int getTotalExperience(Player player) {
		int experience = 0;
		int level = player.getLevel();
		if (level >= 0 && level <= 15) {
			experience = (int) Math.ceil(Math.pow(level, 2) + (6 * level));
			int requiredExperience = 2 * level + 7;
			double currentExp = Double.parseDouble(Float.toString(player.getExp()));
			experience += Math.ceil(currentExp * requiredExperience);
			return experience;
		} else if (level > 15 && level <= 30) {
			experience = (int) Math.ceil((2.5 * Math.pow(level, 2) - (40.5 * level) + 360));
			int requiredExperience = 5 * level - 38;
			double currentExp = Double.parseDouble(Float.toString(player.getExp()));
			experience += Math.ceil(currentExp * requiredExperience);
			return experience;
		} else {
			experience = (int) Math.ceil(((4.5 * Math.pow(level, 2) - (162.5 * level) + 2220)));
			int requiredExperience = 9 * level - 158;
			double currentExp = Double.parseDouble(Float.toString(player.getExp()));
			experience += Math.ceil(currentExp * requiredExperience);
			return experience;
		}
	}

	@Override
	public String getIdentifier() {
		return "experience";
	}

	@Override
	public String getAuthor() {
		return "Aethonx";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}
}
