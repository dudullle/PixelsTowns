package rp.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import rp.utilities.databaseManager;

public class eventListener implements Listener{
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	
	
	//Mettre les actions ici en fonction des events.
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e)
	{
		Player p = e.getPlayer();
		if(p.getWorld().getName().equals("survv2"))
		{
			databaseManager.initPlayer(p);
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) { // Quand un joueur
		Player p = e.getPlayer();
		Location from = e.getFrom();
		Location to = e.getTo();

		if(from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ())
		{
			if(p.getWorld().getName().equals("survv2") || p.getWorld() == Bukkit.getWorld("space"))
			{

				try
				{
					setScoreBoard(p);
				}catch(Exception ex)
				{
					
				}
			}
		}
	}

	//-------------------------------------------


	public void setScoreBoard(Player p)
	{
		try
		{

			Scoreboard board = manager.getNewScoreboard();
			Objective objective;
			try
			{
				objective = board.registerNewObjective("test2", "dummy");
			}catch(Exception coucou)
			{
				objective = board.getObjective("test2");
			}

			Score score = objective.getScore("§aNuméro de la mission : §b" + databaseManager.getPlayerMission(p));
			Score score2 = objective.getScore("§aSous mission : §b" + databaseManager.getPlayerSubMission(p));

			//Setting where to display the scoreboard/objective (either SIDEBAR, PLAYER_LIST or BELOW_NAME)
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			objective.setDisplayName("§2>> §l" + "§2<<");

			//Setting where to display the scoreboard/objective (either SIDEBAR, PLAYER_LIST or BELOW_NAME)
			//Get a fake offline player
			score.setScore(10);
			score2.setScore(9);
			p.setScoreboard(board);
		}catch(Exception ex)
		{
			p.sendMessage(ex.toString());
		}
	}
}
