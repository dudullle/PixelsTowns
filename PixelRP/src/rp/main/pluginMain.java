package rp.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import rp.missions.mission;

public class pluginMain extends JavaPlugin {
	//CLASSE PRINCIPALE
	public static ArrayList<mission> missions = new ArrayList<mission>();
	@Override
	public void onEnable() {
		//INITIALISATION du plugin
		this.getCommand("rpadm").setExecutor(new rpadmCommand()); //Enregistrement de /town
		getServer().getPluginManager().registerEvents(new eventListener(), this); //Enregistrement de l'espion d'evenements

	}
}
