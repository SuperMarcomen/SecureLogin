package me.francesco.securelogin.randomblockgui;

import me.francesco.securelogin.SecureLogin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUICommand implements CommandExecutor {

    private SecureLogin plugin;

    public GUICommand(SecureLogin p) {
        this.plugin = p;
        p.getCommand("randomblockgui").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("securelogin.command.randomblockgui")) {
            if (args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "setrows":
                        if (args.length == 2) {
                            int i = Integer.valueOf(args[1]);
                            if (i <= 6 && i >= 1) {
                                plugin.getCaptcha().set("Captcha.rows", i);
                                plugin.getCaptchaYML().saveConfig();
                                plugin.getColoredMessages().setRows(sender);
                            } else {
                                plugin.getColoredMessages().errorSetRows(sender);
                            }
                            return true;
                        }
                        plugin.getColoredMessages().noArgs(sender);
                        break;
                    case "settitle":
                        if (args.length > 2) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                sb.append(args[i]);
                                sb.append(" ");
                            }
                            sb.deleteCharAt(sb.length() - 1);
                            plugin.getCaptcha().set("Captcha.gui-name", sb.toString());
                            plugin.getCaptchaYML().saveConfig();
                        }
                        break;
                    case "setdecoration":
                        if(!(sender instanceof Player)){
                            plugin.getColoredMessages().onlyPlayer(sender);
                            return true;
                        }
                        Player p = (Player) sender;
                        ItemStack is = p.getItemInHand();
                        plugin.getCaptcha().set("Captcha.Decoration.name", is.getItemMeta().getDisplayName().replaceAll("\\xa7", "&"));
                        plugin.getCaptcha().set("Captcha.Decoration.material", is.getData().getItemType().toString());
                        plugin.getCaptcha().set("Captcha.Decoration.data", is.getData().getData());
                        plugin.getCaptcha().set("Captcha.Decoration.amount", is.getAmount());
                        plugin.getCaptcha().set("Captcha.Decoration.glow", is.getItemMeta().hasEnchants());
                        plugin.getStringListMessages().setItemLore();
                        plugin.getCaptchaYML().saveConfig();
                        break;
                    case "setitem":
                        break;
                }
                return true;
            }
            plugin.getStringListMessages().getHelpMessage(sender);
            return true;
        }
        plugin.getStringListMessages().getHelpMessage(sender);
        return true;
    }
}