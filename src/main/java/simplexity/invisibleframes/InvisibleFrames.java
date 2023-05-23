package simplexity.invisibleframes;

import simplexity.invisibleframes.hooks.ItemsAdder;
import simplexity.invisibleframes.util.ConfigHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class InvisibleFrames extends JavaPlugin {
    private static InvisibleFrames instance;
    private ItemsAdder itemsAdder;
    private Logger logger;

    @Override
    public void onEnable() {
        logger = this.getLogger();
        this.getServer().getPluginManager().registerEvents(new ClickListener(), this);
        Objects.requireNonNull(this.getCommand("ifreload")).setExecutor(new ReloadCommand());
        instance = this;
        try {
            Class.forName("dev.lone.itemsadder.api.CustomBlock");
            this.itemsAdder = new ItemsAdder.Impl();
        } catch (ClassNotFoundException e) {
            logger.info("ItemsAdder API has not been found, ItemsAdder checks are disabled.");
            this.itemsAdder = new ItemsAdder.Noop();
        }
        this.saveDefaultConfig();
        ConfigHandler.getInstance().reloadConfig();
    }

    public static InvisibleFrames getInstance() {
        return instance;
    }

    public Logger getInvisibleFramesLogger() {
        return logger;
    }

    public ItemsAdder getItemsAdder() {
        return this.itemsAdder;
    }
}
