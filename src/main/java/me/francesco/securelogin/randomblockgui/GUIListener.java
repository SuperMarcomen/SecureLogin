package me.francesco.securelogin.randomblockgui;

import me.francesco.securelogin.SecureLogin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class GUIListener implements Listener {

    private SecureLogin plugin;
    private GUIManager guiManager;

    public GUIListener(SecureLogin p){
        this.plugin = p;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        guiManager = new GUIManager(plugin);
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getClickedInventory() == null) return;
        if(!e.getClickedInventory().getName().equals(guiManager.getTitle())) return;
        e.setCancelled(true);
        if(e.getSlot() == plugin.getListManager().getCaptchaSlot().get(p)){
            plugin.getListManager().getCaptchaSlot().remove(p);
            plugin.getListManager().getPlayerSession().put(p.getName(), p.getAddress().getHostString());
            p.closeInventory();
            if(p.hasPermission("securelogin.password.on") && plugin.isPasswordEnable()){
                plugin.getListManager().getPasswordPlayer().add(p);
                plugin.getColoredMessages().enterPassword(p);
            }
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        if(plugin.getListManager().getCaptchaSlot().containsKey(p)){
            new BukkitRunnable(){
                @Override
                public void run(){
                    p.openInventory(guiManager.createInventory(p));
                }
            }.runTaskLater(plugin, 5L);
        }
    }

}
