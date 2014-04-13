package me.renegadeeagle.wgessentials.listeners;

import me.renegadeeagle.wgessentials.WGEssentials;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Ryan on 4/11/2014.
 */
public class AnvilDurrabilityListener implements Listener {

    public WGEssentials instance;
    public AnvilDurrabilityListener(WGEssentials instance){
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.ANVIL){
                if (!instance.getWorldGuard().getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()).allows(instance.getAnvilFlag())){
                    //TODO: Fix the fact that anvils will always face south
                    Block anvil = event.getClickedBlock();
                    anvil.setData((byte) 0); //We are to lazy to find the new method, so we will use decaprecated ones.
                }
            }
        }
    }
}
