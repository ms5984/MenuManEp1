package com.github.sanctum.menu_man.ep1test;

import com.github.ms5984.lib.menuman.Menu;
import com.github.ms5984.lib.menuman.MenuBuilder;
import com.github.ms5984.lib.menuman.MenuClick;
import com.github.sanctum.labyrinth.library.StringUtils;
import com.youtube.hempfest.clans.util.events.command.CommandInsertEvent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ep1Test extends JavaPlugin implements Listener {

    Menu menu;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        menu = new MenuBuilder(Menu.InventoryRows.THREE, "Gamemode Selector")
                .addElement(new ItemStack(Material.GREEN_CONCRETE), StringUtils.translate("&cCreative"))
                .setAction(this::onCreative)
                .assignToSlots(10)
                .addElement(new ItemStack(Material.BLUE_CONCRETE), StringUtils.translate("&9Survival"))
                .setAction(this::onSurvival)
                .assignToSlots(12)
                .create(this);
    }

    @EventHandler
    public void onCommand(CommandInsertEvent e) {
        if (e.getArgs()[0].equalsIgnoreCase("menu")) {
            menu.open(e.getSender());
        }
    }

    public void onCreative(MenuClick menuClick) {
        Player p = menuClick.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE) {
            p.sendMessage("Already in creative!");
        } else if (p.getGameMode() == GameMode.SURVIVAL) {
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage("Now in creative!");
        }
    }

    public void onSurvival(MenuClick menuClick) {
        Player p = menuClick.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL) {
            p.sendMessage("Already in survival!");
        } else if (p.getGameMode() == GameMode.CREATIVE) {
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage("Now in survival!");
        }
    }
}
