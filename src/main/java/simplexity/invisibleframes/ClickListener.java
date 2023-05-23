package simplexity.invisibleframes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ClickListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemFrameInteract(PlayerInteractEntityEvent playerInteractEntityEvent) {
        if (playerInteractEntityEvent.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        Player player = playerInteractEntityEvent.getPlayer();
        Entity entity = playerInteractEntityEvent.getRightClicked();
        if (!player.isSneaking()) return;
        if (!(entity instanceof ItemFrame itemFrame)) return;
        if (!player.hasPermission("invisibleframes.toggleframes")) return;
        FrameToggleEvent frameToggleEvent = new FrameToggleEvent(itemFrame, player);
        Bukkit.getServer().getPluginManager().callEvent(frameToggleEvent);
        if (frameToggleEvent.isCancelled()) return;
        frameToggleEvent.runAllChecks();
        if (frameToggleEvent.isCancelled()) return;
        playerInteractEntityEvent.setCancelled(true);
        frameToggleEvent.toggleFrame();
    }
}
