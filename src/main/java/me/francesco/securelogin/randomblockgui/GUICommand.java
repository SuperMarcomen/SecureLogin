package me.francesco.securelogin.randomblockgui;

import me.francesco.securelogin.SecureLogin;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GUICommand implements CommandExecutor {

    private SecureLogin plugin;

    public GUICommand(SecureLogin p) {
        this.plugin = p;
        p.getCommand("randomblockgui").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("securelogin.command.randomblockgui")
        || !(args.length > 0)) {
            plugin.getStringListMessages().getHelpMessage(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "setrows":
                if (args.length == 2) {
                    int i = Integer.parseInt(args[1]);
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
                    plugin.getColoredMessages().titleSet(sender);
                }
                break;
            case "setdecoration":
                if(!(sender instanceof Player)){
                    plugin.getColoredMessages().onlyPlayer(sender);
                    return true;
                }

                Player p = (Player) sender;
                ItemStack is = p.getItemInHand();

                if(is.getData().getItemType().toString().contains("AIR")) return true;
                if(is.getItemMeta().getDisplayName() != null) plugin.getCaptcha().set("Captcha.Decoration.name", is.getItemMeta().getDisplayName().replaceAll("\\xa7", "&"));
                else plugin.getCaptcha().set("Captcha.Decoration.name", "");

                plugin.getCaptcha().set("Captcha.Decoration.material", is.getData().getItemType().toString());
                plugin.getCaptcha().set("Captcha.Decoration.data", is.getData().getData());
                plugin.getCaptcha().set("Captcha.Decoration.amount", is.getAmount());
                plugin.getCaptcha().set("Captcha.Decoration.glow", is.getItemMeta().hasEnchants());
                plugin.getStringListMessages().setItemLore();
                plugin.getColoredMessages().decorationSet(p);
                plugin.getCaptchaYML().saveConfig();

                break;
            case "setitem":
                if(!(sender instanceof Player)){
                    plugin.getColoredMessages().onlyPlayer(sender);
                    return true;
                }

                p = (Player) sender;
                is = p.getItemInHand();

                if(is.getData().getItemType().toString().contains("AIR")) return true;
                if(is.getItemMeta().getDisplayName() != null) plugin.getCaptcha().set("Captcha.Item.name", is.getItemMeta().getDisplayName().replaceAll("\\xa7", "&"));
                else plugin.getCaptcha().set("Captcha.Item.name", "");

                plugin.getCaptcha().set("Captcha.Item.material", is.getData().getItemType().toString());
                plugin.getCaptcha().set("Captcha.Item.data", is.getData().getData());
                plugin.getCaptcha().set("Captcha.Item.amount", is.getAmount());
                plugin.getCaptcha().set("Captcha.Item.glow", is.getItemMeta().hasEnchants());
                plugin.getStringListMessages().setItemLore();
                plugin.getColoredMessages().itemSet(p);
                plugin.getCaptchaYML().saveConfig();

                break;
            default:
                plugin.getStringListMessages().getHelpMessage(sender);
        }
        return true;
    }
}