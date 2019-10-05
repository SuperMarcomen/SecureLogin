package me.francesco.securelogin.listener;

import fr.xephi.authme.api.v3.AuthMeApi;
import me.francesco.securelogin.SecureLogin;
import me.francesco.securelogin.randomblockgui.GUIManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;


public class ActionListener implements Listener {

    private SecureLogin plugin;
    private GUIManager guiManager;

    public ActionListener(SecureLogin p){
        this.plugin = p;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        guiManager = new GUIManager(plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(plugin.isAuthmeEnabled()){
            AuthMeApi authMeApi = AuthMeApi.getInstance();
            if((!(authMeApi.isAuthenticated(p)) || (!(authMeApi.isRegistered(p.getName()))))) return;
        }
        plugin.getJoinEvent().join(p);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(!plugin.getListManager().getAllList(p)) return;
        plugin.getColoredMessages().cantBreak(p);
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(!plugin.getListManager().getAllList(p)) return;
        plugin.getColoredMessages().cantPlace(p);
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(!plugin.getListManager().getAllList(p)) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerFoodLevelChangeEvent(FoodLevelChangeEvent e){
        Player p = (Player) e.getEntity();
        if(!plugin.getListManager().getAllList(p)) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHealChangeEvent(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if(!plugin.getListManager().getAllList(p)) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(!plugin.getListManager().getAllList(p)) return;
        p.teleport(e.getFrom());
    }

    @EventHandler
    public void onPlayerChangeGamemodeEvent(PlayerGameModeChangeEvent e){
        Player p = e.getPlayer();
        if(!plugin.getListManager().getAllList(p)) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPreprocessCommandEvent(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        if(!plugin.getListManager().getAllList(p)) return;
        plugin.getColoredMessages().noCommand(p);
        e.setCancelled(true);
    }
}