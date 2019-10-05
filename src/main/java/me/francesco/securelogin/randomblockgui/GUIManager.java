package me.francesco.securelogin.randomblockgui;

import com.avaje.ebean.validation.NotNull;
import me.francesco.securelogin.SecureLogin;
import me.francesco.securelogin.messages.StringListMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class GUIManager {

    private SecureLogin plugin;
    private StringListMessages stringListMessages;

    public GUIManager(SecureLogin p){
        this.plugin = p;
        stringListMessages = new StringListMessages(plugin);
    }

    public Inventory createInventory(Player p){
        Inventory inv = Bukkit.createInventory(null, getSize(), getTitle());
        for(int i = 0; i < inv.getSize(); i++){
            inv.setItem(i, createDecoration());
        }
        Random random = new Random();
        int slot = random.nextInt(inv.getSize());
        plugin.getListManager().getCaptchaSlot().put(p, slot);
        inv.setItem(slot, createItem());
        return inv;
    }

    @NotNull
    private ItemStack createItem(){
        Material b = Material.getMaterial(plugin.getCaptcha().getString("Captcha.Item.material", "STONE"));
        if(b == null) b = Material.STONE;
        int blockAmount = plugin.getCaptcha().getInt("Captcha.Item.amount", 1);
        int blockData;
        blockData = plugin.getCaptcha().getInt("Captcha.Item.data", 0);
        ItemStack block = new ItemStack(b, blockAmount, (short) blockData);
        ItemMeta blockItemMeta = block.getItemMeta();
        blockItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getCaptcha().getString("Captcha.Item.name")));
        blockItemMeta.setLore(stringListMessages.getBlockLore());
        if(plugin.getCaptcha().getBoolean("Captcha.Item.glow")){
            blockItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            blockItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        block.setItemMeta(blockItemMeta);
        return block;
    }

    @NotNull
    private ItemStack createDecoration(){
        Material d = Material.getMaterial(plugin.getCaptcha().getString("Captcha.Decoration.material", "STONE"));
        int decorationAmount = plugin.getCaptcha().getInt("Captcha.Decoration.amount", 1);
        int decorationData;
        decorationData = plugin.getCaptcha().getInt("Captcha.Decoration.data", 0);
        ItemStack decoration = new ItemStack(d, decorationAmount, (short) decorationData);
        ItemMeta decorationItemMeta = decoration.getItemMeta();
        decorationItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getCaptcha().getString("Captcha.Decoration.name")));
        decorationItemMeta.setLore(stringListMessages.getDecorationLore());
        if(plugin.getCaptcha().getBoolean("Captcha.Decoration.glow")){
            decorationItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            decorationItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        decoration.setItemMeta(decorationItemMeta);
        return decoration;
    }

    private Integer getSize(){
        int size = plugin.getCaptcha().getInt("Captcha.rows");
        if(size < 1 || size > 6) size = 3;
        size *= 9;
        return size;
    }

    public String getTitle(){
        return ChatColor.translateAlternateColorCodes('&', plugin.getCaptcha().getString("Captcha.gui-name"));
    }
}
