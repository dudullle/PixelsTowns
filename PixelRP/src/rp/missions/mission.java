package rp.missions;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class mission {
	public ArrayList<submission> sub = new ArrayList<submission>();
	public String missionName = "none";
	public Location startTrigger;
	
	public void launchSubMission(int id, Player p)
	{
		sub.get(id).launch(p);
	}
}
