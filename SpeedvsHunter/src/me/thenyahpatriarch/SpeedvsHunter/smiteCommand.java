package me.thenyahpatriarch.SpeedvsHunter;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class smiteCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("death")) {
            if (!(sender instanceof Player)) {
                // console
                sender.sendMessage("The console cannot run this command");
                return true;
            }
            Player player = (Player) sender;
            if (args.length == 0) {
                // /track
                if (player.getInventory().firstEmpty() == -1) {
                    // inventory is full
                    Location loc = player.getLocation();
                    World world = player.getWorld();
                    world.dropItemNaturally(loc, getItem());
                    player.sendMessage(ChatColor.DARK_RED + "BLOOD FOR THE BLOOD GOD ");
                    return true;
                }
                player.sendMessage(ChatColor.DARK_RED + "BlOOD FOR THE BLOOD GOD");
                player.getInventory().addItem(getItem());
                return true;
            }
        }

        return false;
    }

    public ItemStack getItem() {

        ItemStack wand = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = wand.getItemMeta();


        meta.setDisplayName("Fireball Wand");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "haha fireballs go brrrrrr");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);

        wand.setItemMeta(meta);

        return wand;
    }
}
