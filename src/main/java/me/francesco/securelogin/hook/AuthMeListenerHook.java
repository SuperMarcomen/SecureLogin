package me.francesco.securelogin.hook;

import fr.xephi.authme.events.LoginEvent;
import me.francesco.securelogin.SecureLogin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AuthMeListenerHook implements Listener {

    private SecureLogin plugin;

    public AuthMeListenerHook(SecureLogin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerAuthMeLoginEvent(LoginEvent event){
        Player pl = event.getPlayer();
        if (pl.getAddress().getHostString().equals(plugin.getListManager().getPlayerSession().get(pl.getName()))) return;
        plugin.getJoinEvent().join(pl);
    }
}