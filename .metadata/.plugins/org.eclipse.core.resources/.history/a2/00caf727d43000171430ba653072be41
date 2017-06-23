package PixelsSW.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.world.DataException;


public class pluginMain extends JavaPlugin {
	//CLASSE PRINCIPALE
	public static ArrayList<String> plinarena = new ArrayList<String>();
	public static ArrayList<String> arenaNames = new ArrayList<String>();
	public static ArrayList<Location> locs = new ArrayList<Location>();
	public static String currentArena = "";
	public static boolean status = false;
	public static int count = 12;
	public static int gameTime = 600;
	@Override
	public void onEnable() {
		//INITIALISATION du plugin
		this.getCommand("hg").setExecutor(new rpadmCommand()); //Enregistrement de /town
		getServer().getPluginManager().registerEvents(new eventListener(), this); //Enregistrement de l'espion d'evenements
		//arenaNames.add("sw01");
		arenaNames.add("HG");

		doTick();
	}


	//Fonctions
	public static void doTick()
	{
		World world = Bukkit.getWorld("hg");
		new BukkitRunnable() {
			
			@Override
			public void run() {
				doTick();
				if(status == false && plinarena.size() >= 2)
				{
					if(count == 11)
					{
						WorldBorder border = world.getWorldBorder();
						gameTime = 600;
						border.setSize(gameTime);
						count -= 1;
						SchManager.setvoid(Bukkit.getWorld("hg"));

					}if(count == 6)
					{
						count -= 1;
						try {
							prestart();
						} catch (MaxChangedBlocksException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(count <= 0)
					{
						count = 12;
						startGame();
					}else
					{
						count -=1;
						Bukkit.broadcastMessage("§aHUNGERS GAMES ! faites §b/hg §apour rejoindre le jeu !" + plinarena.size() + "/12");
						for(String s:plinarena)
						{

							try
							{
								Bukkit.getPlayer(s).sendTitle("§a" + count*10 +" secondes !", "§bPréparez vous à tuer");
							}catch(Exception ex)
							{

							}
						}
					}
				}else if(status == true)
				{
					//Jeu en cours
					
					try
					{
					WorldBorder border = world.getWorldBorder();
					border.setSize(gameTime);
					}catch(Exception Ex)
					{
						
					}
					gameTime -= 4;
					
					if(plinarena.size() == 1)
					{
						Bukkit.broadcastMessage(plinarena.get(0) + "§a a gagné le HungerGames ["+ currentArena + "]");
						Bukkit.getPlayer(plinarena.get(0)).teleport(Bukkit.getWorld("games2").getSpawnLocation());
						plinarena.remove(0);
						status = false;
					}if(plinarena.size() == 0 || gameTime == 0)
					{
						Bukkit.broadcastMessage("§cPersonne a gagné le HungerGames ["+ currentArena + "]");
						plinarena.clear();
						status = false;
					}else
					{
						for(String s:plinarena)
						{
							Bukkit.getPlayer(s).sendMessage("§aJoueurs en jeu : " + plinarena.toString());
						}
					}

				}else if(status == false && plinarena.size() == 1)
				{
					
					//Jeu pas en cours mais pas assez de gens pour start
					Bukkit.broadcastMessage("§aHUNGERS GAMES ! faites §b/hg §apour rejoindre le jeu ! 1/12");
				}
			}

		}.runTaskLater(Bukkit.getPluginManager().getPlugin("PixelsSW"), 20*10);
	}


	public static void prestart() throws MaxChangedBlocksException, DataException, IOException
	{
		currentArena = arenaNames.get(new Random().nextInt(arenaNames.size()));
		File b = new File("Dudullle/sw/" + currentArena + ".schematic");
		SchManager.pasteSchematics(Bukkit.getWorld("hg"), b, new Location(Bukkit.getWorld("hg"),0,30,0));
	}


	public static void startGame()
	{
		ArrayList<String> Lines = ReadAllText("Dudullle/sw/" + currentArena +".txt");

		for(String ln:Lines)
		{
			try
			{
				locs.add(new Location(Bukkit.getWorld("hg"),Integer.parseInt(ln.split("/")[0]),Integer.parseInt(ln.split("/")[1]),Integer.parseInt(ln.split("/")[2])));
			}catch(Exception e)
			{

			}

		}
		status = true;
		int i = 0;
		for(String s:plinarena)
		{

			try
			{
				Player p = Bukkit.getPlayer(s);

				p.teleport(getRandomSP(currentArena,i));
				p.sendTitle("§aGO ! GO! GO!", "Tuez tout !");
				PlayerInventory inventory = p.getInventory(); // The
				inventory.clear();
				// player's
				// inventory
				ItemStack itemstack = new ItemStack(
						Material.LEATHER_HELMET, 1); // Casque
				ItemStack itemstack1 = new ItemStack(
						Material.LEATHER_CHESTPLATE, 1); // Plastron
				ItemStack itemstack2 = new ItemStack(
						Material.LEATHER_LEGGINGS, 1); // Pantalon
				ItemStack itemstack3 = new ItemStack(
						Material.LEATHER_BOOTS, 1); // Bottes
				ItemStack itemstack4 = new ItemStack(
						Material.STONE_SWORD, 1); // epee
				ItemStack itemstack5 = new ItemStack(
						Material.STONE_PICKAXE, 1); // Pelle
				// en
				ItemStack itemstack6 = new ItemStack(
						Material.COOKED_BEEF, 16);									

				inventory.addItem(itemstack);
				inventory.addItem(itemstack1);
				inventory.addItem(itemstack2);
				inventory.addItem(itemstack3);
				inventory.addItem(itemstack4);
				inventory.addItem(itemstack5);
				inventory.addItem(itemstack6);		
				i+=1;
				
			}catch(Exception ex)
			{

			}
		}
	}
	public static void stopGame()
	{
		locs.clear();
		plinarena.clear();
		status = false;
		currentArena = "";

	}

	public static Location getRandomSP(String Arena,int id)
	{

		return locs.get(id);
	}
	public static ArrayList<String> ReadAllText(String fichier) {
		ArrayList<String> txt = new ArrayList<String>();
		int i =0;
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){

				txt.add(ligne);
				i+=1;
			}
			br.close(); 
		}		
		catch (Exception e){
			//System.out.println(e.toString());

		}
		return txt;
	}
}
