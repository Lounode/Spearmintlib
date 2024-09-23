package github.lounode.spearmintlib.bukkit;

import github.lounode.spearmintlib.bukkit.command.MainCommand;
import github.lounode.spearmintlib.bukkit.command.TestCommand;
import github.lounode.spearmintlib.bukkit.command.VersionCommand;

/**
 * comments:<br>
 * 薄荷库(Bukkit插件版)
 *
 * @author Lounode
 * @date 2024/08/14
 */
public final class Spearmintlib extends BukkitPlugin<Spearmintlib> {
    private MainCommand SPEARMINTLIB_COMMAND;

    @Override
    public void onLoad() {
        SPEARMINTLIB_COMMAND = new MainCommand(this);
        SPEARMINTLIB_COMMAND.register(new VersionCommand());
        SPEARMINTLIB_COMMAND.register(new TestCommand());
        getI18N("spearmintlib.test2");
        getI18N("awa");
    }

    @Override
    public void onEnable() {
        SPEARMINTLIB_COMMAND.onEnable("spearmintlib");
    }

    @Override
    public void onDisable() {
        SPEARMINTLIB_COMMAND.onDisable();
    }
}
