package rp.utilities;

import org.bukkit.entity.Player;

public class PlayerT {
	public String UUID = "";
	public int MissionID = 0;
	public int MissionSubID = 0;

	public void initialize(Player p)
	{
		UUID = p.getUniqueId().toString();
		MissionID = databaseManager.getPlayerMission(p);
		MissionSubID = databaseManager.getPlayerSubMission(p);
	}
}
