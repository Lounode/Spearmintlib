package Spearmintlib.extensions.org.bukkit.entity.Player;

import github.lounode.spearmintlib.bukkit.player.ExpUtil;
import lombok.experimental.UtilityClass;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.bukkit.entity.Player;

/**
 * {@link Player} 的扩展方法
 *
 * @author Lounode
 * @date 2024/08/19
 */
@UtilityClass
@Extension
public class PlayerExtension {
    /**
     * 设置玩家的总经验值<br>
     * 默认使用时，总经验值最高为原版限制 {@link Integer#MAX_VALUE}<br>
     * 当 {@code ignoreIntLimit} 被设置为 {@code true} 的时候，上限会被临时重设为 {@link Float#MAX_VALUE}<br>
     * 这个解除上限的操作是临时的，这意味着你如果下次使用时如果不和前一次一样解除上限，总经验值依旧会受原版限制
     *
     * @param player 被设置经验的玩家
     * @param exp    经验值
     * @param ignoreIntLimit 忽略整型限制 (总经验值可以超过 {@link Integer#MAX_VALUE})
     * @author Lounode
     * @date 2024/08/14
     */
    public static void setTotalExp(@This Player player, float exp, boolean ignoreIntLimit) {
        if (exp < 0) {
            throw new IllegalArgumentException("Total Experience must be positive!");
        }
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);

        float amount = exp;
        if (!ignoreIntLimit) {
            amount = Math.round(exp);
        }

        while (amount > 0) {
            final int expToLevel = player.getNeedExpToNextLevel();
            amount -= expToLevel;
            if (amount >= 0) {
                player.giveExp(expToLevel);
            } else {
                amount += expToLevel;
                player.giveExp((int)amount);
                amount = 0;
            }
        }
    }
    /**
     * comments:<br>
     * 获取玩家的总经验值
     *
     * @param player 玩家
     * @return float - 总经验值
     * @author Lounode
     * @date 2024/08/14
     */
    public static float getTotalExp(@This Player player)
    {
        float exp = player.getNeedExpToNextLevel() * player.getExp();
        int currentLevel = player.getLevel();

        while (currentLevel > 0) {
            currentLevel--;
            exp += ExpUtil.getNeedExpTotalAtLevel(currentLevel);
        }

        if (exp < 0)
        {
            exp = Float.MAX_VALUE;
        }
        return exp;
    }

    /**
     * 获取玩家当前的经验点数
     *
     * @param player 玩家
     * @return float 玩家当前的经验点数
     * @author Lounode
     * @date 2024/08/19
     */
    public static float getExpPoint(@This Player player) {
        return player.getNeedExpToNextLevel() * player.getExp();
    }
    /**
     * comments:<br>
     * 获取玩家到下个等级需要多少经验值
     *
     * @param player 玩家
     * @return float - 需求经验值
     * @author Lounode
     * @date 2024/08/14
     */
    public static float getNeedExpTotalToNextLevel(@This Player player) {
        return ExpUtil.getNeedExpToNextLevel(player.getLevel(), player.getExpPoint());
    }
    /**
     * comments:<br>
     * 获取当前玩家升级到下一级所需的总经验值
     *
     * @param player 玩家
     * @return int
     * @author Lounode
     * @date 2024/08/14
     */
    public static int getNeedExpToNextLevel(@This Player player) {
        return ExpUtil.getNeedExpTotalAtLevel(player.getLevel());
    }

}