package me.francesco.securelogin.password;

import me.francesco.securelogin.SecureLogin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PasswordManager {

    private SecureLogin plugin;

    public PasswordManager(SecureLogin p){
        this.plugin = p;
    }

    public void punishmentsCommand(Player p){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (String s : plugin.getConfig().getStringList("Wrong-password-execute-commands")) {
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), s
                            .replaceAll("%player", p.getName()));
                }
            }
        }.runTask(plugin);
    }
}
