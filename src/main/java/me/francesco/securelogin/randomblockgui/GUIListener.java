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
    public void onInventoryClickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getClickedInventory() == null) return;
        if(!event.getView().getTitle().equals(guiManager.getTitle())) return;

        event.setCancelled(true);

        if(event.getSlot() != plugin.getListManager().getCaptchaSlot().get(player)) return;

        plugin.getListManager().getCaptchaSlot().remove(player);
        plugin.getListManager().getPlayerSession().put(player.getName(), player.getAddress().getHostString());
        player.closeInventory();

        if(player.hasPermission("securelogin.password.on") && plugin.isPasswordEnable()){
            plugin.getListManager().getPasswordPlayer().add(player);
            plugin.getColoredMessages().enterPassword(player);
        }

    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if(plugin.getListManager().getCaptchaSlot().containsKey(player)){
            new BukkitRunnable(){
                @Override
                public void run(){
                    player.openInventory(guiManager.createInventory(player));
                }
            }.runTaskLater(plugin, 5L);
        }
    }

}
