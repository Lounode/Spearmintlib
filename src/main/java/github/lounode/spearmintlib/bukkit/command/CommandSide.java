package github.lounode.spearmintlib.bukkit.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * comments:<br>
 * 命令侧: 控制台、玩家、全部
 *
 * @author Lounode
 * @date 2024/08/17
 */
public enum CommandSide {
    /**
     * 控制台
     */
    CONSOLE,
    /**
     * 玩家
     */
    PLAYER,
    /**
     * 全部
     */
    BOTH;

    /**
     * comments:<br>
     * 命令发送者否通过需求的侧验证
     *
     * @param requiredSide 需求的侧
     * @param sender 命令发送者
     * @return boolean 是否通过
     * @author Lounode
     * @date 2024/08/17
     */
    public static boolean pass(CommandSide requiredSide, CommandSender sender) {
        if (requiredSide == BOTH) {
            return true;
        }

        if (sender instanceof Player) {
            return requiredSide == PLAYER;
        } else {
            return requiredSide == CONSOLE;
        }
    }
}
