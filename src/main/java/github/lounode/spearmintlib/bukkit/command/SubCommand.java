package github.lounode.spearmintlib.bukkit.command;

import github.lounode.spearmintlib.bukkit.BukkitPlugin;
import github.lounode.spearmintlib.bukkit.util.Pair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {
    private MainCommand mainCommand;
    private CommandSide side;
    private String subcommand;
    public SubCommand(String subcommand) {
        this(subcommand, CommandSide.BOTH);
    }
    public SubCommand(String subcommand, CommandSide side) {
        this.subcommand = subcommand;
        this.side = side;
    }
    public void register(MainCommand mainCommand) {
        this.mainCommand = mainCommand;
    }

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }

    public String getSubcommand() {
        return subcommand;
    }
    public MainCommand getMainCommand() {
        return mainCommand;
    }

    public BukkitPlugin getPlugin() {
        return mainCommand.getPlugin();
    }

    public void onEnable() {}

    public boolean canUse(CommandSender sender) {
        if (CommandSide.pass(side, sender)) {
            return true;
        }
        return false;
    }
    public CommandSide getSide() {
        return side;
    }
    public boolean isPlayerOnly() {
        return side == CommandSide.PLAYER;
    }
    public boolean isConsoleOnly() {
        return side == CommandSide.CONSOLE;
    }
    public boolean isBothUse() {
        return side == CommandSide.BOTH;
    }


}
