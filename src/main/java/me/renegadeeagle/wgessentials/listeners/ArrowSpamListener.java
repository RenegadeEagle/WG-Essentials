package me.renegadeeagle.wgessentials.listeners;

import me.renegadeeagle.wgessentials.WGEssentials;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Ryan on 4/11/2014.
 */
public class ArrowSpamListener implements Listener {
    public WGEssentials instance;
    public ArrowSpamListener(WGEssentials instance){
        this.instance = instance;
    }

    public boolean hasEnchantedItem(Player p, ItemStack i, Enchantment e) {
        ItemStack[] inv = p.getInventory().getContents();
        for (ItemStack item : inv) {
            if (item.getType().equals(i.getType())) {
                if (i.getEnchantments().containsKey(e)) {
                    return true;
                }
            }
        }
        return false;
    }
    @EventHandler
    public void onRightClickEvent(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if(event.getPlayer().getItemInHand().getType() == Material.BOW){
                if (!instance.getWorldGuard().getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()).allows(instance.getAllowArrow())){
                    event.getPlayer().sendMessage(ChatColor.DARK_RED+"You cannot use arrows in this region!");
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onEntityLandEvent(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow){
            Arrow arrow = (Arrow) event.getEntity();
            if (!instance.getWorldGuard().getRegionManager(event.getEntity().getWorld()).getApplicableRegions(event.getEntity().getLocation()).allows(instance.getAllowArrow())){
                arrow.remove();
                if(arrow.getShooter() instanceof  Player) {
                    Player player = (Player) arrow.getShooter();
                    player.sendMessage(ChatColor.DARK_RED+"You cannot use arrows in this region!");
                }
            }
        }
    }
}
