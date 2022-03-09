package net.minecraftchampion.ffaCore.manager;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class KitManager {

    public static final String DEFAULT_KIT_PATH = "default.";
    public static final String HELMET_PATH = "helmet.";
    public static final String CHESTPLATE_PATH = "chestplate.";
    public static final String LEGGINGS_PATH = "leggings.";
    public static final String BOOTS_PATH = "boots.";
    public static final String PROTECTION_LEVEL_PATH = "protection";
    public static final String SWORD_PATH = "sword.";
    public static final String SHARPNESS_LEVEL_PATH = "sharpness";
    public static final String ITEM_PATH = "item";
    public static final String KILL_REWARD_PATH = "kill-reward.";
    public static final String QUANTITY_PATH = "quantity";
    private final YamlConfiguration config;
    private final PlayerInventory pInv;

    public KitManager(YamlConfiguration kit, PlayerInventory inventory) {
        this.config = kit;
        this.pInv = inventory;
    }

    /**
     * Give the kit to the players
     */
    public void giveKit() {
        pInv.setHelmet(loadItemStack(DEFAULT_KIT_PATH + HELMET_PATH));
        pInv.setChestplate(loadItemStack(DEFAULT_KIT_PATH + CHESTPLATE_PATH));
        pInv.setLeggings(loadItemStack(DEFAULT_KIT_PATH + LEGGINGS_PATH));
        pInv.setBoots(loadItemStack(DEFAULT_KIT_PATH + BOOTS_PATH));
        pInv.setItem(0, loadItemStack(DEFAULT_KIT_PATH + SWORD_PATH));

        final ItemStack beef = new ItemStack(Material.COOKED_BEEF);
        beef.setAmount(64);

        pInv.setItem(8, beef);
    }

    /**
     *
     * @param path path
     * @return Item
     */
    private ItemStack loadItemStack(String path) {
        final String itemName = this.config.getString(path + ITEM_PATH);
        if (itemName == null) {
            return null;
        }

        final ItemStack item = new ItemStack(Material.getMaterial(itemName));
        final int enchantmentSharpness = this.config.getInt(path + SHARPNESS_LEVEL_PATH);
        final int enchantmentProtection = this.config.getInt(path + PROTECTION_LEVEL_PATH);
        if (enchantmentSharpness != 0) {
            item.addEnchantment(Enchantment.DAMAGE_ALL, enchantmentSharpness);
        }
        if (enchantmentProtection != 0) {
            item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, enchantmentProtection);
        }

        return item;
    }

}
