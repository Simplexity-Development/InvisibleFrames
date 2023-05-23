package adhdmc.invisibleframes.hooks;

import adhdmc.invisibleframes.FrameToggleEvent;
import adhdmc.invisibleframes.InvisibleFrames;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class LoggingListener implements Listener {

    CoreProtectAPI coreProtectAPI = InvisibleFrames.getInstance().getCoreProtectAPI();

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)

    public void onFrameToggle(FrameToggleEvent toggleEvent) {
        if(coreProtectAPI == null) return;
        Player player = toggleEvent.getPlayer();
        ItemFrame itemFrame = toggleEvent.getItemFrame();
        coreProtectAPI.logInteraction(player.getName(), itemFrame.getLocation());
    }

}
