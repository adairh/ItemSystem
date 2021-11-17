package net.wdlvn.IS.Util;

import net.wdlvn.IS.Attributes;
import net.wdlvn.IS.Util.API;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class AttrsItem {
    public static ItemStack armor(){
        return API.IS(Material.CHAINMAIL_CHESTPLATE,1, Arrays.asList(),API.Color("&b&l"+Attributes.ARMOR.toString()));
    }
    public static ItemStack magicArmor(){
        return API.IS(Material.CHAINMAIL_CHESTPLATE,1, Arrays.asList(),API.Color("&b&l"+Attributes.MAGICAL_ARMOR.toString()),Enchantment.ARROW_DAMAGE,1,ItemFlag.HIDE_ENCHANTS);
    }
    public static ItemStack damage(){
        return API.IS(Material.IRON_SWORD,1, Arrays.asList(),API.Color("&a&l"+Attributes.DAMAGE.toString()));
    }
    public static ItemStack magicdamage(){
        return API.IS(Material.IRON_SWORD,1, Arrays.asList(),API.Color("&a&l"+Attributes.MAGICAL_DAMAGE.toString()),Enchantment.ARROW_DAMAGE,1,ItemFlag.HIDE_ENCHANTS);
    }
    public static ItemStack pierce(){
        return API.IS(Material.ARROW,1, Arrays.asList(),API.Color("&5&l"+Attributes.PIERCE.toString()));
    }
    public static ItemStack magicPierce(){
        return API.IS(Material.ARROW,1, Arrays.asList(),API.Color("&5&l"+Attributes.MAGIC_PIERCE.toString()),Enchantment.ARROW_DAMAGE,1,ItemFlag.HIDE_ENCHANTS);
    }
    public static ItemStack crit(){
        return API.IS(Material.BLAZE_POWDER,1, Arrays.asList(),API.Color("&6&l"+Attributes.CRITICAL.toString()));
    }
    public static ItemStack critChance(){
        return API.IS(Material.BLAZE_POWDER,1, Arrays.asList(),API.Color("&6&l"+Attributes.CRITICAL_CHANCE.toString()),Enchantment.ARROW_DAMAGE,1,ItemFlag.HIDE_ENCHANTS);
    }
    public static ItemStack vampire(){
        return API.IS(Material.CHORUS_FRUIT_POPPED,1, Arrays.asList(),API.Color("&4&l"+Attributes.VAMPIRE.toString()));
    }
    public static ItemStack vampireChance(){
        return API.IS(Material.CHORUS_FRUIT_POPPED,1, Arrays.asList(),API.Color("&4&l"+Attributes.VAMPIRE_CHANCE.toString()),Enchantment.ARROW_DAMAGE,1,ItemFlag.HIDE_ENCHANTS);
    }

}
