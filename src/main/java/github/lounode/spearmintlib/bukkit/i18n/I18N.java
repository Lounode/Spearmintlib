package github.lounode.spearmintlib.bukkit.i18n;

import github.lounode.spearmintlib.bukkit.BukkitPlugin;
import github.lounode.spearmintlib.bukkit.util.JsonConfiguration;
import lombok.Getter;

import javax.annotation.Nullable;
import java.io.*;
import java.util.logging.Level;
import java.text.MessageFormat;

public class I18N {
    @Getter
    private BukkitPlugin plugin;

    @Getter
    private JsonConfiguration messages;
    private String languageFileDirectoryInJar = "assets/plugin/lang";

    public I18N(BukkitPlugin plugin) {
        this.plugin = plugin;
        reloadMessages("en_us");
    }

    public boolean reloadMessages(String languageCode) {
        File languageFileDirectory = new File(plugin.getDataFolder(), "lang");
        File languageFile = new File(languageFileDirectory, languageCode + ".json");

        if ((!languageFile.exists() || plugin.isDebugVersion()) && !saveDefaultMessages(languageCode)) {
            return false;
        }

        messages = JsonConfiguration.loadConfiguration(languageFile);
        return true;
    }

    private boolean saveDefaultMessages(String languageCode) {
        String resourcePath = new File(languageFileDirectoryInJar, languageCode + ".json").getPath();
        if (resourcePath.equals("assets/plugin/lang/.json")) {
            throw new IllegalArgumentException("ResourcePath cannot be or empty");
        }

        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = plugin.getResource(resourcePath);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + plugin.getName());
        }

        File outDir = new File(plugin.getDataFolder(), "lang");
        File outFile = new File(outDir, languageCode + ".json");


        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            OutputStream out = new FileOutputStream(outFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, ex);
        }
        return true;
    }

    public String get(String key, @Nullable Object... args) {
        if (messages.contains(key)) {
            return MessageFormat.format(messages.getString(key), args);
        }
        return key;
    }
}
