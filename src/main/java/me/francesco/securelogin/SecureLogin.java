package me.francesco.securelogin;

import me.francesco.securelogin.hook.AuthMeListenerHook;
import me.francesco.securelogin.listener.ActionListener;
import me.francesco.securelogin.messages.ColoredMessages;
import me.francesco.securelogin.messages.StringListMessages;
import me.francesco.securelogin.password.PasswordCommand;
import me.francesco.securelogin.password.PasswordListener;
import me.francesco.securelogin.password.PasswordManager;
import me.francesco.securelogin.util.FileManager;
import me.francesco.securelogin.util.JoinEvent;
import me.francesco.chooseyourtexture.randomblockgui.GUIListener;
import me.francesco.securelogin.util.ListManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SecureLogin extends JavaPlugin {

    private static SecureLogin plugin;
    private FileManager lang;
    private FileManager config;
    private FileManager captcha;
    private FileManager playerpassword;
    private String version;
    private ListManager listManager;
    private JoinEvent joinEvent;
    private StringListMessages stringListMessages;
    private ColoredMessages coloredMessages;

    @Override
    public void onEnable() {
        plugin = this;
        listManager = new ListManager();
        joinEvent = new JoinEvent(this);
        version = plugin.getVersion();
        stringListMessages = new StringListMessages(this);
        new GUIListener(this);
        new ActionListener(this);
        new PasswordCommand(this);
        new PasswordManager(this);
        new PasswordListener(this);
        if(isAuthmeEnabled()) new AuthMeListenerHook(this);
        stringListMessages.startupMessage();
        coloredMessages = new ColoredMessages(this);
        lang = new FileManager(getDataFolder(), "lang.yml");
        config = new FileManager(getDataFolder(), "config.yml");
        captcha = new FileManager(getDataFolder(), "captcha.yml");
        playerpassword = new FileManager(new File(getDataFolder(), "Players"), "password.yml");
        if(!(isPasswordEnable()) && !(isCaptchaEnable())){ getServer().getConsoleSender().sendMessage("§cAny protection enabled");}
    }

    @Override
    public FileConfiguration getConfig(){ return this.config.getConfig(); }
    @Override
    public void saveConfig(){ this.config.saveConfig(); }
    @Override
    public void reloadConfig(){ this.config.reloadConfig(); }
    @Override
    public void saveDefaultConfig(){ this.config.saveDefaultConfig(); }

    public static SecureLogin getInstance() { return plugin; }
    public FileManager getConfigYML(){ return this.config; }
    public FileManager getPlayerPasswordYML(){ return this.playerpassword; }
    public FileManager getLangYML(){ return this.lang; }
    public FileManager getCaptchaYML(){ return this.captcha; }
    public FileConfiguration getLang(){ return this.lang.getConfig(); }
    public FileConfiguration getCaptcha(){ return this.captcha.getConfig(); }
    public FileConfiguration getPlayerPassword(){ return this.playerpassword.getConfig(); }
    public String getVersion(){ return version; }

    public ListManager getListManager(){ return this.listManager; }
    public ColoredMessages getColoredMessages(){ return this.coloredMessages; }
    public JoinEvent getJoinEvent(){ return this.joinEvent; }

    public boolean isAuthmeEnabled(){
        try{
            Class.forName("fr.xephi.authme.AuthMe");
            return true;
        }catch (ClassNotFoundException e){
            return false;
        }
    }
    public boolean isPasswordEnable(){ return getConfig().getBoolean("Active-password"); }
    public boolean isCaptchaEnable(){ return getConfig().getBoolean("Active-captcha"); }
    public boolean isSessionEnable(){ return getConfig().getBoolean("Active-session"); }
}