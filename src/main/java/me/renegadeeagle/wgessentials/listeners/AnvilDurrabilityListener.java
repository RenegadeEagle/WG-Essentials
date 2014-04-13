package me.renegadeeagle.wgessentials.listeners;

import me.renegadeeagle.wgessentials.WGEssentials;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.material.Directional;

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
                    Block anvil = event.getClickedBlock();
                    int data = anvil.getData();
                    if(data % 2 == 0){
                        anvil.setData((byte) 0);  //If the anvil's data is even, then it goes north/south. So setting it to 0 won't make a visual difference.
                    }else{
                        anvil.setData((byte) 1); //If the anvils data is odd, then it goes east/west. So setting it's data to 1 will not make a visual difference.
                    }
                }
            }
        }
    }
}

