package px.towns;

import java.util.ArrayList;

public class town {
//Objet Ville
	
	public String TownName = "NO NAME"; //Nom de la ville
	
	public ArrayList<String> playersUUID = new ArrayList<String>(); //Liste des membres de la ville
	public ArrayList<townBlock> townBlocks = new ArrayList<townBlock>(); //Liste des �l�ments de la ville
	
	public int lvl = 0; //Niveau de la Ville
	
	
	public boolean load(String TownName)
	{
		
		return false;
	}
	public boolean save()
	{
		return false;
	}
}
