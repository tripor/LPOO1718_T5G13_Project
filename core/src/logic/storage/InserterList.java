package logic.storage;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import inserter.Inserter;
import logic.map.Map;

public class InserterList extends Group{
	
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	
	public InserterList(GameStage game) {
		this.game=game;

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
		
		return true;
	}
	public void removeInserter(Inserter i)
	{
		this.removeActor(i);
		this.game.map().removeMap(i,(int) i.getX(),(int) i.getY(), (int) i.getWidth(), (int) i.getHeight());
	}
	
}
