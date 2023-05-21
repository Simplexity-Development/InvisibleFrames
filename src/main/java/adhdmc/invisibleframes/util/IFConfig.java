package adhdmc.invisibleframes.util;

import com.denizenscript.denizen.nms.util.jnbt.Tag;
import com.denizenscript.denizen.objects.properties.item.ItemFlags;
import com.denizenscript.denizencore.objects.ObjectTag;
import com.denizenscript.denizencore.tags.TagManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class IFConfig {
    private static final ArrayList<String> blacklistedDenizenTags = new ArrayList<>();

    public void reloadDenizenFlags(FileConfiguration config){
        blacklistedDenizenTags.clear();
        List<String> tagList = config.getStringList("ignore-items-tagged");
        for (String tag : tagList) {
            blacklistedDenizenTags.add(tag);
        }
    }

    public static ArrayList<String> getBlacklistedDenizenTags() {
        return blacklistedDenizenTags;
    }
}
