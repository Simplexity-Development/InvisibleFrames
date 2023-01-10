package adhdmc.invisibleframes;

import org.bukkit.plugin.java.JavaPlugin;

public final class InvisibleFrames extends JavaPlugin {

    private static InvisibleFrames instance;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new ClickListener(), this);
        this.getCommand("ifreload").setExecutor(new ReloadCommand());
        this.saveDefaultConfig();
        getConfig().addDefault("lock-frame", true);
        getConfig().addDefault("toggle-empty", false);
        instance = this;
    }

    public static InvisibleFrames getInstance() {
        return instance;
    }
}
