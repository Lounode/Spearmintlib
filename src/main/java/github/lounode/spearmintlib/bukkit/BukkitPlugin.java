package github.lounode.spearmintlib.bukkit;

import github.lounode.spearmintlib.bukkit.i18n.I18N;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import com.demonwav.mcdev.annotations.*;

import javax.annotation.Nullable;

public abstract class BukkitPlugin<T extends BukkitPlugin<T>> extends JavaPlugin {
    protected static BukkitPlugin<?> instance;
    protected BukkitPlugin() {
        instance = this;
    }
    @Getter
    private I18N i18N = new I18N(this);
    public boolean isDebugVersion() {
        String debugProperty = System.getProperty("debug");
        return "true".equals(debugProperty);
    }
    public String getI18N(@Translatable(foldMethod = true) String key, @Nullable Object... args) {
        return i18N.get(key, args);
    }
}
