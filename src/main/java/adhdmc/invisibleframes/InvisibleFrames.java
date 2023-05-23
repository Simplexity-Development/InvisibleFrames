package adhdmc.invisibleframes;

import adhdmc.invisibleframes.hooks.ItemsAdder;
import adhdmc.invisibleframes.hooks.LoggingListener;
import adhdmc.invisibleframes.util.ConfigHandler;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class InvisibleFrames extends JavaPlugin {
    private static InvisibleFrames instance;
    private ItemsAdder itemsAdder;
    private Logger logger;
    private CoreProtectAPI coreProtectAPI;

    @Override
    public void onEnable() {
        logger = this.getLogger();
        this.getServer().getPluginManager().registerEvents(new ClickListener(), this);
        this.getCommand("ifreload").setExecutor(new ReloadCommand());
        instance = this;
        try {
            Class.forName("dev.lone.itemsadder.api.CustomBlock");
            this.itemsAdder = new ItemsAdder.Impl();
        } catch (ClassNotFoundException e) {
            logger.info("ItemsAdder API has not been found, ItemsAdder checks are disabled.");
            this.itemsAdder = new ItemsAdder.Noop();
        }
        checkForCoreProtect();
        if (coreProtectAPI != null) {
            this.getServer().getPluginManager().registerEvents(new LoggingListener(), this);
        }
        this.saveDefaultConfig();
        ConfigHandler.getInstance().reloadConfig();
    }

    public void checkForCoreProtect() {
        Plugin plugin = getInstance().getServer().getPluginManager().getPlugin("CoreProtect");
        if (!(plugin instanceof CoreProtect coreProtect)) {
            coreProtectAPI = null;
            return;
        }
        if (!coreProtect.getAPI().isEnabled()) {
            coreProtectAPI = null;
            return;
        }
        if (coreProtect.getAPI().APIVersion() < 9) {
            coreProtectAPI = null;
            return;
        }
        coreProtectAPI = coreProtect.getAPI();
    }

    public CoreProtectAPI getCoreProtectAPI() {
        return coreProtectAPI;
    }

    public static InvisibleFrames getInstance() {
        return instance;
    }
    public Logger getInvisibleFramesLogger(){
        return logger;
    }

    public ItemsAdder getItemsAdder() {
        return this.itemsAdder;
    }
}
