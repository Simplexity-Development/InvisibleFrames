package adhdmc.invisibleframes;

import adhdmc.invisibleframes.hooks.ItemsAdder;
import org.bukkit.plugin.java.JavaPlugin;

public final class InvisibleFrames extends JavaPlugin {
    private static InvisibleFrames instance;
    private ItemsAdder itemsAdder;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new ClickListener(), this);
        this.getCommand("ifreload").setExecutor(new ReloadCommand());
        this.saveDefaultConfig();
        instance = this;
        try {
            Class.forName("dev.lone.itemsadder.api.CustomBlock");
            this.itemsAdder = new ItemsAdder.Impl();
        } catch (ClassNotFoundException e) {
            this.getLogger().info("ItemsAdder API has not been found, ItemsAdder checks are disabled.");
            this.itemsAdder = new ItemsAdder.Noop();
        }
    }

    public static InvisibleFrames getInstance() {
        return instance;
    }

    public ItemsAdder getItemsAdder() {
        return this.itemsAdder;
    }
}
