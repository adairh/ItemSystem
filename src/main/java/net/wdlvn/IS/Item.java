package net.wdlvn.IS;

import net.wdlvn.IS.Util.NBT.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Item {

    private String path = "";
    private Material material = Material.AIR;
    private String name = "";
    private List<String> lore = new ArrayList<String>();
    private HashMap<Attributes, Double> attr = new HashMap<Attributes, Double>();
    private List<ItemFlag> itemFlags = new ArrayList<ItemFlag>();
    private HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
    private ItemStack itemStack;

  /*  public Item(Material material, String name, List<String> lore, List<ItemFlag> itemFlags,
                HashMap<Enchantment, Integer> enchantments, HashMap<Attributes, Double> attr, String savePath){
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.attr = attr;
        this.itemFlags = itemFlags;
        this.enchantments = enchantments;
        this.path = savePath;
        saveItem();

    }*/

    public Item(ItemStack is, HashMap<Attributes, Double> attr, String savePath){
        this.material = is.getType();
        this.name = is.getItemMeta().getDisplayName();
        this.lore = is.getItemMeta().getLore();
        this.attr = attr;
        this.itemFlags = new ArrayList<ItemFlag>(is.getItemMeta().getItemFlags());
        this.enchantments = new HashMap<Enchantment, Integer>(is.getEnchantments());
        this.path = savePath;
        this.itemStack = is;
        saveItem();
    }

    public Item(String nameFile){
        this.path = nameFile;
        File file = new File(ItemSystem.getIns().getDataFolder()+"/Items",nameFile+".yml");
        if (file.exists()){
            FileConfiguration f = YamlConfiguration.loadConfiguration(file);
            ItemStack is = f.getItemStack(nameFile+".Item");
            this.material = is.getType();
            this.name = is.getItemMeta().getDisplayName();
            this.lore = is.getItemMeta().getLore();

            this.itemFlags = new ArrayList<>(is.getItemMeta().getItemFlags());

            this.enchantments = new HashMap<>(is.getEnchantments());

            this.itemStack = is;

            HashMap<Attributes, Double> attributesList = new HashMap<Attributes, Double>();
            for (String s:f.getConfigurationSection(nameFile+".Attributes").getKeys(false)) {
                for (Attributes a : Attributes.values()) {
                    if (a.toString().equalsIgnoreCase(s)) {
                        attributesList.put(a, f.getDouble(nameFile + ".Attributes." + s));
                    }
                }
            }
            this.attr = attributesList;
        }
    }

    public ItemStack getItemStack(){/*
        ItemStack is = new ItemStack(this.material);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(this.name);
        im.setLore(this.lore);
        for (int i = 0;i < this.itemFlags.size();i++) {
            im.addItemFlags(this.itemFlags.get(i));
        }

        is.addUnsafeEnchantments(enchantments);

       *//* for (Enchantment en:enchantments.keySet()) {
            is.addEnchantment(en,enchantments.get(en));
        }*//*

        is.setItemMeta(im);

        NBTItem nbti = new NBTItem(is);
        nbti.setString("ItemSystem",this.path);
*/

        NBTItem nbti = new NBTItem(this.itemStack);
        nbti.setString("ItemSystem",this.path);
        return nbti.getItem();
    }

    public String getPath() {
        return this.path;
    }

    public Material getMaterial(){
        return this.material;
    }

    public String getName(){
        return this.name;
    }

    public List<String> getLore(){
        return this.lore;
    }

    public List<ItemFlag> getItemFlags(){
        return this.itemFlags;
    }

    public HashMap<Attributes, Double> getAttr(){
        return this.attr;
    }

    public HashMap<Enchantment, Integer> getEnchantments(){
        return this.enchantments;
    }

    public Double getAttrValue(Attributes a){
        if (this.attr.containsKey(a)) {
            return this.attr.get(a);
        }
        return 0.0;
    }

    private void saveItem(){
        String nameFile = this.path;
        HashMap<Attributes, Double> attr = this.attr;

        File f = new File(ItemSystem.getIns().getDataFolder()+"/Items",nameFile+".yml");
            if (!f.exists()){
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();

                YamlConfiguration file = YamlConfiguration.loadConfiguration(f);

                file.save(f);

                file.addDefault(nameFile+".Item",this.getItemStack());
                for (Attributes a:attr.keySet()){
                    file.addDefault(nameFile+".Attributes."+a.toString(),attr.get(a));
                }
                file.options().copyDefaults(true);
                file.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Item> getAllItem(){
        File f = new File(ItemSystem.getIns().getDataFolder()+"/Items");
        File[] listItem = f.listFiles();
        List<Item> items = new ArrayList<Item>();
        if (f.exists()) {
            for (int i = 0; i < listItem.length; i++) {
                File item = listItem[i];
                String name = item.getName().replace(".yml","");
                items.add(new Item(name));
            }
        }
        return items;
    }

    public static boolean isControled(ItemStack is){
        NBTItem nbti = new NBTItem(is);
        if (nbti.hasNBTData()) {
              if (nbti.hasKey("ItemSystem")) {
                  for (Item i:getAllItem()){
                      if (nbti.getString("ItemSystem").equals(i.path)){
                        return true;
                      }
                  }
            }
        }
        return false;
    }


    public static Item getItem(ItemStack is){
        if (isControled(is)) {
            NBTItem nbti = new NBTItem(is);
            String path = nbti.getString("ItemSystem");
            return new Item(path);
        }
        return null;
    }

}


