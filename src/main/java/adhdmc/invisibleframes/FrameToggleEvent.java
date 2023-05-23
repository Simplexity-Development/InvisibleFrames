package adhdmc.invisibleframes;

import adhdmc.invisibleframes.util.ConfigHandler;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class FrameToggleEvent extends Event implements Cancellable {
    private static final HandlerList handlerList = new HandlerList();
    private boolean cancelled;
    private final ItemFrame itemFrame;
    private final Player player;

    public FrameToggleEvent(ItemFrame itemFrame, Player player){
        this.itemFrame = itemFrame;
        this.player = player;
    }

    public boolean isFrameFixed() {
        return itemFrame.isFixed();
    }

    public boolean isFrameVisible() {
        return itemFrame.isVisible();
    }

    public boolean isItemsAdderCustomStack() {
        return  InvisibleFrames.getInstance().getItemsAdder().isCustomStack(getContainedItem());
    }

    public List<ItemStack> getBlacklistedItems() {
        return Collections.unmodifiableList(ConfigHandler.getInstance().getBlacklistedItems());
    }

    public ItemStack getContainedItem() {
        InvisibleFrames.getInstance().getInvisibleFramesLogger().info(itemFrame.getItem().toString());
        return itemFrame.getItem();
    }

    public void toggleFrame() {
        itemFrame.setVisible(!isFrameVisible());
        if (ConfigHandler.getInstance().shouldFixFrames()) {
            itemFrame.setFixed(!isFrameFixed());
        } else {
            itemFrame.setFixed(false);
        }
    }

    public void checkBlacklist() {
        if (getBlacklistedItems().contains(getContainedItem())) {
            cancelled = true;
        }
    }

    public void checkItemsAdder() {
        if (isItemsAdderCustomStack()) {
            cancelled = true;
        }
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public ItemFrame getItemFrame() {
        return itemFrame;
    }

    public Player getPlayer() {
        return player;
    }
}
