package me.francesco.securelogin.password;

import me.francesco.securelogin.SecureLogin;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class PasswordListener implements Listener {

    private SecureLogin plugin;
    private PasswordManager passwordManager;

    public PasswordListener(SecureLogin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        passwordManager = new PasswordManager(plugin);
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getListManager().getAllList(player)) return;
        event.setCancelled(true);
        String message = event.getMessage();

        if (plugin.getPlayerPassword().contains(player.getName())) {
            if (message.equals(plugin.getPlayerPassword().getString(player.getName()))) {
                plugin.getListManager().getPasswordPlayer().remove(player);
                plugin.getColoredMessages().correctPassword(player);
                plugin.getListManager().getPlayerSession().put(player.getName(), player.getAddress().getHostString());
            } else {
                passwordManager.punishmentsCommand(player);
            }
        } else {
            if (message.equals(plugin.getConfig().getString("Password"))) {
                plugin.getListManager().getPasswordPlayer().remove(player);
                plugin.getColoredMessages().correctPassword(player);
                plugin.getListManager().getPlayerSession().put(player.getName(), player.getAddress().getHostString());
            } else {
                passwordManager.punishmentsCommand(player);
            }
        }

    }

}