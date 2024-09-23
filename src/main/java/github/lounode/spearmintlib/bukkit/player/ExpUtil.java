package github.lounode.spearmintlib.bukkit.player;

import lombok.experimental.UtilityClass;

/**
 * 玩家经验值相关的工具类
 *
 * @author Lounode
 * @date 2024/08/19
 */
@UtilityClass
public final class ExpUtil {
    /**
     * 获取当前等级升级到下一级所需的总经验值
     *
     * @param level 当前等级
     * @return int - 升级到下一级所需的总经验值
     * @author Lounode
     * @date 2024/08/14
     */
    public static int getNeedExpTotalAtLevel(int level)
    {
        if (level < 0) {
            throw new IllegalArgumentException("Level could not be negative!");
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
            exp += getNeedExpTotalAtLevel(currentLevel);
            currentLevel++;
        }
        if (exp < 0)
        {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }
    /**
     * 获取当前等级到下一级还所需多少经验
     *
     * @param level 当前等级
     * @param exp   当前经验值
     * @return float - 需求经验值
     * @author Lounode
     * @date 2024/08/14
     */
    public static float getNeedExpToNextLevel(int level, float exp) {
        return getNeedExpTotalAtLevel(level) - exp;
    }
}
