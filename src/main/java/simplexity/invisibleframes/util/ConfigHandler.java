package simplexity.invisibleframes.util;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import simplexity.invisibleframes.InvisibleFrames;

import java.util.HashSet;
import java.util.List;

public class ConfigHandler {
    private static ConfigHandler instance;

    private ConfigHandler() {
    }

    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }

    private final HashSet<ItemStack> blacklistedItems = new HashSet<>();
    private boolean fixFrames = true;
    private boolean toggleEmpty = false;

    public void reloadConfig() {
        blacklistedItems.clear();
        FileConfiguration config = InvisibleFrames.getInstance().getConfig();
        fixFrames = config.getBoolean("lock-frame");
        toggleEmpty = config.getBoolean("toggle-empty");
        List<String> configBlacklist = config.getStringList("blacklisted-items");
        for (String item : configBlacklist) {
            try {
                ItemStack newItem = Bukkit.getItemFactory().createItemStack(item).asOne();
                blacklistedItems.add(newItem);
            } catch (IllegalArgumentException e) {
                InvisibleFrames.getInstance().getInvisibleFramesLogger().warning(item + " could not be cast to an ItemStack. Please make sure your syntax is correct");
            }
        }
    }

    public HashSet<ItemStack> getBlacklistedItems() {
        return blacklistedItems;
    }

    public boolean shouldFixFrames() {
        return fixFrames;
    }

    public boolean shouldToggleEmpty() {
        return toggleEmpty;
    }
}
