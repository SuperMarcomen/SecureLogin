package me.francesco.securelogin.util;

import me.francesco.securelogin.SecureLogin;
import me.francesco.securelogin.randomblockgui.GUIManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ProtectionManager {

    private SecureLogin plugin;
    private GUIManager guiManager;

    public ProtectionManager(SecureLogin p){
        this.plugin = p;
        guiManager = new GUIManager(plugin);
    }

    public void join(Player p){
        if(!p.hasPermission("securelogin.securelogin")) return;
        if(plugin.isSessionEnable()){
            if(p.getAddress().getHostString().equals(plugin.getListManager().getPlayerSession().get(p.getName()))){
                plugin.getColoredMessages().sessionRestored(p);
                return;
            }
        }
        if(p.hasPermission("securelogin.captcha.on") && plugin.isCaptchaEnable()) {
            plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    p.openInventory(guiManager.createInventory(p));
                }
            }, 10L);
        }else if(p.hasPermission("securelogin.password.on") && plugin.isPasswordEnable()){
            plugin.getListManager().getPasswordPlayer().add(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.enter-password")));
        }
    }
}