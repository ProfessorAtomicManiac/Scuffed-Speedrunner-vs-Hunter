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

public class trackCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("track")) {
            if (!(sender instanceof Player)) {
                // console
                sender.sendMessage("The console cannot run this command");
                return true;
            }
            Player player = (Player) sender;
            if (args.length == 0) {
                // /track
                player.sendMessage(ChatColor.RED + "Usage: /track {player}");
            }
            if (args.length >= 1) {
                for (String username : onlinePlayers()) {
                    if (args[0].equalsIgnoreCase(username)) {
                        if (player.getInventory().firstEmpty() == -1) {
                            // inventory is full
                            Location loc = player.getLocation();
                            World world = player.getWorld();
                            world.dropItemNaturally(loc, getItem(username));
                            player.sendMessage(ChatColor.GOLD + "You are now tracking " + username);
                            return true;
                        }
                        Player tracking = Bukkit.getServer().getPlayer(username);
                        player.sendMessage(ChatColor.RED + "You are now tracking " + username);
                        player.getInventory().addItem(getItem(username));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<String> onlinePlayers() {
        List<String> list = new ArrayList<String>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            list.add(p.getName());
        }
        return list;
    }

    public ItemStack getItem(String player) {

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();


        meta.setDisplayName(player);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Tracking Device");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);

        compass.setItemMeta(meta);

        return compass;
    }
}
