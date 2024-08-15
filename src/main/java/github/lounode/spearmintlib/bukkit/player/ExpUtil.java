package github.lounode.spearmintlib.bukkit.player;

import org.bukkit.entity.Player;

public class ExpUtil {
    /**
     * comments:<br>
     * 设置玩家的总经验值
     *
     * @param player 被设置经验的玩家
     * @param exp    经验值
     * @author Lounode
     * @date 2024/08/14
     */
    public static void setTotalExp(Player player, float exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Experience is negative!");
        }
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);

        float amount = exp;
        while (amount > 0) {
            final int expToLevel = getExpAtLevel(player);
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
    public static float getTotalExp(Player player)
    {
        float exp = Math.round(getExpAtLevel(player) * player.getExp());
        int currentLevel = player.getLevel();

        while (currentLevel > 0) {
            currentLevel--;
            exp += getExpAtLevel(currentLevel);
        }

        if (exp < 0)
        {
            exp = Float.MAX_VALUE;
        }
        return exp;
    }

    /**
     * comments:<br>
     * 获取到当前等级所需的所有经验
     *
     * @param level 目标等级
     * @return int - 经验需求
     * @author Lounode
     * @date 2024/08/14
     */
    public static int getTotalExpAtLevel(int level)
    {
        int currentLevel = 0;
        int exp = 0;

        while (currentLevel < level)
        {
            exp += getExpAtLevel(currentLevel);
            currentLevel++;
        }
        if (exp < 0)
        {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

    /**
     * comments:<br>
     * 获取当前等级到下一级还所需多少经验
     *
     * @param level 当前等级
     * @param exp   当前经验值
     * @return float - 需求经验值
     * @author Lounode
     * @date 2024/08/14
     */
    public static float getExpToNextLevel(int level, float exp) {
        return getExpAtLevel(level) - exp;
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
    public static float getExpToNextLevel(Player player) {
        return getExpToNextLevel(player.getLevel(), player.getExp());
    }

    /**
     * comments:<br>
     * 获取当前等级升级到下一级所需的总经验值
     *
     * @param level 等级
     * @return int - 经验值
     * @author Lounode
     * @date 2024/08/14
     */
    public static int getExpAtLevel(int level)
    {
        if (level < 0) {
            throw new IllegalArgumentException("Level is negative!");
        }
        //0-15
        if (level <= 15) {
            return (2 * level) + 7;
        }
        //16-30
        if (level <= 30) {
            return (5 * level) - 38;
        }
        //31+
        return (9 * level) - 158;
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
    public static int getExpAtLevel(Player player) {
        return getExpAtLevel(player.getLevel());
    }
}
