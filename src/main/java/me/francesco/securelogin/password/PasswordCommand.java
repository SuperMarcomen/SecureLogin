package me.francesco.securelogin.password;

import me.francesco.securelogin.SecureLogin;
import me.francesco.securelogin.messages.StringListMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PasswordCommand implements CommandExecutor {

    private SecureLogin plugin;
    private StringListMessages stringListMessages;

    public PasswordCommand(SecureLogin p) {
        this.plugin = p;
        stringListMessages = new StringListMessages(p);
        p.getCommand("securelogin").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("securelogin.command.password")) return false;
        if (args.length == 0) {
            stringListMessages.getHelpMessage(sender);
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "setglobalpassword":
                if (!(sender instanceof ConsoleCommandSender)) break;

                if (!sender.hasPermission("securelogin.command.setglobalpassword")) {
                    plugin.getColoredMessages().noPerms(sender);
                    break;
                }

                if (args.length == 2) {
                    plugin.getConfig().set("Password", args[1]);
                    plugin.getConfigYML().saveConfig();
                    plugin.getColoredMessages().newGlobalPassword(sender);
                } else {
                    stringListMessages.getHelpMessage(sender);
                }

                break;

            case "setpassword":
                if (!sender.hasPermission("securelogin.command.setpassword")) {
                    plugin.getColoredMessages().noPerms(sender);
                    break;
                }

                switch (args.length) {
                    case 3: {
                        boolean b = false;
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.getName().equals(args[1])) {
                                b = true;
                                plugin.getPlayerPassword().set(player.getName(), args[2]);
                                plugin.getPlayerPasswordYML().saveConfig();
                                plugin.getListManager().getPlayerSession().put(player.getName(), player.getAddress().getHostString());
                            }
                        }

                        if (b) {
                            plugin.getColoredMessages().playerFound(sender);
                        } else {
                            plugin.getColoredMessages().playerNotFound(sender);
                        }

                        break;
                    }
                    case 2: {
                        plugin.getColoredMessages().setPersonalPassword(sender);
                    }
                    default:
                        stringListMessages.getHelpMessage(sender);

                }

                break;

            case "reload":
                if (!sender.hasPermission("securelogin.admin.reload")) {
                    plugin.getColoredMessages().noPerms(sender);
                    break;
                }

                if (args.length != 1) break;
                plugin.reloadConfig();
                plugin.getLangYML().reloadConfig();
                plugin.getCaptchaYML().reloadConfig();
                plugin.getPlayerPasswordYML().reloadConfig();
                plugin.getColoredMessages().configReload(sender);

                break;

            default:
                stringListMessages.getHelpMessage(sender);
        }

        return false;
    }
}

