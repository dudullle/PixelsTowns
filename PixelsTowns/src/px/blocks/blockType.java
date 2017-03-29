package px.blocks;

public abstract class blockType {
	
	public int blocId = 0; //0 = vide
	abstract boolean build(); //Actions pour build l'�l�ment
	abstract boolean improve(); //Actions pour am�liorer l'�l�ment
	abstract boolean repair(); //Actions pour r�parer l'�l�ment
	abstract void doTick(); //Actions � effectuer lors d'un tick
}
