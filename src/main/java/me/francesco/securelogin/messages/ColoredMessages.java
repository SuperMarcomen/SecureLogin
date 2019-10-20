package me.francesco.securelogin.messages;

import me.francesco.securelogin.SecureLogin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColoredMessages {

    private SecureLogin plugin;

    public ColoredMessages(SecureLogin p){
        this.plugin = p;
    }

    public void playerFound(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.player-found"))); }

    public void playerNotFound(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.player-not-found"))); }

    public void setPersonalPassword(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.set-personal-password"))); }

    public void correctPassword(Player p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.correct-password"))); }

    public void enterPassword(Player p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.enter-password"))); }

    public void configReload(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.reload-config"))); }

    public void noPerms(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.no-permission"))); }

    public void newGlobalPassword(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.new-global-password"))); }

    public void sessionRestored(Player p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.active-session"))); }

    public void noArgs(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.no-arguments"))); }

    public void errorSetRows(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.wrong-rows-number"))); }

    public void setRows(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.set-rows"))); }

    public void onlyPlayer(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.only-player"))); }

    public void cantBreak(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.cannot-break"))); }

    public void cantPlace(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.cannot-place"))); }

    public void noCommand(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.no-command"))); }

    public void titleSet(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.title-set"))); }

    public void noCommandBlock(CommandSender p){ p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.no-command-block"))); }

    private String hasGlow(){ return ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.has-glow")); }

    private String hasNotGlow(){ return ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.has-not-glow")); }

    public void itemSet(CommandSender p){
        String s = ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.item-set"))
                .replaceAll("%item", plugin.getCaptcha().getString("Captcha.Item.material"))
                .replaceAll("%data", plugin.getCaptcha().getString("Captcha.Item.data"))
                .replaceAll("%amount", plugin.getCaptcha().getString("Captcha.Item.amount"))
                .replaceAll("%hasGlow", plugin.getCaptcha().getBoolean("Captcha.Item.glow") ? plugin.getColoredMessages().hasGlow() : plugin.getColoredMessages().hasNotGlow() );
        p.sendMessage(s);
    }

    public void decorationSet(CommandSender p){
        String s = ChatColor.translateAlternateColorCodes('&', plugin.getLang().getString("Messages.item-set"))
                .replaceAll("%item", plugin.getCaptcha().getString("Captcha.Decoration.material"))
                .replaceAll("%data", plugin.getCaptcha().getString("Captcha.Decoration.data"))
                .replaceAll("%amount", plugin.getCaptcha().getString("Captcha.Decoration.amount"))
                .replaceAll("%hasGlow", plugin.getCaptcha().getBoolean("Captcha.Decoration.glow") ? plugin.getColoredMessages().hasGlow() : plugin.getColoredMessages().hasNotGlow() );
        p.sendMessage(s);
    }
}