package PixelsSW.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;



public class eventListener implements Listener{
	//Mettre les actions ici en fonction des events.

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();
		if(pluginMain.plinarena.contains(p.getDisplayName()))
		{
			e.setRespawnLocation(Bukkit.getWorld("games2").getSpawnLocation());
			p.sendMessage("Vous êtes mort !");
			Bukkit.broadcastMessage(p.getDisplayName() + " est mort (HungerGames)");
			pluginMain.plinarena.remove(p.getDisplayName());
		}

	}
	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		if(pluginMain.plinarena.contains(p.getDisplayName()))
		{
			pluginMain.plinarena.remove(p.getDisplayName());
		}
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if(pluginMain.plinarena.contains(p.getDisplayName()) && p.getWorld().getName().equals("hg"))
		{
			if(e.getClickedBlock().getType().equals(Material.CHEST))
			{
				e.getClickedBlock().breakNaturally();
			}
		}
	}
}
