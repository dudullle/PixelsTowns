package px.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import px.towns.town;

public class pluginMain extends JavaPlugin {
	//CLASSE PRINCIPALE
	World w = Bukkit.getWorld("surv_twn"); //Variable du monde ATTENTION toujours utiliser cette variable.
	public static ArrayList<town> towns = new ArrayList<town>(); //Liste des villes (IMPORTANT)
	
	@Override
	public void onEnable() {
		//INITIALISATION du plugin
		this.getCommand("town").setExecutor(new townCommand()); //Enregistrement de /town
		getServer().getPluginManager().registerEvents(new eventListener(), this); //Enregistrement de l'espion d'evenements
		Bukkit.getLogger().info("Chargement des villes existantes ...");
		
		
	}
}
