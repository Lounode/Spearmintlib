package Spearmintlib.extensions.org.bukkit.inventory.ItemStack;

import github.lounode.spearmintlib.bukkit.nms.MinecraftVersion;
import lombok.experimental.UtilityClass;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;

/**
 * {@link ItemStack} 的扩展方法
 *
 * @author Lounode
 * @date 2024/08/19
 */
@UtilityClass
@Extension
public class ItemStackExtension {
    /**
     * 增强 {@link ItemMeta#getDisplayName}<br>
     * 在无自定义名称的情况下返回大写内建ID<br>
     * 对于成书在无自定义名称的情况下特定的返回其标题 {@link BookMeta#getTitle()}
     *
     * @param itemStack {@link ItemStack}
     * @return {@link String } 物品名
     * @author Lounode
     * @date 2024/05/15
     */
    public static String getDisplayName(@This ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        String itemDisplayName = itemMeta.getDisplayName();

        if (itemDisplayName == null) {
            itemDisplayName = itemStack.getType().name();
            //Book Title
            if (itemMeta instanceof BookMeta) {
                BookMeta meta = (BookMeta) itemStack.getItemMeta();
                itemDisplayName = meta.getTitle();
            }
        }
        return itemDisplayName;
    }
    /*
    public static String getLocaleKey(@This ItemStack itemStack) {
        //val nmsItemStack = getNMSCopy(itemStack) as net.minecraft.server.v1_12_R1.ItemStack
        //val nmsItem = nmsItemStack.item
        if (MinecraftVersion.isHigherOrEqual(MinecraftVersion.V1_13)) {
            //TODO
        } else {
            if (itemStack.getItemMeta() instanceof SpawnEggMeta) {
                SpawnEggMeta meta = (SpawnEggMeta) itemStack.getItemMeta();
                EntityType entityType = meta.getSpawnedType();
                return "entity."+ entityType.name() + ".name";
            }
            // 获取译名
            val localeName = itemLocaleNameMethod.invoke(nmsItem, nmsItemStack).toString()
                    val localeLanguage = net.minecraft.server.v1_8_R3.LocaleI18n::class.java.getProperty<net.minecraft.server.v1_8_R3.LocaleLanguage>("a", true, remap = false)!!
                    val localeMap = localeLanguage.getProperty<Map<String, String>>("d", remap = false)!!
                    // 逆向查找语言文件节点
                    val localeKey = localeMap.entries.firstOrNull { it.value == localeName }?.key
            if (localeKey == null) {
                // 对于一些特殊的物品，例如：修改 SkullOwner 后的头、成书等，译名会被修改，导致无法获取到语言文件节点。
                itemLocaleKeyMethod ?: error("Unsupported item: ${itemStack.type}.")
                var name = itemLocaleKeyMethod.invoke(nmsItem, nmsItemStack)?.toString() ?: error("Unsupported item ${itemStack.type}")
                // 如果物品不以 .name 结尾，则添加 .name 后缀
                if (!name.endsWith(".name")) {
                    name += ".name"
                }
                LocaleKey("S", name)
            } else {
                LocaleKey("N", localeKey)
            }
        }
    }

     */
}