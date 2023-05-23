package simplexity.invisibleframes;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import simplexity.invisibleframes.util.ConfigHandler;

import java.util.Collections;
import java.util.Set;

/**
 * Run when an item frame is toggled from visible to invisible or vice-versa
 */

public class FrameToggleEvent extends Event implements Cancellable {
    private static final HandlerList handlerList = new HandlerList();
    private boolean cancelled;
    private final ItemFrame itemFrame;
    private final Player player;

    public FrameToggleEvent(ItemFrame itemFrame, Player player) {
        this.itemFrame = itemFrame;
        this.player = player;
    }

    /**
     * Returns whether the item frame is currently fixed
     *
     * @return boolean
     */
    public boolean isFrameFixed() {
        return itemFrame.isFixed();
    }

    /**
     * Returns whether the item frame is currently visible
     *
     * @return boolean
     */
    public boolean isFrameVisible() {
        return itemFrame.isVisible();
    }

    /**
     * Returns whether the item frame is holding a custom itemsAdder item
     *
     * @return boolean
     */
    public boolean isItemsAdderCustomStack() {
        return InvisibleFrames.getInstance().getItemsAdder().isCustomStack(getContainedItem());
    }

    /**
     * Returns the blacklisted items from the configuration
     *
     * @return Set<ItemStack>
     */

    public Set<ItemStack> getBlacklistedItems() {
        return Collections.unmodifiableSet(ConfigHandler.getInstance().getBlacklistedItems());
    }

    /**
     * Returns the item contained in the item frame
     *
     * @return ItemStack
     */
    public ItemStack getContainedItem() {
        return itemFrame.getItem();
    }

    /**
     * Checks if the item frame is empty, cancels the event if it is
     */
    public void checkToggleEmpty() {
        if (ConfigHandler.getInstance().shouldToggleEmpty()) return;
        if (getContainedItem().getType().isAir()) {
            cancelled = true;
        }
    }

    /**
     * Sets item frame visible/invisible
     * <br>Sets item frame fixed/not fixed if the configuration allows for fixing frames
     */
    public void toggleFrame() {
        itemFrame.setVisible(!isFrameVisible());
        if (ConfigHandler.getInstance().shouldFixFrames()) {
            itemFrame.setFixed(!isFrameFixed());
        } else {
            itemFrame.setFixed(false);
        }
    }

    /**
     * Runs all checks that determine if the event should be cancelled: checkItemsAdder(), checkBlacklist(), and checkToggleEmpty()
     */
    public void runAllChecks() {
        checkItemsAdder();
        checkBlacklist();
        checkToggleEmpty();
    }

    /**
     * Checks the contained item against the configured item blacklist. Cancels the event if the item is blacklisted
     */
    public void checkBlacklist() {
        if (getBlacklistedItems().contains(getContainedItem().asOne())) {
            cancelled = true;
        }
    }

    /**
     * Checks if the item is a custom item from itemsadder. Cancels the event if it is.
     */
    public void checkItemsAdder() {
        if (isItemsAdderCustomStack()) {
            cancelled = true;
        }
    }

    /**
     * Checks if this event has been cancelled
     *
     * @return boolean
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets whether this event should be cancelled
     *
     * @param cancel boolean
     */
    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /**
     * Gets the HandlerList for this event
     *
     * @return HandlerList
     */
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Gets the HandlerList for this event
     *
     * @return HandlerList
     */
    public static HandlerList getHandlerList() {
        return handlerList;
    }

    /**
     * Gets the item frame from this event
     *
     * @return ItemFrame
     */
    public ItemFrame getItemFrame() {
        return itemFrame;
    }

    /**
     * Gets the player from this event
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }
}
