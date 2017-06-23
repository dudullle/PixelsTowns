package com.gamingmesh.jobs.MythicMobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

import com.gamingmesh.jobs.Jobs;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;

public class MythicMobs4 implements MythicMobInterface {

    public BukkitAPIHelper MMAPI = null;
    private Jobs plugin;

    public MythicMobs4(Jobs plugin) {
	this.plugin = plugin;

    }

    @Override
    public void registerListener() {
	Bukkit.getServer().getPluginManager().registerEvents(new MythicMobs4Listener(plugin), plugin);
    }

    @Override
    public boolean isMythicMob(LivingEntity lVictim) {
	if (MMAPI.isMythicMob(lVictim))
	    return true;
	return false;
    }

    @Override
    public boolean Check() {
	Plugin mm = Bukkit.getPluginManager().getPlugin("MythicMobs");
	if (mm == null)
	    return false;

	try {
	    Class.forName("io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent");
	    Class.forName("io.lumine.xikage.mythicmobs.mobs.MythicMob");
	    Class.forName("io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper");
	    Class.forName("io.lumine.xikage.mythicmobs.MythicMobs");
	} catch (ClassNotFoundException e) {
	    // Disabling
	    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
		"&e[Jobs] &6MythicMobs was found - &cBut your version is outdated, please update for full support."));
	    return false;
	}

	MMAPI = ((MythicMobs) mm).getAPIHelper();
	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[Jobs] &6MythicMobs4 was found - Enabling capabilities."));
	return true;

    }
}
