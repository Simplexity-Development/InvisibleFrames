package adhdmc.invisibleframes.hooks;

import org.bukkit.inventory.ItemStack;

public interface ItemsAdder {
    boolean isCustomStack(ItemStack stack);

    class Impl implements ItemsAdder {
        @Override
        public boolean isCustomStack(ItemStack stack) {
            return dev.lone.itemsadder.api.CustomStack.byItemStack(stack) != null;
        }
    }

    class Noop implements ItemsAdder {
        @Override
        public boolean isCustomStack(ItemStack stack) {
            return false;
        }
    }
}
