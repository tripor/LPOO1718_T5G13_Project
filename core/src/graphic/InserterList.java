package graphic;

import com.badlogic.gdx.scenes.scene2d.Actor;

import graphic.entities.InserterG;
import logic.Entity;
import logic.entities.InserterL;


public class InserterList extends GroupExtension{
	/**
	 * Constructor for the group of inserters of the game
	 * @param game the game it belongs to
	 */
	public InserterList() {
	}
	/**
	 * Adds actor to the group and to the map
	 * @param i The inserter I want to add
	 * @return True if it's possible to add, false otherwise
	 */
	public boolean addInserter(InserterG i) {

		if(GameStage.singleton.map.addMap(i.instance))
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
		GameStage.singleton.map().removeMap(i.instance);
	}
	/**
	 * Removes a inserter from the map and game
	 * @param p the inserter I want to remove
	 */
	public void removeInserter(InserterL p) {
		for(Actor it:this.getChildren())
		{
			if(((ActorExtension) it).getInstance()==p)
			{
				this.removeInserter((InserterG) it);
			}
		}
	}
	/**
	 * Loads all the inserters to the screen
	 */
	public void loadFromMap() {
		for (Entity it : GameStage.singleton.map.lista) {
			if(InserterL.class.isAssignableFrom(it.getClass()))
			{
				InserterG novo = new InserterG(it);
				this.addInserter(novo);
			}
		}
	}

}
