package graphic;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;

import graphic.entities.InserterG;
import logic.Place;
import logic.entities.InserterL;


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
	public void loadFromMap()
	{
		for(InserterL it:this.game.map.lista_inserter)
		{
			InserterG novo=new InserterG(this.game,it);
			this.addInserter(novo);
		}
	}
	
}
