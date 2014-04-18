package me.renegadeeagle.wgessentials.listeners;

import me.renegadeeagle.wgessentials.WGEssentials;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Ryan on 4/17/2014.
 */
public class CropTrampleListener implements Listener {
    public WGEssentials instance;
    public CropTrampleListener(WGEssentials instance){
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerTrampleEvent(PlayerInteractEvent event){
        if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL) {
            if (!instance.getWorldGuard().getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()).allows(instance.getCroptrample())) {
                event.setCancelled(true);
            }
        }
    }
}
