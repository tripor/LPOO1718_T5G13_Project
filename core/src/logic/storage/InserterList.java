package logic.storage;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import inserter.Inserter;

public class InserterList extends Group{
	
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	
	public InserterList(GameStage game) {
		this.game=game;

	}
	
	public void addInserter(Inserter i) {
		this.game.map().addMap(i,(int) i.getX(),(int) i.getY(), (int) i.getWidth(), (int) i.getHeight());
		this.addActor(i);
	}
}
