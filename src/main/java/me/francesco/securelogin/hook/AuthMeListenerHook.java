package me.francesco.securelogin.hook;

import fr.xephi.authme.api.v3.AuthMeApi;
import fr.xephi.authme.events.LoginEvent;
import me.francesco.securelogin.SecureLogin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AuthMeListenerHook implements Listener {

    private SecureLogin plugin;
    private AuthMeApi authMeApi;

    public AuthMeListenerHook(SecureLogin p){
        this.plugin = p;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        authMeApi = AuthMeApi.getInstance();
    }

    @EventHandler
    public void onPlayerAuthMeLoginEvent(LoginEvent e){
        Player p = e.getPlayer();
        if (p.getAddress().getHostString().equals(plugin.getListManager().getPlayerSession().get(p.getName()))) return;
        plugin.getJoinEvent().join(p);
    }
}