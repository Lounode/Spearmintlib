package github.lounode.spearmintlib.bukkit.command;

import github.lounode.spearmintlib.bukkit.BukkitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    private BukkitPlugin plugin;
    private List<SubCommand> subCommands = new ArrayList<>();

    public MainCommand(BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(SubCommand command) {
        if (plugin.isEnabled()) {
            return;
        }
        subCommands.removeIf(subCommand -> subCommand.getSubcommand().equals(command.getSubcommand()));
        command.register(this);
        subCommands.add(command);
    }
    public void onEnable(String mainCommand) {
        plugin.getCommand(mainCommand).setExecutor(this);
        plugin.getCommand(mainCommand).setTabCompleter(this);

        for (SubCommand subCommand : subCommands) {
            if (subCommand instanceof Listener) {
                Bukkit.getPluginManager().registerEvents((Listener) subCommand, plugin);
            }
            subCommand.onEnable();
        }

        plugin.getLogger().info("Loaded " + subCommands.size() + " commands.");
    }
    public void onDisable() {

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            args = new String[]{"help"};
        }
        for(SubCommand subcommand: subCommands) {
            if (!args[0].equals(subcommand.getSubcommand())) {
                continue;
            }
            if (!CommandSide.pass(subcommand.getSide(), sender)) {
                //
                return false;
            }
            return subcommand.onCommand(sender, command, label, args);
        }
        //sender.sendMessage(RPGI18N.NO_CMD.get(args[0]));
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand subCommand : subCommands) {
                if (!subCommand.getSubcommand().contains(args[0])) {
                    continue;
                }
                if (!subCommand.canUse(sender)) {
                    continue;
                }
                result.add(subCommand.getSubcommand());
            }
        } else {
            for (SubCommand subCommand : subCommands) {
                if (!subCommand.getSubcommand().equalsIgnoreCase(args[0])) {
                    continue;
                }
                if (!subCommand.canUse(sender)) {
                    continue;
                }
                result = subCommand.onTabComplete(sender, command, alias, args);
            }
        }

        return result;
    }

    public BukkitPlugin getPlugin() {
        return plugin;
    }
}
