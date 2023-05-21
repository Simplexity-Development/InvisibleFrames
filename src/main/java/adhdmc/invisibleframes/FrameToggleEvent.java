package adhdmc.invisibleframes;

import org.bukkit.entity.ItemFrame;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class FrameToggleEvent extends Event implements Cancellable {
    private ItemFrame
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return null;
    }
}
