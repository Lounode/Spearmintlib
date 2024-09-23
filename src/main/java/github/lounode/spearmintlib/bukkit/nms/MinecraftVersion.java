package github.lounode.spearmintlib.bukkit.nms;

import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MinecraftVersion {
    V1_8,
    V1_9,
    V1_10,
    V1_11,
    V1_12,
    V1_13,
    V1_14,
    V1_15,
    V1_16,
    V1_17,
    V1_18,
    V1_19,
    V1_20,
    V1_21;

    private static MinecraftVersion VERSION;
    private static final Pattern VERSION_PATTERN = Pattern.compile("v(\\d+)_(\\d+)_R(\\d+)");

    public static MinecraftVersion getMinecraftVersion() {
        if (VERSION != null) {
            return VERSION;
        }
        String version = Bukkit.getServer() != null ? Bukkit.getServer().getClass().getPackage().getName().split("^.+\\.")[1] : "v1_12_R1";
        Matcher matcher = VERSION_PATTERN.matcher(version);
        matcher.matches();
        VERSION = MinecraftVersion.valueOf("V1_" + matcher.group(2));
        return VERSION;
    }
    public static boolean isLower(MinecraftVersion version) {
        return getMinecraftVersion() < version;
    }
    public static boolean isLowerOrEqual(MinecraftVersion version) {
        return getMinecraftVersion() <= version;
    }
    public static boolean isHigher(MinecraftVersion version) {
        return getMinecraftVersion() > version;
    }
    public static boolean isHigherOrEqual(MinecraftVersion version) {
        return getMinecraftVersion() >= version;
    }
}
