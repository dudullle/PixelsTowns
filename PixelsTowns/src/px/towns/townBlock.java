package px.towns;

import org.bukkit.entity.Player;

import px.blocks.blockType;


public class townBlock {
	public int xCoord = 0; //Coordon�es en X (ATTENTION carr�s de 16*16)
	public int yCoord = 0; //Coordon�es en Y
	public int groundLVL = 0; // Niveau du sol
	
	public int blockLVL = 0; //Niveau du block (0 si vide)
	public blockType type = null; //Type du block
	public boolean broken = false; //Indique si le bloc est endomag� ou pas
	
	public void load(String BlockFile)
	{
		
	}
	public void save(String BlockFile)
	{
		
	}
	public void blockInteract(int x, int y)
	{
		//Interragir avec un autre bloc
	}
	public void playerInteract(Player p)
	{
		//Interragir avec un joueur
	}
}
