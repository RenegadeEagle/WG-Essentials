package me.renegadeeagle.wgessentials;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;
import me.renegadeeagle.wgessentials.listeners.AnvilDurrabilityListener;
import me.renegadeeagle.wgessentials.listeners.ArrowSpamListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * Created by Ryan on 4/11/2014.
 */
public class WGEssentials extends JavaPlugin{

    public static StateFlag AnvilFlag = new StateFlag("anvilbreak", true);
    public static StateFlag arrow = new StateFlag("arrow", true);
    public void onEnable() {
        this.getWorldGuard();
        this.getWGCustomFlags().addCustomFlag(AnvilFlag);
        this.getWGCustomFlags().addCustomFlag(arrow);
        this.getServer().getPluginManager().registerEvents(new AnvilDurrabilityListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ArrowSpamListener(this), this);
    }
    public void onDisable(){

    }
    public WorldGuardPlugin getWorldGuard() {
        Plugin wg = this.getServer().getPluginManager().getPlugin("WorldGuard");
        if ((wg == null) || (!(wg instanceof WorldGuardPlugin))) {
            return null;
        }
        return (WorldGuardPlugin)wg;
    }

    public WGCustomFlagsPlugin getWGCustomFlags()	{
        Plugin plugin = getServer().getPluginManager().getPlugin("WGCustomFlags");
        if (plugin == null || !(plugin instanceof WGCustomFlagsPlugin)) {
            return null;
        }
        return (WGCustomFlagsPlugin) plugin;
    }
    public static StateFlag getAnvilFlag() {
        return AnvilFlag;
    }
    public static StateFlag getAllowArrow() {
        return arrow;
    }
}

