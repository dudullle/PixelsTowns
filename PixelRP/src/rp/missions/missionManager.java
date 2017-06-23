package rp.missions;

import java.util.ArrayList;

import org.bukkit.Location;

import rp.main.pluginMain;

public class missionManager {
	public static mission getMission(String Name)
	{
		for(mission m:pluginMain.missions)
		{
			if(m.missionName.equals(Name))
			{
				return m;
			}
		}
		return null;
	}

}
