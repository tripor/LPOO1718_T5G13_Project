package logic.storage;


import java.util.ArrayList;

import com.groundup.game.GameStage;

import graphic.GroupExtension;
import inserter.Inserter;

public class InserterList extends GroupExtension{
	
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	/**
	 * Saves all the inserters of the game
	 */
	private ArrayList<Inserter> lista;
	/**
	 * Constructor for the group of inserters of the game
	 * @param game the game it belongs to
	 */
	public InserterList(GameStage game) {
		this.game=game;
		this.lista= new ArrayList<Inserter>();
	}
	/**
	 * Adds actor to the group and to the map
	 * @param i The inserter I want to add
	 * @return True if it's possible to add, false otherwise
	 */
	public boolean addInserter(Inserter i) {

		boolean success = this.game.map().tryAdd(i, this.game);
		
		if(!success) {
			return false;
		}
		
		this.game.map().addMap(i,(int) i.getX(),(int) i.getY(), (int) i.getWidth(), (int) i.getHeight());
		this.addActor(i);
		this.lista.add(i);
		
		return true;
	}
	/**
	 * Removes a inserter from the map and game
	 * @param i the inserter I want to remove
	 */
	public void removeInserter(Inserter i)
	{
		this.removeActor(i);
		this.game.map().removeMap(i,(int) i.getX(),(int) i.getY(), (int) i.getWidth(), (int) i.getHeight());
	}
	/**
	 * 
	 * @return Returns all the game inserters
	 */
	public ArrayList<Inserter> getLista() {
		return lista;
	}
	/**
	 * Re adds all the actores to the game 
	 */
	public void reAdd()
	{
		for(Inserter it:this.lista)
		{
			this.addActor(it);
		}
	}
	
}
