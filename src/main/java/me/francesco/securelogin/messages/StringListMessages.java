package me.francesco.securelogin.messages;

import me.francesco.securelogin.SecureLogin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class StringListMessages {

    private SecureLogin plugin;

    public StringListMessages(SecureLogin p) {
        this.plugin = p;
    }

    public List<String> getBlockLore() {
        List<String> list = new ArrayList<>();
        for (String s : plugin.getCaptcha().getStringList("Captcha.Item.lore")) {
            list.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return list;
    }

    public List<String> getDecorationLore() {
        List<String> list = new ArrayList<>();
        for (String s : plugin.getCaptcha().getStringList("Captcha.Decoration.lore")) {
            list.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return list;
    }

    public void setItemLore(){
        List<String> list2 = new ArrayList<>();
        for(String s: plugin.getCaptcha().getStringList("Captcha.Decoration.lore")){
            list2.add(s.replaceAll("\\xa7", "&"));
        }
        plugin.getCaptcha().set("Captcha.Decoration.lore", list2);
        plugin.getCaptchaYML().saveConfig();
    }

    public void getHelpMessage(CommandSender cs) {
        String[] args;
        for (String s : plugin.getLang().getStringList("Help-messages")) {
            cs.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
        }
    }

    public void startupMessage() {
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&m+----------------------------------------------------------+"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a   _____                          _                 _       "));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a  / ____|                        | |               (_)      "));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a | (___   ___  ___ _   _ _ __ ___| |     ___   __ _ _ _ __  "));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a  \\___ \\ / _ \\/ __| | | | '__/ _ \\ |    / _ \\ / _` | | '_ \\ "));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a  ____) |  __/ (__| |_| | | |  __/ |___| (_) | (_| | | | | |"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a |_____/ \\___|\\___|\\__,_|_|  \\___|______\\___/ \\__, |_|_| |_|"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a                                               __/ |        "));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a                                              |___/         "));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&m+----------------------------------------------------------+"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&aSecureLogin By Pompiere1"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&cSpigotMC: &ahttps://www.spigotmc.org/members/pompiere1.404669/"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&cGithub: &ahttps://github.com/Pompiere1"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&cTelegram: &a@Pompiere1"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&aI'm looking for plugins compatible with me"));
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        if (plugin.isAuthmeEnabled())
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAuthMe: &aYes"));
        else
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAuthMe: &cNo"));
    }
}
