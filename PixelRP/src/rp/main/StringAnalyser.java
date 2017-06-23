package rp.main;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class StringAnalyser {

	public static boolean doActionbyString(Player p, String text)
	{
		ArrayList<String> txt = new ArrayList<String>();
		for(String s:text.split("////"))
		{
			txt.add(s);
		}
		if(txt.size() == 1)
		{
			p.sendMessage(txt.get(0));
		}else
		{
			if(txt.get(0).equals("command"))
			{
				
			}else if(txt.get(0).equals("asynctp"))
			{
				
			}else if(txt.get(0).equals("ask"))
			{
				
			}else if(txt.get(0).equals(""))
			{
				
			}
		}
		return true;

	}
}
