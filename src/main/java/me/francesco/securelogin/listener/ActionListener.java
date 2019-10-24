package me.francesco.securelogin.listener;

import fr.xephi.authme.api.v3.AuthMeApi;
import me.francesco.securelogin.SecureLogin;
import me.francesco.securelogin.randomblockgui.GUIManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;


public class ActionListener implements Listener {

    private SecureLogin plugin;

    public ActionListener(SecureLogin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        if(plugin.isAuthmeEnabled()) {
            AuthMeApi authMeApi = AuthMeApi.getInstance();
            
            if((!(authMeApi.isAuthenticated(player)) || (!(authMeApi.isRegistered(player.getName()))))) return;
        }
        
        plugin.getJoinEvent().join(player);
    }
    
    private void check(Player player, Cancellable event) {
        if(!plugin.getListManager().getAllList(player)) return;

        plugin.getColoredMessages().cantBreak(player);
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        check(event.getPlayer(), event);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        check(event.getPlayer(), event);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if(!plugin.getListManager().getAllList(p)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
        if(!plugin.getListManager().getAllList(p)) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHealChangeEvent(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;

        check((Player) event.getEntity(), event);
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        check(event.getPlayer(), event);
    }

    @EventHandler
    public void onPlayerChangeGamemodeEvent(PlayerGameModeChangeEvent event) {
        check(event.getPlayer(), event);
    }

    @EventHandler
    public void onPlayerPreprocessCommandEvent(PlayerCommandPreprocessEvent event) {
        check(event.getPlayer(), event);
    }

    @EventHandler
    public void onPlayerChangeWorldEvent(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().toString();
        for(String s : plugin.getConfig().getStringList("worlds")) {
            if(world.equals(s)) {
                plugin.getJoinEvent().join(p);
                return;
            }
        }
    }
}