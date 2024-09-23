package github.lounode.spearmintlib.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class VersionCommand extends SubCommand {

    public VersionCommand() {
        super("version");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(getPlugin().getName() + "1.0.0");
        return false;
    }
}
