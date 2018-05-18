package logic.storage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import conveyor.Conveyor;

public class ConveyorList extends Group{
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	public ConveyorList(GameStage game) {
		this.game=game;
	}
	
	public boolean addConveyor(Conveyor c) {
		this.addActor(c);
		this.game.map().addMap(c,(int) c.getX(), (int)c.getY(), (int)c.getWidth(),(int) c.getHeight());
		return false;
	}
}
