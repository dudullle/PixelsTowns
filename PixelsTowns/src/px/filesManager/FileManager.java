package px.filesManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.bukkit.Bukkit;

import px.main.pluginMain;
import px.towns.town;

public class FileManager {
	//Classe pour g�rer la lecture et la sauvegarde des fichiers
	public static void SaveFile(String path, ArrayList<String> text) 
	{
		PrintWriter ecri ;
		try
		{
			ecri = new PrintWriter(new FileWriter(path));
			for(String str:text)
			{
				ecri.println(str);	

			}
			ecri.flush();
			ecri.close();
		}//try
		catch (Exception a)
		{

		}
	}//ecrire
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
	public static String path = "Dudullle/towny/"; // -> /towns -> fichiers des villes -> /players -> dossier des joueurs -> /config -> Donn�es IMPORTANTES

	public static void loadTowns()
	{
		//Ouvrir le fichier de config /config/towns.txt
		ArrayList<String> Towns = ReadAllText(path + "config/towns.txt");
		Bukkit.getLogger().info("Chargement de : " + Towns.size() + " villes");
		
		for(String s : Towns)
		{
			town t = new town();
			t.load(s);
			pluginMain.towns.add(t);
		}
	}
	public static void saveTowns()
	{

		for(town s : pluginMain.towns)
		{
			s.save();
			
		}
	}

}
