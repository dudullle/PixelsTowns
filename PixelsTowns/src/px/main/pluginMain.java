package px.towny.main;

import org.bukkit.plugin.java.JavaPlugin;

public class pluginMain extends JavaPlugin {
	//CLASSE PRINCIPALE
	@Override
	public void onEnable() {
		//INITIALISATION du plugin
		this.getCommand("town").setExecutor(new townCommand()); //Enregistrement de /town
		getServer().getPluginManager().registerEvents(new eventListener(), this); //Enregistrement de l'espion d'evenements

		
	}
}
