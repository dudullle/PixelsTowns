package dModeratorManagerBungee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import d.Moderator.Players.dPlayer;
import d.Moderator.Utilities.databaseManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.ServerPing.PlayerInfo;
import net.md_5.bungee.api.ServerPing.Players;
import net.md_5.bungee.api.ServerPing.Protocol;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class EventManager implements Listener {

	@EventHandler(priority = 99)
	public void onServerListPingEvent(ProxyPingEvent event) {
		ServerPing ping = event.getResponse();
		String motd = "";
		String ip = event.getConnection().getAddress().getHostString();
		//ANALYSE DES DOUBLES COMPTES
		boolean banned = databaseManager.getIpBanned(ip);
		if(banned)
		{
			
			motd = "§AP§BI§CX§DE§EL§FS §1C§2R§3R§4A§5F§6T§AE§BR§CS §b2.1 - §e1.12 - §c§lSLIMEFUN ! "
					+ "\n       §4 >>>>>>> Vous êtes §c§lBANNI §4de ce serveur. <<<<<<<<<";

		}else
		{
			motd = "§AP§BI§CX§DE§EL§FS §1C§2R§3R§4A§5F§6T§AE§BR§CS §b2.1 - §e1.12 - §c§lSLIMEFUN ! "
					+ "\n §a>>>§e Skyblock§a - §dRolePlay §a- §c PvP §a- §2Mini-jeux §a<<<<";
		}
		ping.setDescription(motd);

		event.setResponse(ping);

	}


	@EventHandler
	public void onPostLogin(PreLoginEvent event) {
		//EVENT
		//1 : Analyse si proxi

		//3 : Récup des données dans la DATABASE
		//4 : Recup des comptes
		//5 : Analyse si banni
		//6 : Recup des mails
		//7 : Envoi du msg en fonction

		//--1--
		String playerip = event.getConnection().getAddress().getHostString();
		String playerName = event.getConnection().getName().toString();
		boolean allowed = true;

		try
		{

			URL url = new URL("http://stpprx2server.altervista.org/algo3.php?ip=" + playerip);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String res = in.readLine();in.close();
			if (res.contains("si_pr"))
			{
				event.setCancelReason("§cVPN / PROXI détecté : Merci d'utiliser une connexion normale !\n (PS : les bots ca marche pas chez nous !");
				event.setCancelled(true);
				allowed = false;
				//Notif
				for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
					if(p.hasPermission("modo.notify"))
					{
						p.sendMessage("§cVPN / PROXI : §4" + playerName + "§c avec l'ip :§4 " + playerip);
					}
				}

			}
		}catch(Exception ex)
		{

		}
		if(allowed == true)
		{
			databaseManager.initPlayer(playerName,playerip);
			databaseManager.addIP(playerName,playerip);
			dPlayer dp = new dPlayer();
			dp.Ips = databaseManager.getIPS(playerName);
			//ANALYSE DES DOUBLES COMPTES
			ArrayList<String> comptes = databaseManager.getAllAccounts(playerName, dp);
			String fComptes = "";
			boolean banned = false;
			String banreason = "";
			for(String s: comptes)
			{
				//Analyse si banni !
				if(databaseManager.getBanned(s) == 1)
				{
					banreason = databaseManager.getBannedReason(s);
					event.setCancelReason("§4Vous êtes banni !\n " + banreason +"\n§c§l TOUT DOUBLE COMPTE ENTRAINE UN BANNISSEMENT SANS POSSIBILITE DE DEBAN !");
					event.setCancelled(true);
					fComptes += "§4"+s+ " ";
					banned = true;
				}else
				{
					fComptes += databaseManager.getApprouved(s) + s + " ";
				}

			}

			ProxyServer.getInstance().getLogger().info("§aComptes du joueur "+ playerName + " : " + fComptes);
			if(banned == false)
			{
				for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
					if(p.hasPermission("modo.notify"))
					{
						p.sendMessage("§a" + playerName + "§2a rejoint le serveur avec l'ip : §6" + playerip);
						p.sendMessage("§2Comptes détectés §6" + fComptes);
					}
				}
			}else
			{
				for(String s: comptes)
				{
					databaseManager.banPlayer(playerName, banreason);
				}
				for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
					if(p.hasPermission("modo.notify"))
					{
						p.sendMessage("§c" + playerName + "§4a tenté de rejoindre le serveur avec l'ip : §6" + playerip);
						p.sendMessage("§2Comptes détectés §6" + fComptes +"§2 ; Raison du ban : §6 : "+banreason);
					}
				}
			}
		}
	}
}
