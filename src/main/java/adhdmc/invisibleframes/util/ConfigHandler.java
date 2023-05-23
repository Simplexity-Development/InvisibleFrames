package adhdmc.invisibleframes.util;


import adhdmc.invisibleframes.InvisibleFrames;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {
    private static ConfigHandler instance;
    private ConfigHandler(){}
    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }

    private final ArrayList<ItemStack> blacklistedItems = new ArrayList<>();
    private boolean fixFrames = true;
    public void reloadConfig() {
        blacklistedItems.clear();
        FileConfiguration config = InvisibleFrames.getInstance().getConfig();
        fixFrames = config.getBoolean("lock-frame");
        List<String> configBlacklist = config.getStringList("blacklisted-items");
        for (String item : configBlacklist) {
            try {
                ItemStack newItem = Bukkit.getItemFactory().createItemStack(item);
                blacklistedItems.add(newItem);
            } catch (IllegalArgumentException e) {
                InvisibleFrames.getInstance().getInvisibleFramesLogger().warning(item + " could not be cast to an ItemStack. Please make sure your syntax is correct");
            }
        }
    }

    public ArrayList<ItemStack> getBlacklistedItems() {
        return blacklistedItems;
    }

    public boolean shouldFixFrames() {
        return fixFrames;
    }
}
