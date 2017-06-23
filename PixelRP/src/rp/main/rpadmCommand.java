package rp.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import rp.missions.mission;
import rp.missions.missionManager;
import rp.utilities.databaseManager;

public class rpadmCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		Player p =(Player) sender;
		if(args[0].equals("createmission"))
		{
			mission newmission = null;
			newmission.missionName = args[1];

		}else if(args[0].equals("setmission"))
		{
			mission m = missionManager.getMission(args[1]);
			if(args[2].equals("trigger"))
			{
				//Lancer la procédure pour mettre un trigger
			}
			

		}else if(args[0].equals("data"))
		{
			p.sendMessage("mission " + databaseManager.getPlayerMission(p));
			
		}
		return true;
	}
}
