package PixelsSW.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class rpadmCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		Player p = (Player) sender;
		if(args.length == 0)
		{
			//Join le jeu
			if(pluginMain.plinarena.contains(p.getDisplayName()))
			{
				p.sendMessage("§cVous avez déja rejoint !");
			}else if(pluginMain.plinarena.size() < 13 && pluginMain.status == false)
			{
				pluginMain.plinarena.add(p.getDisplayName());
				p.sendMessage("§aVous avez join le jeu !");
			}else
			{
				p.sendMessage("§cTrop tard ... plus de place dispo ou jeu en cours !");
			}
			
		}else if(args[0].equals("leave"))
		{
			pluginMain.plinarena.remove(1);
		}
		return true;
	}
}
