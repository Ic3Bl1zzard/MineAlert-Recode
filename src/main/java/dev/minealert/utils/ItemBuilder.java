package dev.minealert.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemBuilder {

    public static class Builder {

        private static Builder instance = null;

        public static Builder getInstance() {
            if(instance == null){
                instance = new Builder();
            }

            return instance;
        }

        private Material material;

        private int amount;

        private byte id;

        private String name;

        private List<String> lore;

        private Map<Enchantment, Integer> enchantmentMap;

        public Builder itemType(Material material) {
            this.material = material;
            return this;
        }

        public Builder itemAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder itemID(byte id) {
            this.id = id;
            return this;
        }

        public Builder itemName(String name) {
            this.name = FormatUtils.color(name);
            return this;
        }

        public Builder itemLore(List<String> lores) {
            lore = lores.stream().map(FormatUtils::color).collect(Collectors.toList());
            return this;
        }

        public Builder itemEnchant(Map<Enchantment, Integer> enchants) {
            this.enchantmentMap = enchants;
            return this;
        }

        public ItemStack build() {
            ItemStack item;
            if (id != -1) {
                item = new ItemStack(material, amount, id);
            } else {
                item = new ItemStack(material, amount);
            }
            if (enchantmentMap != null) {
                item.addUnsafeEnchantments(enchantmentMap);
            }
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(name);
                meta.setLore(lore);
            }
            item.setItemMeta(meta);
            return item;
        }
    }
}
