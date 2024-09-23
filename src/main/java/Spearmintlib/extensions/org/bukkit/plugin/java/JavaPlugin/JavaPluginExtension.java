package Spearmintlib.extensions.org.bukkit.plugin.java.JavaPlugin;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.bukkit.plugin.java.JavaPlugin;

@Extension
public class JavaPluginExtension {
  public static void helloWorld(@This JavaPlugin thiz) {
    System.out.println("hello world!");
  }
}