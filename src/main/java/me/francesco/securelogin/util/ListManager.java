package me.francesco.securelogin.util;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListManager {

    private List<Player> passwordPlayer;
    private HashMap<String, String> playerMap;
    private HashMap<Player, Integer> captchaSlot;

    public ListManager(){
        this.passwordPlayer = new ArrayList<>();
        this.playerMap = new HashMap<>();
        this.captchaSlot = new HashMap<>();
    }

    public List<Player> getPasswordPlayer(){
        return this.passwordPlayer;
    }

    public HashMap<String, String> getPlayerSession(){ return this.playerMap; }

    public HashMap<Player, Integer> getCaptchaSlot(){ return this.captchaSlot; }

    public boolean getAllList(Player p){ return this.getPasswordPlayer().contains(p) || this.getCaptchaSlot().containsKey(p); }

}