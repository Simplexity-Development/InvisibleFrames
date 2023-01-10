package adhdmc.invisibleframes;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class  ClickListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemFrameInteract(PlayerInteractEntityEvent event) {
        FileConfiguration config = InvisibleFrames.getInstance().getConfig();
        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) return;

        Player player = event.getPlayer();
        if (!player.isSneaking()) return;

        Entity entity = event.getRightClicked();
        if (!(entity instanceof ItemFrame itemFrame)) return;
        if (itemFrame.getItem().getType().equals(Material.AIR) && !config.getBoolean("toggle-empty", false)) return;
        if (!player.hasPermission("invisibleframes.toggleframes")) return;

        event.setCancelled(true);
        itemFrame.setVisible(!itemFrame.isVisible());
        if (config.getBoolean("lock-frame", true)) {
            itemFrame.setFixed(!itemFrame.isFixed());
        } else {
            itemFrame.setFixed(false);
        }
    }
}
