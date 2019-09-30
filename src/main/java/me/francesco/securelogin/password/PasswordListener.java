package me.francesco.securelogin.password;

import me.francesco.securelogin.SecureLogin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PasswordListener implements Listener {

    private SecureLogin plugin;
    private PasswordManager passwordManager;

    public PasswordListener(SecureLogin p) {
        this.plugin = p;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        passwordManager = new PasswordManager(plugin);
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (plugin.getListManager().getAllList(p)) {
            e.setCancelled(true);
            String message = e.getMessage();
            if(plugin.getPlayerPassword().contains(p.getName())){
                if (message.equals(plugin.getPlayerPassword().getString(p.getName()))) {
                    plugin.getListManager().getPasswordPlayer().remove(p);
                    plugin.getColoredMessages().correctPassword(p);
                    plugin.getListManager().getPlayerSession().put(p.getName(), p.getAddress().getHostString());
                } else {
                    passwordManager.punishmentsCommand(p);
                }
            }else{
                if (message.equals(plugin.getConfig().getString("Password"))) {
                    plugin.getListManager().getPasswordPlayer().remove(p);
                    plugin.getColoredMessages().correctPassword(p);
                    plugin.getListManager().getPlayerSession().put(p.getName(), p.getAddress().getHostString());
                } else {
                    passwordManager.punishmentsCommand(p);
                }
            }
        }
    }
}