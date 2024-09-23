package github.lounode.spearmintlib.bukkit.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class TestCommand extends SubCommand{
    public TestCommand() {
        super("test");

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(new ItemStack(Material.STONE).getDisplayName());

        return true;
    }
}
