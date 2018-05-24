package graphic;


import java.util.ArrayList;

import graphic.enteties.InserterG;


public class InserterList extends GroupExtension{
	
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	/**
	 * Constructor for the group of inserters of the game
	 * @param game the game it belongs to
	 */
	public InserterList(GameStage game) {
		this.game=game;
	}
	/**
	 * Adds actor to the group and to the map
	 * @param i The inserter I want to add
	 * @return True if it's possible to add, false otherwise
	 */
	public boolean addInserter(InserterG i) {

		if(this.game.map.addMap(i.instance))
		{
			this.addActor(i);
			return true;
		}
		return false;
	}
	/**
	 * Removes a inserter from the map and game
	 * @param i the inserter I want to remove
	 */
	public void removeInserter(InserterG i)
	{
		this.removeActor(i);
		this.game.map().removeMap(i.instance);
	}
	
}
