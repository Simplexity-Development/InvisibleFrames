package adhdmc.invisibleframes;

import org.bukkit.plugin.java.JavaPlugin;

public final class InvisibleFrames extends JavaPlugin {

    private static InvisibleFrames instance;
    private static boolean hasItemsAdder;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new ClickListener(), this);
        this.getCommand("ifreload").setExecutor(new ReloadCommand());
        this.saveDefaultConfig();
        getConfig().addDefault("lock-frame", true);
        getConfig().addDefault("toggle-empty", false);
        instance = this;
        hasItemsAdder = false;
        try {
            Class.forName("dev.lone.itemsadder.api.CustomBlock");
            hasItemsAdder = true;
        } catch (ClassNotFoundException e) {
            this.getLogger().info("ItemsAdder API has not been found, ItemsAdder checks are disabled.");
        }
    }

    public static InvisibleFrames getInstance() {
        return instance;
    }
    public static boolean hasItemsAdder() { return hasItemsAdder; }
}
