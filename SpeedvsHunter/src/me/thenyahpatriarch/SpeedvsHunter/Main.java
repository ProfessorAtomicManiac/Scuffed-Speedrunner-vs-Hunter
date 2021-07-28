package me.thenyahpatriarch.SpeedvsHunter;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static void main(String[] args) {

    }

    @Override
    public void onEnable() {

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("track").setExecutor(new trackCommand());
        this.getCommand("death").setExecutor(new smiteCommand());
        this.getCommand("track").setTabCompleter(new TrackTab());
    }

    @Override
    public void onDisable() {

    }

    @EventHandler()
    public void onClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                Player player = event.getPlayer();
                Player target = Bukkit.getServer().getPlayer(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName());

                // Right/Left Click
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR) {
                    player.updateInventory();
                    player.setCompassTarget(target.getLocation());
                }

            }

        }
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                Player player = event.getPlayer();
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR) {
                    player.launchProjectile(Fireball.class);
                }

            }
        }
    }

    @EventHandler
    public void hitfireball(ProjectileHitEvent evento) {
        EntityType fball = evento.getEntityType();
        if( fball != null && fball.equals(EntityType.FIREBALL)) {
            Fireball f = (Fireball) evento.getEntity();
            Location location = f.getLocation();
            f.getWorld().createExplosion(location, 4);

        }
    }

}
































