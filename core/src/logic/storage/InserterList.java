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

		int width=(int) i.getWidth();
		int height = (int) i.getHeight();
		this.game.map().addMap(i, i.getRow(), i.getCol(), width, height);
		this.addActor(i);
	}
}
