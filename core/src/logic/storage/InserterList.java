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
	
	public boolean addInserter(Inserter i) {

		boolean success = Map.tryAdd(i, this.game);
		
		if(!success) {
			return false;
		}
		
		this.game.map().addMap(i,(int) i.getX(),(int) i.getY(), (int) i.getWidth(), (int) i.getHeight());
		this.addActor(i);
		
		return true;
	}
}
